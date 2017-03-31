package com.shekhar.app.ipl.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.adapter.TabAdapter;
import com.shekhar.app.ipl.fragment.HomeFragment;
import com.shekhar.app.ipl.fragment.MatchScheduleFragment;
import com.shekhar.app.ipl.fragment.TeamListFragment;
import com.shekhar.app.ipl.global.GlobalData;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class LandingActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CircleImageView profilePhoto;
    private TextView profileName;
    private TextView profileEmail;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("configuration");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String appTitle = "";
                String statusText = "";
                boolean statusDisplay;

                Map<String, String> value = (Map<String, String>) dataSnapshot.getValue();
                JSONObject jsonObject = new JSONObject(value);
                try {
                    appTitle = jsonObject.getString("home_screen_title");
                    statusText = jsonObject.getString("status_text");
                    statusDisplay = jsonObject.getBoolean("status_display");

                    Intent i = new Intent("updateStatus");
                    i.putExtra("status_display", statusDisplay);
                    i.putExtra("status_text", statusText);
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                getSupportActionBar().setTitle(getString(R.string.app_name));
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setupViewPager();
        loadUserData();

        profilePhoto = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.profilePhoto);
        profileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profileName);
        profileEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profileEmail);

        setNavProfileInfo();
    }

    public void loadUserData() {

        SharedPreferences UserInfo = this.getSharedPreferences("UserInfo", MODE_PRIVATE);
        GlobalData.getInstance().setUserId(UserInfo.getString("id", ""));
        GlobalData.getInstance().setProfileName(UserInfo.getString("username", ""));
        GlobalData.getInstance().setProfilePhoto(UserInfo.getString("photoUrl", ""));
        GlobalData.getInstance().setProfileEmail(UserInfo.getString("email", ""));

    }

    private void setNavProfileInfo() {
        if (GlobalData.getInstance().getProfilePhoto() != null
                && !GlobalData.getInstance().getProfilePhoto().equalsIgnoreCase("")) {
            Picasso.with(LandingActivity.this)
                    .load(GlobalData.getInstance().getProfilePhoto())
                    .noFade()
                    .into(profilePhoto);
        }
        profileName.setText(GlobalData.getInstance().getProfileName());
        profileEmail.setText(GlobalData.getInstance().getProfileEmail());
    }


    @Override
    protected int getStatusBarColor() {
        return 0;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.landing_acivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent intent = null;
        int id = item.getItemId();

        if (id == R.id.nav_match_result) {

        } else if (id == R.id.nav_team) {

        } else if (id == R.id.nav_point_table) {

        } else if (id == R.id.nav_statistics) {
            intent = new Intent(LandingActivity.this, NewFeedsActivity.class);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_rate_this_app) {

        }

        if (intent != null) {
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager() {

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), this);
        tabAdapter.addFragment(new HomeFragment(), "Home");
        tabAdapter.addFragment(new MatchScheduleFragment(), "Schedules");
        tabAdapter.addFragment(new TeamListFragment(), "Teams");

        viewPager.setAdapter(tabAdapter);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
