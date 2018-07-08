package com.serviceexchange.www.serviceexchangeandroid.guestnavigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.GuestHome;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.LoginActivity;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.ManageServices;

public class GuestNavigationDrawerActivity extends AppCompatActivity implements GuestNavigationDrawerMVP.View {

    GuestNavigationDrawerMVP.Presenter presenter;

    Toolbar toolbar;
    ActionBar actionBar;

    NavigationView navigationView;

    private static final String TAG = "GuestNavigationDrawer";
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_navigation_drawer_activity);

        presenter = new GuestNavigationDrawerPresenter(null);
        presenter.setView(this);
        toolbar = findViewById(R.id.guest_toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        drawerLayout = findViewById(R.id.guest_drawer_layout);

        navigationView = findViewById(R.id.guest_nav_view);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.guest_content_frame, new GuestHome())
                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                presenter.navigationItemClicked(item.getItemId());
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showHomeFragment() {
        Log.d(TAG, "showHomeFragment");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.guest_content_frame, new GuestHome())
                .commit();
    }

    @Override
    public void showJoinUsFragment() {
        Intent intentLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(intentLoginActivity);
        finish();
    }

    @Override
    public void showAboutFragment() {

    }

    @Override
    public void showTermOfServiceFragment() {

    }

    @Override
    public void showPolicyPrivacyFragment() {

    }

    @Override
    public void showSupportFragment() {

    }

}
