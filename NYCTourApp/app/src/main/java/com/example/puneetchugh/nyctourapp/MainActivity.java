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
        touristAttractionsArrayList.add(new TouristAttractions("Statue Of Liberty", "New York, NY", "8am - 8pm", "Statue of liberty has a historical importance", R.drawable.nyc_statue_of_liberty));
        touristAttractionsArrayList.add(new TouristAttractions("Brooklyn Bridge", "Brooklyn, NY", "24 hours", "Statue of liberty has a historical importance", R.drawable.nyc_brooklyn_bridge));
        touristAttractionsArrayList.add(new TouristAttractions("Times Squre", "42nd Street, 6th Avenue, NY", "24 hours", "Times square has all the bright advertisements on tall buildings", R.drawable.nyc_times_square));
        touristAttractionsArrayList.add(new TouristAttractions("Empire State", "30th Street, NY", "8am - 9pm", "One of the tallest buildings of New York that keeps changing the color of its top portion", R.drawable.nyc_empire_state));
        touristAttractionsArrayList.add(new TouristAttractions("Madam Tussauds", "50th Street, NY", "11am - 9pm", "Madam Tussauds has wax statues of world celebrities", R.drawable.nyc_madam_tussauds));
        touristAttractionsArrayList.add(new TouristAttractions("Central Park", "New York, NY", "5am - 10pm", "Huge Park with various tourist attraction in the heart of Manhattan", R.drawable.nyc_central_park));


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
