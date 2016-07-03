package com.example.puneetchugh.datastoragesqlite;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by puneetchugh on 7/2/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence[] titles;
    private int numberOfTabs;

    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int numberOfTabs) {
        super(fm);

        this.titles = mTitles;
        this.numberOfTabs = numberOfTabs;

    }
    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            MainFragment tab1 = new MainFragment();
            return tab1;

        }
        else if(position == 1){
            InventoryListFragment tab2 = new InventoryListFragment();
            return tab2;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return 0;
    }
}

