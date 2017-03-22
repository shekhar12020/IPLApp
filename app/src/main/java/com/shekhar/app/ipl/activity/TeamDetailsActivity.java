package com.shekhar.app.ipl.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.model.match.Team;

public class TeamDetailsActivity extends AppCompatActivity {

    private Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        if (getIntent() != null && getIntent().getExtras() != null) {
            team = getIntent().getParcelableExtra("teamDetails");
        }
    }
}
