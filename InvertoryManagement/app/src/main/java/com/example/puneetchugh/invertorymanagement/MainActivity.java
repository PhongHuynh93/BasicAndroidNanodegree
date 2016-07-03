package com.example.puneetchugh.invertorymanagement;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase database;
    private EditText productName;
    private EditText productQuantity;
    private EditText productSeller;
    private EditText productPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mySQLiteHelper = new MySQLiteHelper(this);
        //database = mySQLiteHelper.getWritableDatabase();
        //mySQLiteHelper.insert("Notepad", 10, "WestField Traders",50);
        //mySQLiteHelper.insert("Pen",20,"RightWing Traders",60);
        //mySQLiteHelper.insert("Lighter",5,"ThinkLogic Traders",100);
        //mySQLiteHelper.insert("Highlighter", 12, "TwoBrothers traders", 20);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyPageFragmentAdapter pagerAdapter =
                new MyPageFragmentAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);



        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //tab.setCustomView(pagerAdapter.getTabView(i));
        }

    }
    @Override
    public void onDestroy() {

        super.onDestroy();
        mySQLiteHelper.close();

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

    public void orderQuantity(View view){

        productName = (EditText) findViewById(R.id.order_name_id);
        productQuantity = (EditText) findViewById(R.id.order_quanity_id);
        productSeller = (EditText) findViewById(R.id.order_seller_id);
        productPrice = (EditText) findViewById(R.id.order_price_id);

        String productNameString = productName.getText().toString().trim();
        int productQuantityNumber = Integer.parseInt(productQuantity.getText().toString().trim());
        String productSellerString = productSeller.getText().toString().trim();
        int productPriceNumber = Integer.parseInt(productPrice.getText().toString().trim());;
        mySQLiteHelper.insert(productNameString, productQuantityNumber,productSellerString, productPriceNumber);

        productName.setText("");
        productQuantity.setText("");
        productSeller.setText("");
        productPrice.setText("");

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyPageFragmentAdapter pagerAdapter =
                new MyPageFragmentAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
