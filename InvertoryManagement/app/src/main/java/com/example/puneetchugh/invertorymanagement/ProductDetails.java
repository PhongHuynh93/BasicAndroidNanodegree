package com.example.puneetchugh.invertorymanagement;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetails extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private MySQLiteHelper mySQLiteHelper;
    private InventoryItem inventoryItem;
    private TextView productQuantityView;
    private TextView productNameView;
    private TextView productPriceView;
    private TextView productSellerView;
    private int tempQuantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        productQuantityView = (TextView) findViewById(R.id.quantity_product_details_id);
        productNameView = (TextView) findViewById(R.id.product_name_id);
        productPriceView = (TextView) findViewById(R.id.product_price_id);
        productSellerView = (TextView) findViewById(R.id.product_seller_id);

        mySQLiteHelper = new MySQLiteHelper(this);
        //sqLiteDatabase = mySQLiteHelper.getWritableDatabase();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        inventoryItem = (InventoryItem)bundle.get("inventory_item");
        tempQuantity = inventoryItem.getQuantity();
        productQuantityView.setText(Integer.toString(inventoryItem.getQuantity()));
        productSellerView.setText("Supplier : "+inventoryItem.getSupplier());
        productNameView.setText("Name : "+inventoryItem.getItemName());
        productPriceView.setText("Price($) per item : " + Integer.toString(inventoryItem.getPrice()));
    }

    public void sellPurchase(View view){

        switch (view.getId()){

            case R.id.item_sold: if(tempQuantity != 0) {
                tempQuantity--;
                productQuantityView.setText(String.valueOf(tempQuantity));
            }
                break;
            case R.id.item_purchased:
                tempQuantity++;
                productQuantityView.setText(String.valueOf(tempQuantity));
                break;

        }

        Toast.makeText(this, "Don't forget to click on Order for making changes in the inventory list", Toast.LENGTH_SHORT).show();
    }

    public void deleteProduct(View view){
        mySQLiteHelper.deleteItem(inventoryItem);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("source-activity","ProductDetails");
        //finish();
        startActivity(intent);
    }

    public void orderProduct(View view){

        int productQuantity = Integer.parseInt(productQuantityView.getText().toString());
        int id = inventoryItem.getId();

        int difference = productQuantity - inventoryItem.getQuantity();
        if(difference > 0){
            Toast.makeText(this, "Updated Inventory List. "+String.valueOf(difference)+" "+ inventoryItem.getItemName()+" sold",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Updated Inventory List. "+String.valueOf(-difference)+" "+ inventoryItem.getItemName()+" purchased",Toast.LENGTH_SHORT).show();
        }
        inventoryItem.setQuantity(productQuantity);
        mySQLiteHelper.updateItem(inventoryItem);
        //sqLiteDatabase.close();
        //mySQLiteHelper = null;
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("source-activity", "ProductDetails");
        //finish();
        startActivity(intent);
    }

    public void onDestroy() {

        super.onDestroy();
        mySQLiteHelper.close();
    }
}
