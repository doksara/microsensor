package hr.foi.air.microsensor;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.core.CurrentActivity;

public class HomepageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {
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

        setCurrentActivity();
        initializeLayout();
        setBackStackChangeListener();
        initializeNavigationManager();
        startMainModule();
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
                NavigationManager.getInstance().selectNavigationItem(menuItem);
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
        NavigationManager.getInstance().startMainModule();
    }
}
