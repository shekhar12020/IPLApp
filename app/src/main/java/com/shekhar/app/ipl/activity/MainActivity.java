package com.shekhar.app.ipl.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("configuration");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String appTitle = "";

                Map<String, String> value = (Map<String, String>) dataSnapshot.getValue();
                JSONObject jsonObject = new JSONObject(value);
                try {
                    appTitle = jsonObject.getString("home_screen_title");
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
    }

    @Override
    protected int getStatusBarColor() {
        return 0;
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
