package com.serviceexchange.www.serviceexchangeandroid.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.linkedin.platform.LISessionManager;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;

import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.earning.EarningFragment;
import com.serviceexchange.www.serviceexchangeandroid.homeStatistic.StatisticFragment;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.GuestHome;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments.RequestedServicesFragment;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.LoginActivity;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.ManageServices;
import com.serviceexchange.www.serviceexchangeandroid.myService.MyServiceFragment;

import com.serviceexchange.www.serviceexchangeandroid.inbox.InboxFragment;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.MyRequestsFragment;
import com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices.OfferReceivedFragment;
import com.serviceexchange.www.serviceexchangeandroid.profile.ProfileActivity;
import com.serviceexchange.www.serviceexchangeandroid.profile.ProfileFragment;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.RequestsFragment;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.offerdetailfragment.OfferDetailActivity;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.splashactivity.SplashActivity;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Inject;


public class NavigationDrawerActivity extends AppCompatActivity implements NavigationDrawerMVP.View {

    @Inject
    NavigationDrawerMVP.Presenter presenter;
    @Inject
    Context context;

    Toolbar toolbar;
    ActionBar actionBar;
    private ImageView profileImage;
    private TextView userName;
    private UserDTO user;

    NavigationView navigationView;

    SwitchCompat switchCompat;

    private SharedPreferencesModel sharedPreferencesModel;

    private static final String TAG = "NavigationDrawer";
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_activity);

        ((App) getApplication()).getComponent().inject(this);
        presenter.setView(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);

        sharedPreferencesModel = SharedPreferencesModel.getInstance(context);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_frame, new StatisticFragment())
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

        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.OnlineStatus);
        View switchView = MenuItemCompat.getActionView(menuItem);

        switchCompat = switchView.findViewById(R.id.switcher);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.statusSwitchClicked(isChecked);
            }
        });

        user = presenter.getCurrentUser();
        View headerView = navigationView.getHeaderView(0);
        profileImage = headerView.findViewById(R.id.profileImageView);
        userName = headerView.findViewById(R.id.profileUserName);
        if (!(user.getImage().isEmpty()))
            Picasso.with(context).load(user.getImage()).into(profileImage);
        userName.setText(user.getName());
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
    public void showMyStatsFragment() {
        StatisticFragment statisticFragment = new StatisticFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, statisticFragment)
                .commit();
    }

    @Override
    public void showHomeFragment() {
        Log.d(TAG, "showHomeFragment");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new GuestHome())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = presenter.getCurrentUser();
        userName.setText(user.getName());
        Picasso.with(context).load(user.getImage()).into(profileImage);
        SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
                SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                          String key) {
                        user = presenter.getCurrentUser();
                        userName.setText(user.getName());
                        Picasso.with(context).load(user.getImage()).into(profileImage);
                    }
                };
    }

    @Override
    public void showInboxFragment() {
        Log.d(TAG, "showInboxFragment");
        InboxFragment inboxFragment = new InboxFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, inboxFragment)
                .commit();
    }

    @Override
    public void showNotificationFragment() {
        Log.d(TAG, "showNotificationFragemnt");
        // TODO notification fragment
    }

    @Override
    public void showMyRequestsFragment() {
        Log.d(TAG, "showMyRequestsFragment");
        MyRequestsFragment myRequestsFragment = new MyRequestsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, myRequestsFragment)
                .commit();
    }

    @Override
    public void showManageServiceFragment() {
        Log.d(TAG, "showManageServiceFragment");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new ManageServices())
                .commit();
    }

    @Override
    public void showEarningFragment() {
        EarningFragment earningFragment = new EarningFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, earningFragment)
                .commit();
    }

    @Override
    public void showMyServiceFragment() {
        MyServiceFragment myServiceFragment = new MyServiceFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, myServiceFragment)
                .commit();
    }

    @Override
    public void showShowServiceRequestsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new RequestsFragment())
                .commit();
    }

    @Override
    public void showShareServiceFragment() {
        OfferReceivedFragment offerReceivedFragment = new OfferReceivedFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, offerReceivedFragment)
                .commit();
    }

    @Override
    public void showProfileFragment() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void showLogoutFragment() {
        //delete all shared shared_prefs
        File sharedPreferenceFile = new File("/data/data/" + getPackageName() + "/shared_prefs/");
        File[] listFiles = sharedPreferenceFile.listFiles();
        for (File file : listFiles) {
            file.delete();
        }

        if (sharedPreferencesModel.getCurrentUserFromSharedPreferences().getAccountType().equals("faceBook")) {
            //logout from facebook
            LoginManager.getInstance().logOut();
        } else {
            //logout from linkedin
            LISessionManager.getInstance(getApplicationContext()).clearSession();
        }

        Intent intentLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(intentLoginActivity);
        finish();
    }


    @Override
    public void onlineStatusAction(boolean isChecked) {
        Toast.makeText(NavigationDrawerActivity.this, "Switcher is:" + isChecked, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
