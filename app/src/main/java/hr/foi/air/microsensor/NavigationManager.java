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
import hr.foi.air.microsensor.fragments.AttendanceMonitorFragment;
import hr.foi.air.microsensor.fragments.AttendanceSubmissionFragment;
import hr.foi.air.microsensor.fragments.RealtimeViewFragment;
import hr.foi.air.microsensor.fragments.StatisticsViewFragment;


public class NavigationManager {
    private static NavigationManager instance;
    private List<NavigationItem> navigationItems;

    private AppCompatActivity activity;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private int dynamicGroupId;

    /**
     * Private Singleton constructor. On construct, adds all the modules in the {@link List&lt;NavigationItem&gt;}.
     */
    private NavigationManager()
    {
        navigationItems = new ArrayList<>();
        navigationItems.add(new RealtimeViewFragment());
        navigationItems.add(new StatisticsViewFragment());
        navigationItems.add(new AttendanceMonitorFragment());
        navigationItems.add(new AttendanceSubmissionFragment());
    }

    /**
     * Returns the items as {@link List&lt;NavigationItem&gt;} from Singleton instance.
     * @return List of all modules.
     */
    public List<NavigationItem> getNavigationItems(){
        return this.navigationItems;
    }

    /**
     * Returns the Singleton instance.
     * @return instance of {@link NavigationManager}
     */
    public static NavigationManager getInstance()
    {
        if (instance == null)
            instance = new NavigationManager();

        return instance;
    }

    /**
     * Sets the drawer dependencies on the Navigation panel.
     * @param activity Current activity
     * @param navigationView Selected NavigationView
     * @param drawerLayout Selected DrawerLayout
     * @param dynamicGroupId ID of Dynamic group
     */
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

    /**
     * Sets up the drawer in HomepageActivity.
     */
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

    /**
     * Starts the Main module (first module in the {@link List&lt;NavigationItem&gt;})
     * @param currentData Data passed to the module on start.
     */
    public void startMainModule(String currentData) {
        NavigationItem mainModule = navigationItems != null ? navigationItems.get(0) : null;
        if (mainModule != null)
            startModule(mainModule, currentData);
    }

    /**
     * Starts the selected module.
     * @param module Module to be started
     * @param currentData Data passed to the module on start.
     */
    private void startModule(NavigationItem module, String currentData) {
        FragmentManager mFragmentManager = activity.getSupportFragmentManager();
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, module.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commitNow();

        DataManager.getInstance().sendData(module, currentData);
    }

    /**
     * Opens the selected navigation item and refreshes UI.
     * @param menuItem ID of the selected menu item
     * @param currentData Data to be passed to the module
     */
    public void selectNavigationItem(MenuItem menuItem, String currentData) {
        if (!menuItem.isChecked()) {
            menuItem.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);

            NavigationItem selectedItem = navigationItems.get(menuItem.getItemId());
            startModule(selectedItem, currentData);
        }
    }
}