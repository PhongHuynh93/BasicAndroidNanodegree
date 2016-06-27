package com.example.puneetchugh.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText sachinTendulkar;
    private EditText yuvrajSingh;
    private EditText steveSmith;
    private RadioButton trueAnswer;
    private RadioButton trueAnswer1;
    private RadioButton falseAnswer;
    private RadioButton falseAnswer1;

    private CheckBox checkFirst, checkFirst1;
    private CheckBox checkSecond, checkSecond1;
    private CheckBox checkThird, checkThird1;
    private CheckBox checkFourth, checkFourth1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sachinTendulkar = (EditText)findViewById(R.id.answer_sachin_tendulkar_id);
        yuvrajSingh = (EditText) findViewById(R.id.answer_yuvraj_singh);
        steveSmith = (EditText) findViewById(R.id.answer_steve_smith_id);
        trueAnswer = (RadioButton)findViewById(R.id.true_id);
        trueAnswer1 = (RadioButton)findViewById(R.id.true_id_1);
        falseAnswer = (RadioButton)findViewById(R.id.false_id);
        falseAnswer1 = (RadioButton) findViewById(R.id.false_id_1);
        checkFirst = (CheckBox) findViewById(R.id.check_first_id);
        checkFirst1 = (CheckBox) findViewById(R.id.check_first_id_1);
        checkSecond = (CheckBox) findViewById(R.id.check_second_id);
        checkSecond1 = (CheckBox)findViewById(R.id.check_second_id_1);
        checkThird = (CheckBox) findViewById(R.id.check_third_id);
        checkThird1 = (CheckBox) findViewById(R.id.check_third_id_1);
        checkFourth = (CheckBox) findViewById(R.id.check_fourth_id);
        checkFourth1 = (CheckBox) findViewById(R.id.check_fourth_id_1);

        checkFirst.setText("India");
        checkSecond.setText("Sri Lanka");
        checkThird.setText("Bangladesh");
        checkFourth.setText("West Indies");

        checkFirst1.setText("Irfan Pathan");
        checkSecond1.setText("Zaheer Khan");
        checkThird1.setText("Malinga");
        checkFourth1.setText("Charles Lengevelt");

        trueAnswer1.setText("True");
        trueAnswer.setText("True");

        falseAnswer.setText("False");
        falseAnswer1.setText("False");
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

    public void trueFalse(View view){

        switch (view.getId()){
            case R.id.true_id: if(falseAnswer.isChecked()){
                                    falseAnswer.toggle();
                                }
                                break;

            case R.id.true_id_1:if(falseAnswer1.isChecked()) {
                                falseAnswer1.toggle();
                                }
                                break;

            case R.id.false_id: if(trueAnswer.isChecked()) {
                                trueAnswer.toggle();
                                }
                                break;

            case R.id.false_id_1: if(trueAnswer1.isChecked()) {
                                  trueAnswer1.toggle();
                                }
                                break;
        }
    }

    public void answersSubmitted(View view){

        int score = 0;

        if(sachinTendulkar.getText().toString().toLowerCase().matches("sachin tendulkar") ){
            score++;
        }

        if(yuvrajSingh.getText().toString().toLowerCase().matches("yuvraj singh")){
            score++;
        }

        if(steveSmith.getText().toString().toLowerCase().matches("steve smith")){
            score++;
        }

        if(trueAnswer.isChecked()){
            score++;
        }

        if(falseAnswer1.isChecked()){
            score++;
        }

        if(checkFirst.isChecked() && checkThird.isChecked() && checkFourth.isChecked()){
            score++;
        }

        if(checkThird1.isChecked()){
            score++;
        }


        Intent intent = new Intent(this, Result.class);
        intent.putExtra("score", String.valueOf(score));
        startActivity(intent);
    }


}
