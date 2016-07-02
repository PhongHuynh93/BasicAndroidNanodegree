package com.example.puneetchugh.musicplayer;

/**
 * Created by puneetchugh on 7/1/16.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MusicList extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> listAdapter;
    String[] songsList = new String[]{"Tum Hi Ho", "Hai Apna Dil Toh Awara", "Abhi Mujh mein Kahin"};
    Context context;
    static String SONG_NAME = "song_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, songsList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent songDescriptionIntent= new Intent(context,SongDescription.class);
                songDescriptionIntent.putExtra(SONG_NAME, Integer.toString(position));
                startActivity(songDescriptionIntent);
            }
        });
    }

    public void moveToPlaying(View View){

        Intent intent = new Intent(this, MusicPlaying.class);
        finish();
        startActivity(intent);
    }

}

