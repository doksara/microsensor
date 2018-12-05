package hr.foi.air.microsensor;

<<<<<<< HEAD
import android.content.Intent;
=======
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.RemoteException;
>>>>>>> user_interface
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
<<<<<<< HEAD
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import butterknife.ButterKnife;
import hr.foi.air.core.CurrentActivity;

public class HomepageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager mFragmentManager;
=======
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Weather.WeatherSender;

public class HomepageActivity extends AppCompatActivity implements Observer {
    @BindView(R.id.userMessage) TextView userMessage;
>>>>>>> bc4ea8f54aa0d33f7aa339b6fd163a30bbc1ebe8
=======
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.utils.UrlBeaconUrlCompressor;

import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.core.CurrentActivity;

public class HomepageActivity extends AppCompatActivity implements BeaconConsumer, RangeNotifier, NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private static final String TAG = "MainActivity";
    private BeaconManager mBeaconManager;
    String currentData = "0;0;0;0;0";

    @BindView(R.id.mDrawerLayout) DrawerLayout mDrawerLayout;
    @BindView(R.id.mNavigationView) NavigationView mNavigationView;
    @BindView(R.id.mToolbar) Toolbar mToolbar;
    ActionBarDrawerToggle drawerToggle;
    FragmentManager mFragmentManager;
>>>>>>> user_interface

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);

<<<<<<< HEAD
=======
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
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

>>>>>>> user_interface
        setCurrentActivity();
        initializeLayout();
        setBackStackChangeListener();
        initializeNavigationManager();
        startMainModule();
    }

<<<<<<< HEAD
=======
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
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

                        }
                    });
                    builder.show();
                }
                return;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mBeaconManager = BeaconManager.getInstanceForApplication(this.getApplicationContext());
        // Detect the URL frame:
        mBeaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout(BeaconParser.EDDYSTONE_URL_LAYOUT));
        mBeaconManager.bind((BeaconConsumer) this);
    }

    public void onBeaconServiceConnect() {
        Region region = new Region("all-beacons-region", null, null, null);
        try {
            mBeaconManager.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mBeaconManager.addRangeNotifier((RangeNotifier) this);
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
        for (Beacon beacon: beacons) {
            if (beacon.getServiceUuid() == 0xfeaa && beacon.getBeaconTypeCode() == 0x10) {
                // This is a Eddystone-URL frame
                String url = UrlBeaconUrlCompressor.uncompress(beacon.getId1().toByteArray());
                Log.d(TAG, "I see a beacon transmitting a url: " + url +
                        " approximately " + beacon.getDistance() + " meters away.");
                this.currentData = url;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mBeaconManager.unbind((BeaconConsumer) this);
    }

>>>>>>> user_interface
    private void setCurrentActivity() {
        CurrentActivity.setActivity(this);
    }

    private void initializeLayout()
    {
<<<<<<< HEAD
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
=======
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
>>>>>>> user_interface
    }

    private void setBackStackChangeListener()
    {
        getSupportFragmentManager().addOnBackStackChangedListener(this);
<<<<<<< HEAD
    }

    private void initializeNavigationManager() {
        NavigationManager nm = NavigationManager.getInstance();
        nm.setDrawerDependencies(
                this,
                navigationView,
                drawerLayout,
                R.id.dynamic_group);
    }

<<<<<<< HEAD
=======
    }

    private void initializeNavigationManager() {
        NavigationManager nm = NavigationManager.getInstance();
        nm.setDrawerDependencies(
                this,
                mNavigationView,
                mDrawerLayout,
                R.id.dynamic_group);
    }

>>>>>>> user_interface
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
            case R.id.menu_about:
                //do something
                break;
            default:
<<<<<<< HEAD
                NavigationManager.getInstance().selectNavigationItem(menuItem);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
=======
                NavigationManager.getInstance().selectNavigationItem(menuItem, this.currentData);
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
>>>>>>> user_interface
        return true;
    }

    @Override
    public void onBackStackChanged() {
        drawerToggle.setDrawerIndicatorEnabled(mFragmentManager.getBackStackEntryCount() == 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(mFragmentManager.getBackStackEntryCount() > 0);
        drawerToggle.syncState();
    }

    public void startMainModule() {
<<<<<<< HEAD
        NavigationManager.getInstance().startMainModule();
=======
    @OnClick(R.id.button2)
    public void onClick(View view)
    {
        DataObservable.getInstance().addObserver(this);
        WeatherSender controller = new WeatherSender();
        controller.sendWeather(controller.create(), 2, 20, 55, 25);
        //WeatherLoader controller = new WeatherLoader();
        //controller.loadWeather(controller.create(), "FOI1", "D101");
    }

    // U ovoj funkciji pozivati druge funkcije koje rade sa dohvaćenim podacima
    @Override
    public void update(Observable o, Object arg) {
        String response = (String) arg;
        Toast.makeText(HomepageActivity.this, response, Toast.LENGTH_SHORT).show();
        /*List<Object> list = (List<Object>) arg;
        String message = (String) list.get(1);
        List<Weather> weatherList = (List<Weather>) list.get(0);
        if(!weatherList.isEmpty())
        {
            TextView text1 = findViewById(R.id.textView);
            text1.setText(Integer.toString(weatherList.get(0).getTemperatura()));
            TextView text2 = findViewById(R.id.textView2);
            text2.setText(Float.toString(weatherList.get(1).getVlaznost_zraka()));
        }
        else {
            Toast.makeText(HomepageActivity.this, message, Toast.LENGTH_SHORT).show();
        }
        */
        DataObservable.getInstance().deleteObserver(this);
>>>>>>> bc4ea8f54aa0d33f7aa339b6fd163a30bbc1ebe8
=======
        NavigationManager.getInstance().startMainModule(this.currentData);
>>>>>>> user_interface
    }
}
