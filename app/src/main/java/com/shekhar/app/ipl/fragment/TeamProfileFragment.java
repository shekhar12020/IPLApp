package com.shekhar.app.ipl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.model.team.Team;
import com.squareup.picasso.Picasso;

/**
 * Created by shekhar on 17/03/17.
 */

public class TeamProfileFragment extends BaseFragment {

    private View rootView;
    private RelativeLayout parentLayout;
    public TextView teamName;
    public TextView teamOwner;
    public TextView teamCaptain;
    public TextView teamCoach;
    public TextView extra;
    public ImageView teamPhoto;

    private Team team;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_team_profile, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            team = bundle.getParcelable("TeamData");
        }

        bindViews();
        if (team != null) {
            setTeamProfileData(team);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void bindViews() {

        parentLayout = (RelativeLayout) rootView.findViewById(R.id.parentLayout);

        teamName = (TextView) rootView.findViewById(R.id.teamName);
        teamOwner = (TextView) rootView.findViewById(R.id.teamOwner);
        teamCaptain = (TextView) rootView.findViewById(R.id.teamCaptain);
        teamCoach = (TextView) rootView.findViewById(R.id.teamCoach);
        extra = (TextView) rootView.findViewById(R.id.extra);
        teamPhoto = (ImageView) rootView.findViewById(R.id.teamPhoto);
    }

    private void setTeamProfileData(Team team) {

        teamName.setText(team.getTeam_name());
        teamOwner.setText(team.getOwner());
        teamCaptain.setText(team.getCaptain());
        teamCoach.setText(team.getCoach());
        extra.setText(team.getHome_ground());

        if (!team.getImage_url().equalsIgnoreCase("")) {
            Picasso.with(getActivity())
                    .load(team.getImage_url())
                    .placeholder(R.drawable.loading_border)
                    .error(R.drawable.loading_border)
                    .into(teamPhoto);
        } else {
            Picasso.with(getActivity())
                    .load(R.drawable.loading_border)
                    .placeholder(R.drawable.loading_border)
                    .error(R.drawable.loading_border)
                    .into(teamPhoto);
        }
    }

}
