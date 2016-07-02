package com.example.puneetchugh.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SongDescription extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private static String SONG_NAME = "song_name";
    private TextView songNameView;
    private TextView songDurationView;
    private int[] songsIndexArray = new int[]{R.raw.abhi_mujh_mein,R.raw.tum_hi_ho,R.raw.hai_apna_dil};
    String[] songsList = new String[]{"Tum Hi Ho", "Hai Apna Dil Toh Awara", "Abhi Mujh mein Kahin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        songNameView = (TextView) findViewById(R.id.song_name_id);
        songDurationView = (TextView) findViewById(R.id.song_duration_id);

        Intent intent = getIntent();
        int songIndex = Integer.parseInt(intent.getStringExtra(SONG_NAME));

        mediaPlayer = MediaPlayer.create(this, songsIndexArray[songIndex]);
        songNameView.setText(songsList[songIndex]);
        int seconds = mediaPlayer.getDuration()%1000;
        int minutes = seconds/60;
        seconds = seconds%60;
        songDurationView.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
    }

    public void goBack(View view){

        mediaPlayer.release();
        Intent intent = new Intent(this, MusicList.class);
        finish();
        startActivity(intent);
    }

}
