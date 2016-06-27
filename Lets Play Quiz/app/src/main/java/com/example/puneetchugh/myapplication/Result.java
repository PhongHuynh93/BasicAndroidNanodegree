package com.example.puneetchugh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String score = intent.getExtras().getString("score");

        resultTextView = (TextView) findViewById(R.id.result_id);

        resultTextView.setText("You got "+String.valueOf(score)+" out of 7");
    }
}
