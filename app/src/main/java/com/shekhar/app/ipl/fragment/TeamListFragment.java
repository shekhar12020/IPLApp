package com.shekhar.app.ipl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shekhar.app.ipl.R;

/**
 * Created by shekhar on 17/13/17.
 */

public class TeamListFragment extends BaseFragment {

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
