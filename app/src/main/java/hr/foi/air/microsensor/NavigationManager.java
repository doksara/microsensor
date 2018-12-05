package hr.foi.air.microsensor;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.core.NavigationItem;
<<<<<<< HEAD
import hr.foi.air.microsensor.fragments.AttendanceFragment;
=======
import hr.foi.air.microsensor.fragments.AttendanceMonitorFragment;
import hr.foi.air.microsensor.fragments.AttendanceSubmissionFragment;
import hr.foi.air.microsensor.fragments.RealtimeViewFragment;
import hr.foi.air.microsensor.fragments.StatisticsViewFragment;
>>>>>>> user_interface


public class NavigationManager {
    private static NavigationManager instance;
    private List<NavigationItem> navigationItems;

    private AppCompatActivity activity;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private int dynamicGroupId;

    private NavigationManager()
    {
        navigationItems = new ArrayList<>();
<<<<<<< HEAD
        navigationItems.add(new AttendanceFragment());
        // ovdje idu sve opcije u navigaciji (realni, statisticki, prijava prisustva)
=======
        navigationItems.add(new RealtimeViewFragment());
        navigationItems.add(new StatisticsViewFragment());
        navigationItems.add(new AttendanceMonitorFragment());
        navigationItems.add(new AttendanceSubmissionFragment());
>>>>>>> user_interface
    }

    public static NavigationManager getInstance()
    {
        if (instance == null)
            instance = new NavigationManager();

        return instance;
    }

    public void setDrawerDependencies(
            AppCompatActivity activity,
            NavigationView navigationView,
            DrawerLayout drawerLayout,
            int dynamicGroupId)
    {
        this.activity = activity;
        this.navigationView = navigationView;
        this.drawerLayout = drawerLayout;
        this.dynamicGroupId = dynamicGroupId;

        setupDrawer();
    }

    private void setupDrawer()
    {
        for (int i = 0; i < navigationItems.size(); i++) {
            NavigationItem item = navigationItems.get(i);
            navigationView.getMenu()
                    .add(dynamicGroupId, i, i+1, item.getName(activity))
                    .setCheckable(true)
                    .setIcon(item.getIcon(activity));
        }
    }

<<<<<<< HEAD
    public void startMainModule() {
        NavigationItem mainModule = navigationItems != null ? navigationItems.get(0) : null;
        if (mainModule != null)
            startModule(mainModule);
    }

    private void startModule(NavigationItem module) {
=======
    public void startMainModule(String currentData) {
        NavigationItem mainModule = navigationItems != null ? navigationItems.get(0) : null;
        if (mainModule != null)
            startModule(mainModule, currentData);
    }

    private void startModule(NavigationItem module, String currentData) {
>>>>>>> user_interface
        FragmentManager mFragmentManager = activity.getSupportFragmentManager();
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, module.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

<<<<<<< HEAD
        DataManager.getInstance().sendData(module);
    }

    public void selectNavigationItem(MenuItem menuItem) {
=======
        DataManager.getInstance().sendData(module, currentData);
    }

    public void selectNavigationItem(MenuItem menuItem, String currentData) {
>>>>>>> user_interface
        if (!menuItem.isChecked()) {
            menuItem.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);

            NavigationItem selectedItem = navigationItems.get(menuItem.getItemId());
<<<<<<< HEAD
            startModule(selectedItem);
=======
            startModule(selectedItem, currentData);
>>>>>>> user_interface
        }
    }
}