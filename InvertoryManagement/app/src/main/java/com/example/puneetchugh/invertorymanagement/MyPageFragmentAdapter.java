package com.example.puneetchugh.invertorymanagement;



import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by puneetchugh on 7/2/16.
 */
public class MyPageFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "MAIN", "INVENTORY" };
    private Context context;
    private int position;

    public MyPageFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public MyPageFragmentAdapter(FragmentManager fm, Context context, int position) {
        super(fm);
        this.context = context;
        this.position = position;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        this.position = position;

        switch (position){
            case 0: Fragment mainFragment =  MainFragment.newInstance();
                    return mainFragment;

            case 1: Fragment inventoryListFragment = InventoryListFragment.newInstance();
                    return inventoryListFragment;

            default: Fragment defaultFragment = InventoryListFragment.newInstance();
                return defaultFragment;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
