package com.example.puneetchugh.reportcard;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private ArrayList<ReportCardClass> reportCardList;
    private String[] names = new String[]{"Puneet", "Chugh", "Jyoti", "Kaku"};
    private Context context;
    //private Class<OnePerson> onePersonClass = OnePerson.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = (ListView)findViewById(R.id.list_view);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        context = this;
        listView = (ListView)findViewById(R.id.list_view);

        reportCardList = new ArrayList<ReportCardClass>();

        reportCardList.add(0, new ReportCardClass("Puneet", 90, 80,79));
        reportCardList.add(1, new ReportCardClass("Chugh"));
        reportCardList.add(2, new ReportCardClass("Jyoti", 95,98,99));
        reportCardList.add(3, new ReportCardClass("Kaku", 99, 60,50));

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, names);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);
                Intent intent = new Intent(context, OnePerson.class);
                ReportCardClass reportCardClass = reportCardList.get(position);

                intent.putExtra("ONE_PERSON", reportCardClass);
                startActivity(intent);
            }
        });

        listView.setAdapter(arrayAdapter);

    }

    /*
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Intent intent = new Intent(this, OnePerson.class);
        intent.putExtra("ONE_PERSON", (Parcelable) reportCardList.get(position));
        startActivity(intent);
    }
*/
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
