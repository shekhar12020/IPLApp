package com.shekhar.app.ipl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.adapter.TeamListAdapter;
import com.shekhar.app.ipl.model.team.Team;
import com.shekhar.app.ipl.util.DebugLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by shekhar on 17/13/17.
 */

public class TeamListFragment extends BaseFragment {

    private View rootView;

    private RecyclerView teamList;
    private RelativeLayout parentLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_team_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindViews();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ipl/teams");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Team> values = (ArrayList<Team>) dataSnapshot.getValue();

                ArrayList<Team> teams = new ArrayList<>();
                for (int i = 0; i < values.size(); i++) {
                    Map<String, String> value = (Map<String, String>) values.get(i);
                    JSONObject jsonObject = new JSONObject(value);
                    try {
                        Team team = new Team();

                        team.setTeam_id(jsonObject.get("team_id").toString());
                        team.setTeam_name(jsonObject.get("team_name").toString());
                        team.setShort_name(jsonObject.get("short_name").toString());

                        team.setOwner(jsonObject.get("owner").toString());
                        team.setCoach(jsonObject.get("coach").toString());
                        team.setCaptain(jsonObject.get("captain").toString());

                        team.setHome_ground(jsonObject.get("home_ground").toString());
                        team.setImage_url(jsonObject.get("image_url").toString());

//                        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
//                        ArrayList<String> memberList = new Gson().fromJson(jsonObject.get("team_members").toString(), listType);
//                        team.setTeam_members(memberList);

                        teams.add(team);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                teamList.setLayoutManager(llm);
                teamList.setAdapter(new TeamListAdapter(getActivity(), teams));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                DebugLog.d("Failed to read value." + error.toException());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void bindViews() {
        teamList = (RecyclerView) rootView.findViewById(R.id.teamList);
        parentLayout = (RelativeLayout) rootView.findViewById(R.id.parentLayout);
    }

}
