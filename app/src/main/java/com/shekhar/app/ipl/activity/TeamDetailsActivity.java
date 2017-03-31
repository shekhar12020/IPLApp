package com.shekhar.app.ipl.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.adapter.TabAdapter;
import com.shekhar.app.ipl.fragment.TeamMatchListFragment;
import com.shekhar.app.ipl.fragment.TeamProfileFragment;
import com.shekhar.app.ipl.fragment.TeamSquadListFragment;
import com.shekhar.app.ipl.model.team.Team;

public class TeamDetailsActivity extends BaseActivity {

    private Team team;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent() != null && getIntent().getExtras() != null) {
            team = getIntent().getParcelableExtra("teamDetails");
            getSupportActionBar().setTitle(team.getTeam_name().trim());
        } else {
            getSupportActionBar().setTitle("Team Details");
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setupViewPager();
    }

    @Override
    protected int getStatusBarColor() {
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                supportFinishAfterTransition();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager() {

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), this);
        tabAdapter.addFragment(new TeamProfileFragment(), "Profile", team);
        tabAdapter.addFragment(new TeamSquadListFragment(), "Squad",team);
        tabAdapter.addFragment(new TeamMatchListFragment(), "Matches",team);

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
