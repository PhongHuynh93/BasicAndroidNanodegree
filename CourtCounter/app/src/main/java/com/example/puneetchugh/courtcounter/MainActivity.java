package com.example.puneetchugh.courtcounter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int teamAScore = 0;
    private static int teamBScore = 0;

    private TextView textViewScoreA;
    private TextView textViewScoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewScoreA = (TextView) findViewById(R.id.textView2);
        textViewScoreB = (TextView) findViewById(R.id.textView4);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("TEAM_A_SCORE", teamAScore);
        outState.putInt("TEAM_B_SCORE", teamBScore);

    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        teamAScore = state.getInt("TEAM_A_SCORE", teamAScore);
        teamBScore = state.getInt("TEAM_B_SCORE", teamBScore);

        setContentView(R.layout.activity_main);
        textViewScoreA = (TextView) findViewById(R.id.textView2);
        textViewScoreB = (TextView) findViewById(R.id.textView4);

        textViewScoreB.setText(String.valueOf(teamBScore));
        textViewScoreA.setText(String.valueOf(teamAScore));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    public void scorechange(View view){

        switch (view.getId()){

            case R.id.button: teamAScore = teamAScore + 1;
                              textViewScoreA.setText(String.valueOf(teamAScore));
                              break;
            case R.id.button2: teamAScore = teamAScore + 2;
                               textViewScoreA.setText(String.valueOf(teamAScore));
                               break;
            case R.id.button3: teamAScore = teamAScore + 3;
                               textViewScoreA.setText(String.valueOf(teamAScore));
                               break;
            case R.id.button4: teamBScore = teamBScore + 1;
                               textViewScoreB.setText(String.valueOf(teamBScore));
                               break;
            case R.id.button5: teamBScore = teamBScore + 2;
                               textViewScoreB.setText(String.valueOf(teamBScore));
                               break;
            case R.id.button6: teamBScore = teamBScore + 3;
                               textViewScoreB.setText(String.valueOf(teamBScore));
                               break;

        }
    }

    public void resetScore(View view){
        teamAScore = 0;
        teamBScore = 0;
        textViewScoreB.setText(String.valueOf(teamBScore));
        textViewScoreA.setText(String.valueOf(teamAScore));
    }

}
