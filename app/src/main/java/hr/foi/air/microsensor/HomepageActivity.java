package hr.foi.air.microsensor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setBackStackChangeListener()
    {
        getSupportFragmentManager().addOnBackStackChangedListener(this);
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

        drawerLayout.closeDrawer(GravityCompat.START);
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
    }
}
