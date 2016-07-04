package com.example.puneetchugh.invertorymanagement;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase database;
    private EditText productName;
    private EditText productQuantity;
    private EditText productSeller;
    private EditText productPrice;
    private String imageName = new String();
    private Bitmap bitmapImage;
    private byte[] bytesArrayImage = new byte[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mySQLiteHelper = new MySQLiteHelper(this);

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

        if(productName.getText().toString().matches("") || productSeller.toString().matches("") ||
                productQuantity.toString().matches("") || productPrice.toString().matches("") || bitmapImage == null){
            Toast.makeText(this,"You can't have a null product name or no image", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            String productNameString = productName.getText().toString().trim();
            int productQuantityNumber = Integer.parseInt(productQuantity.getText().toString().trim());
            String productSellerString = productSeller.getText().toString().trim();
            Boolean validEmail = emailValidator(productSellerString);
            if(validEmail == false){
                Exception e = new Exception();
                throw e;
            }
            int productPriceNumber = Integer.parseInt(productPrice.getText().toString().trim());
            byte[] photoByte = getBytes(bitmapImage);
            mySQLiteHelper.insert(productNameString, productQuantityNumber,productSellerString, productPriceNumber, photoByte);
            bitmapImage = null;
        }catch (NumberFormatException nFE){
            Toast.makeText(this, "You cannot enter a non-number value for quantity or price", Toast.LENGTH_SHORT).show();
            return;
        }catch (Exception e){
            Toast.makeText(this, "Not a valid email id", Toast.LENGTH_SHORT).show();
        }

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

    public void clickImage(View view){

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            bitmapImage = (Bitmap) data.getExtras().get("data");
        }
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
