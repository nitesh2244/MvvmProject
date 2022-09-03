package com.example.mvvmdemo.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwipePagerAdapter extends FragmentStatePagerAdapter {

    //Class variables
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public SwipePagerAdapter(FragmentManager manager) {
        super(manager);
    }

    /**
     * Method used to get item position.
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * Method used to get list item count.
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * Method used to add fragment.
     */
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}