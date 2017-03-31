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
import com.shekhar.app.ipl.adapter.TeamSquadAdapter;
import com.shekhar.app.ipl.model.team.Squad;
import com.shekhar.app.ipl.model.team.Team;
import com.shekhar.app.ipl.util.DebugLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by shekhar on 17/13/17.
 */

public class TeamSquadListFragment extends BaseFragment {

    private View rootView;

    private RecyclerView squadList;
    private RelativeLayout parentLayout;
    private Team team;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_team_list, container, false);
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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ipl/team_squads/" + team.getShort_name() + "/players");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Squad> values = (ArrayList<Squad>) dataSnapshot.getValue();

                ArrayList<Squad> squads = new ArrayList<>();
                for (int i = 0; i < values.size(); i++) {
                    Map<String, String> value = (Map<String, String>) values.get(i);
                    JSONObject jsonObject = new JSONObject(value);
                    try {
                        Squad squad = new Squad();
                        squad.setpName(jsonObject.get("pName").toString().trim());
                        squad.setpType(jsonObject.get("pType").toString().trim());
                        squad.setpImg(jsonObject.get("pImg").toString().trim());

                        squad.setCountry(jsonObject.get("country").toString().trim());
                        squad.setBats(jsonObject.get("bats").toString().trim());
                        squad.setBowls(jsonObject.get("bowls").toString().trim());

                        squads.add(squad);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                squadList.setLayoutManager(llm);
                squadList.setAdapter(new TeamSquadAdapter(getActivity(), squads));
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
        squadList = (RecyclerView) rootView.findViewById(R.id.teamList);
        parentLayout = (RelativeLayout) rootView.findViewById(R.id.parentLayout);
    }

}
