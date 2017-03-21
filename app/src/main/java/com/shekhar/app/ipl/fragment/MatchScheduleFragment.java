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
import com.shekhar.app.ipl.adapter.ScheduledMatchListAdapter;
import com.shekhar.app.ipl.model.match.ScheduledMatch;
import com.shekhar.app.ipl.util.DebugLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by shekhar on 17/03/17.
 */

public class MatchScheduleFragment extends BaseFragment {

    private View rootView;

    private RecyclerView matchScheduleList;
    private RelativeLayout parentLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_match_schedule, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindViews();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("matches");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<ScheduledMatch> values = (ArrayList<ScheduledMatch>) dataSnapshot.getValue();

                ArrayList<ScheduledMatch> scheduledMatches = new ArrayList<>();
                for (int i = 0; i < values.size(); i++) {
                    Map<String, String> value = (Map<String, String>) values.get(i);
                    JSONObject jsonObject = new JSONObject(value);
                    try {
                        ScheduledMatch scheduledMatch = new ScheduledMatch();
                        scheduledMatch.setTime(jsonObject.get("time").toString());
                        scheduledMatch.setDate(jsonObject.get("date").toString());
                        scheduledMatch.setMatch(jsonObject.get("match").toString());
                        scheduledMatch.setVanue(jsonObject.get("venue").toString());
                        scheduledMatches.add(scheduledMatch);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                matchScheduleList.setLayoutManager(llm);
                matchScheduleList.setAdapter(new ScheduledMatchListAdapter(getActivity(), scheduledMatches));
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
        matchScheduleList = (RecyclerView) rootView.findViewById(R.id.matchScheduleList);
        parentLayout = (RelativeLayout) rootView.findViewById(R.id.parentLayout);
    }

}
