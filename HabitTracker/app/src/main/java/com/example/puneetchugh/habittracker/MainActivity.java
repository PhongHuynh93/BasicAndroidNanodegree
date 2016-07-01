package com.example.puneetchugh.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    MySQLiteHelper mySQLiteHelper;

    SQLiteDatabase db;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        mySQLiteHelper = new MySQLiteHelper(context);
        db = mySQLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.HabitsLeft.COLUMN_HABIT, "Smoking");
        values.put(Contract.HabitsLeft.COLUMN_DATE_LEFT, "04-06-2016");
        db.insert(Contract.TABLE_HABITS_LEFT, null, values);
        ContentValues values2 = new ContentValues();
        values.put(Contract.HabitsLeft.COLUMN_HABIT, "Drinking");
        values.put(Contract.HabitsLeft.COLUMN_DATE_LEFT, "08-06-2016");
        db.insert(Contract.TABLE_HABITS_LEFT, null, values);
        db.delete(Contract.TABLE_HABITS_LEFT, Contract.HabitsLeft.COLUMN_HABIT + " = ?", new String[]{"Smoking"});
        values.put(Contract.HabitsLeft.COLUMN_HABIT, "Smoking");
        values.put(Contract.HabitsLeft.COLUMN_DATE_LEFT, "06-16-2016");
        db.update(Contract.TABLE_HABITS_LEFT, values, Contract.HabitsLeft.COLUMN_HABIT + " = ?", new String[]{"Drinking"});
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
