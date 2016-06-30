package com.example.puneetchugh.nyctourapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<TouristAttractions> touristAttractionsArrayList;

    private CustomPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        touristAttractionsArrayList = new LinkedList<>();
        touristAttractionsArrayList.add(new TouristAttractions(getResources().getString(R.string.statue_of_liberty), getResources().getString(R.string.statue_of_liberty_loc), getResources().getString(R.string.statue_of_liberty_timings) , getResources().getString(R.string.statue_of_liberty_description), R.drawable.nyc_statue_of_liberty));
        touristAttractionsArrayList.add(new TouristAttractions(getResources().getString(R.string.brooklyn_bridge), getResources().getString(R.string.brooklyn_bridge_loc), getResources().getString(R.string.brooklyn_bridge_timings), getResources().getString(R.string.brooklyn_bridge_description), R.drawable.nyc_brooklyn_bridge));
        touristAttractionsArrayList.add(new TouristAttractions(getResources().getString(R.string.times_square), getResources().getString(R.string.times_square_loc), getResources().getString(R.string.times_square_timings), getResources().getString(R.string.times_square_description), R.drawable.nyc_times_square));
        touristAttractionsArrayList.add(new TouristAttractions(getResources().getString(R.string.empire_state), getResources().getString(R.string.empire_state_loc), getResources().getString(R.string.empire_state_timings), getResources().getString(R.string.empire_state_description), R.drawable.nyc_empire_state));
        touristAttractionsArrayList.add(new TouristAttractions(getResources().getString(R.string.madam_tussauds), getResources().getString(R.string.madam_tussauds_loc), getResources().getString(R.string.madam_tussauds_timings), getResources().getString(R.string.madam_tussauds_description), R.drawable.nyc_madam_tussauds));
        touristAttractionsArrayList.add(new TouristAttractions(getResources().getString(R.string.central_park), getResources().getString(R.string.central_park_loc), getResources().getString(R.string.central_park_timings), getResources().getString(R.string.central_park_description), R.drawable.nyc_central_park));


        mCustomPagerAdapter = new CustomPagerAdapter(this, touristAttractionsArrayList);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
