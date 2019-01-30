package hr.foi.air.microsensor;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.utils.UrlBeaconUrlCompressor;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.core.CurrentActivity;
import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.fragments.RealtimeViewFragment;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Weather.WeatherResponse;
import hr.foi.air.webservice.Weather.WeatherSender;

public class HomepageActivity extends AppCompatActivity implements BeaconConsumer, RangeNotifier, NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener, Observer {
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final String TAG = "MainActivity";
    private BeaconManager mBeaconManager;
    BluetoothAdapter mBluetoothAdapter;
    Timer beaconStateNotifier;

    private String currentData = "-1;0;0;0";
    private String currentUser = "0";
    private boolean dataSent = false;
    private boolean activeState = false;

    @BindView(R.id.mDrawerLayout) DrawerLayout mDrawerLayout;
    @BindView(R.id.mNavigationView) NavigationView mNavigationView;
    @BindView(R.id.mToolbar) Toolbar mToolbar;
    ActionBarDrawerToggle drawerToggle;
    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);

        checkCoarseLocationPermission();
        checkBluetoothPermission();

        setCurrentUser();
        setCurrentActivity();

        initializeLayout();
        setBackStackChangeListener();
        initializeNavigationManager();
        startMainModule();
        startBeaconStateNotifier();
    }

    /**
     * Starts the timer which executes {@param notifyStateChanges} every 2 seconds.
     */
    public void startBeaconStateNotifier(){
        beaconStateNotifier = new Timer();
        beaconStateNotifier.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                notifyStateChanges();
            }
        },0,2000);
    }

    /**
     * Resets the Beacon state every 2 seconds to false (inactive) and notifies other fragments.
     */
    public void notifyStateChanges(){
        for (NavigationItem item : NavigationManager.getInstance().getNavigationItems()){
            item.setBeaconState(this.activeState);
        }
        this.activeState = false;
    }

    /**
     * Checks whether the user allows coarse location usage.
     */
    private void checkCoarseLocationPermission()
    {
        if(this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("This app needs location access");
            builder.setMessage("Please grant location access so this app can detect beacons.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }, PERMISSION_REQUEST_COARSE_LOCATION);
                }
            });
            builder.show();
        }
    }

    /**
     * Checks whether the user allows Bluetooth service.
     */
    private void checkBluetoothPermission()
    {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch(requestCode){
            case PERMISSION_REQUEST_COARSE_LOCATION:{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Log.d(TAG, "coarse location permission granted");
                }
                else{
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted," +
                            "this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            // No need to override this method
                        }
                    });
                    builder.show();
                }
                break;
            }
            default: break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mBeaconManager = BeaconManager.getInstanceForApplication(this.getApplicationContext());
        // Detect the URL frame:
        mBeaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout(BeaconParser.EDDYSTONE_URL_LAYOUT));
        mBeaconManager.bind(this);
    }

    public void onBeaconServiceConnect() {
        Region region = new Region("all-beacons-region", null, null, null);
        try {
            mBeaconManager.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mBeaconManager.addRangeNotifier(this);
    }

    /**
     * If beacon is in region, it will check whether it matches Eddystone UUID, parse the data from URL and send it to {@link RealtimeViewFragment}.
     * @param beacons List of beacons.
     * @param region List of all beacons in region.
     */
    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
        for (Beacon beacon: beacons) {
            if (beacon.getServiceUuid() == 0xfeaa && beacon.getBeaconTypeCode() == 0x10) {
                // This is a Eddystone-URL frame
                String url = UrlBeaconUrlCompressor.uncompress(beacon.getId1().toByteArray());
                Log.d(TAG, "I see a beacon transmitting a url: " + url +
                        " approximately " + beacon.getDistance() + " meters away.");

                this.currentData = url + ";" + this.currentUser;
                this.activeState = true;
                if (!dataSent){
                    sendData();
                }
                NavigationManager.getInstance().getNavigationItems().get(0).setData(currentData);
            }
        }
    }

    private void sendData() {
        String[] rawData = currentData.split(";");
        DataObservable.getInstance().addObserver(this);
        WeatherSender controller = new WeatherSender();
        controller.sendWeather(controller.create(), Integer.parseInt(rawData[0]), Integer.parseInt(rawData[1]), Integer.parseInt(rawData[2]), Integer.parseInt(rawData[3]));
    }

    /**
     * If data from Beacon is successfully sent to the server, it will show message and set the flag to true.
     * @param o Observer that is subscribed to subject.
     * @param arg Object that needs to be casted to {@link WeatherResponse}
     */
    @Override
    public void update(Observable o, Object arg) {
        String weatherResponse =  (String) arg;
        if(weatherResponse.equals("Query succeeded!"))
        {
            dataSent = true;
        }
        Toast.makeText(this, weatherResponse, Toast.LENGTH_SHORT).show();
        DataObservable.getInstance().deleteObserver(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBeaconManager.unbind(this);
    }

    /**
     * Sets the current activity.
     */
    private void setCurrentActivity() {
        CurrentActivity.setActivity(this);
    }

    /**
     * Sets the current user, receives data from Intent.
     */
    private void setCurrentUser()
    {
        Intent i = getIntent();
        this.currentUser = i.getStringExtra("currentUser");
        this.currentData = this.currentData + ";" + this.currentUser;
    }

    /**
     * Initializes the layout with actionbar, drawer and navigation view.
     */
    private void initializeLayout()
    {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Sets the backstack change listener on the support fragment manager.
     */
    private void setBackStackChangeListener()
    {
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    /**
     * Initializes {@link NavigationManager}.
     */
    private void initializeNavigationManager() {
        NavigationManager nm = NavigationManager.getInstance();
        nm.setDrawerDependencies(
                this,
                mNavigationView,
                mDrawerLayout,
                R.id.dynamic_group);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view menuItem clicks here.

        switch(menuItem.getItemId())
        {
            case R.id.mLogout:
                logoutUser();
                break;
            default:
                NavigationManager.getInstance().selectNavigationItem(menuItem, this.currentData);
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackStackChanged() {
        drawerToggle.setDrawerIndicatorEnabled(mFragmentManager.getBackStackEntryCount() == 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(mFragmentManager.getBackStackEntryCount() > 0);
        drawerToggle.syncState();
    }

    /**
     * Starts the main module (first module).
     */
    public void startMainModule() {
        NavigationManager.getInstance().startMainModule(this.currentData);
    }

    /**
     * Logs off the current user and cleans all memory in backstack.
     */
    public void logoutUser() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                    default: break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    @Override
    public void onStop(){
        beaconStateNotifier.cancel();
        super.onStop();
    }
}
