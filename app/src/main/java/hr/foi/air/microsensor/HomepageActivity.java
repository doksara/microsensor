package hr.foi.air.microsensor;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);

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

        setCurrentActivity();
        initializeLayout();
        setBackStackChangeListener();
        initializeNavigationManager();
        startMainModule();
    }

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

    private void setCurrentActivity() {
        CurrentActivity.setActivity(this);
    }

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

    private void setBackStackChangeListener()
    {
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

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
            case R.id.menu_about:
                //do something
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

    public void startMainModule() {
        NavigationManager.getInstance().startMainModule(this.currentData);
    }
}
