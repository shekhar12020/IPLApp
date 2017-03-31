package com.shekhar.app.ipl.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shekhar.app.ipl.fragment.TeamProfileFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shekhar on 17/03/2017.
 */
public class TabAdapter<T> extends FragmentPagerAdapter {
    private Context mContext;
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public TabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    public void addFragment(Fragment fragment, String title) {
        if (fragment instanceof TeamProfileFragment) {
            Bundle bundle = new Bundle();
            bundle.putString("TestMessage", "TestMessage");
            fragment.setArguments(bundle);
        }
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    public void addFragment(Fragment fragment, String title, T bundleData) {
        if (bundleData != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("TeamData", (Parcelable) bundleData);
            fragment.setArguments(bundle);
        }
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

}