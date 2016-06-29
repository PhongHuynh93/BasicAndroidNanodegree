package com.example.puneetchugh.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> listAdapter;
    String[] songsList = new String[]{"Tum Hi Ho", "Hai Apna Dil Toh Awara", "Abhi Mujh mein Kahin"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, songsList);
        listView.setAdapter(listAdapter);

    }

    public void moveToPlaying(View View){

        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

}
