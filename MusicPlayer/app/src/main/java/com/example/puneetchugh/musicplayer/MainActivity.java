package com.example.puneetchugh.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static int counter = 0;

    private Button playStop;
    private Button forward;
    private Button backward;
    private Button pause;
    private MediaPlayer mediaPlayer;
    String[] songs = new String[]{"abhi_mujh_mein","tum_hi_ho","hai_apna_dil"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mediaPlayer =  MediaPlayer.create(this, R.raw.tum_hi_ho);

        int track1 = R.raw.abhi_mujh_mein;
        int track2 = R.raw.tum_hi_ho;
        int track3 = R.raw.hai_apna_dil;




        playStop = (Button) findViewById(R.id.button4);
        forward = (Button) findViewById(R.id.button3);
        backward = (Button) findViewById(R.id.button);
        pause = (Button) findViewById(R.id.button2);

        mediaPlayer.start();
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

    public void playNextSong(View view) {

        if(counter == 2){

            Toast.makeText(this,"This is the last song. You can only go to the previous song", Toast.LENGTH_SHORT).show();
            return;
        }

        counter++;
        mediaPlayer.stop();
        if(counter == 1){
            mediaPlayer = MediaPlayer.create(this, R.raw.hai_apna_dil);
        }

        else if(counter == 2){
            mediaPlayer = MediaPlayer.create(this, R.raw.abhi_mujh_mein);
        }
        Toast.makeText(this, "Playing the next song", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
    }

    public void playOrStopSong(View view) {
        if(mediaPlayer.isPlaying()){
            playStop.setText("Play");
            mediaPlayer.stop();
        }

        else if(!mediaPlayer.isPlaying()){
            playStop.setText("Stop");
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();

        }

    }

    public void pauseSong(View view) {

        if(!mediaPlayer.isPlaying()){
            Toast.makeText(this,"Resume Playing", Toast.LENGTH_SHORT).show();
            mediaPlayer.start();
            return;
        }
        Toast.makeText(this,"Pausing the song", Toast.LENGTH_SHORT).show();
        mediaPlayer.pause();



    }

    public void playPreviousSong(View view) {
        if(counter == 0){

            Toast.makeText(this,"This is the first song. You can only go to the next song", Toast.LENGTH_SHORT).show();
            return;
        }

        counter--;
        mediaPlayer.stop();

        if(counter == 0){
            mediaPlayer = MediaPlayer.create(this, R.raw.tum_hi_ho);
        }
        else if(counter == 1){
            mediaPlayer = MediaPlayer.create(this, R.raw.hai_apna_dil);
        }


        Toast.makeText(this, "Playing the previous song", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
    }

    @Override
    public void onStop(){
        super.onStop();
        mediaPlayer.release();
    }
}
