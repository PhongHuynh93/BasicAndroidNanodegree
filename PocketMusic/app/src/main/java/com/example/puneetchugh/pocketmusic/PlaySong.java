package com.example.puneetchugh.pocketmusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PlaySong extends AppCompatActivity {

    private Button playStop;
    private Button forward;
    private Button backward;
    private Button pause;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mediaPlayer =  MediaPlayer.create(this, R.raw.PowerClubMix );
        playStop = (Button) findViewById(R.id.button4);
        forward = (Button) findViewById(R.id.button3);
        backward = (Button) findViewById(R.id.button);
        pause = (Button) findViewById(R.id.button2);
        



    }

    public void playNextSong(View view) {

        Toast.makeText(this,"Playing the next song", Toast.LENGTH_SHORT).show();
        int songPosition = mediaPlayer.getCurrentPosition();
        mediaPlayer.seekTo(songPosition + 1);
    }

    public void playOrStopSong(View view) {
        if(mediaPlayer.isPlaying()){
            playStop.setText("Play");
            mediaPlayer.stop();
        }

        if(!mediaPlayer.isPlaying()){
            playStop.setText("Stop");
            mediaPlayer.reset();
            mediaPlayer.start();
        }

    }

    public void pauseSong(View view) {

        if(!mediaPlayer.isPlaying()){
            Toast.makeText(this,"Resume Playing", Toast.LENGTH_SHORT).show();
            mediaPlayer.start();
        }
        Toast.makeText(this,"Pausing the song", Toast.LENGTH_SHORT).show();
        mediaPlayer.pause();

    }

    public void playPreviousSong(View view) {
        Toast.makeText(this,"Playing the previous song", Toast.LENGTH_SHORT).show();
        int songPosition = mediaPlayer.getCurrentPosition();
        mediaPlayer.seekTo(songPosition - 1);
    }

    @Override
    public void onStop(){
        super.onStop();
        mediaPlayer.release();
    }
}
