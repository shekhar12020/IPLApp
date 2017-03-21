package com.shekhar.app.ipl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shekhar.app.ipl.R;

/**
 * Created by shekhar on 17/13/17.
 */

public class HomeFragment extends BaseFragment {

    private View rootView;
    private RelativeLayout parentLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, container, false);
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
        parentLayout = (RelativeLayout) rootView.findViewById(R.id.parentLayout);
    }

}
