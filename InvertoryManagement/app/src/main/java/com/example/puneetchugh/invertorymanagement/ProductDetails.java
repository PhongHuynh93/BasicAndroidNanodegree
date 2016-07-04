package com.example.puneetchugh.invertorymanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView productImageView;
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
        productImageView = (ImageView) findViewById(R.id.product_image_id);
        mySQLiteHelper = new MySQLiteHelper(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        inventoryItem = (InventoryItem)bundle.get("inventory_item");
        tempQuantity = inventoryItem.getQuantity();
        productQuantityView.setText(Integer.toString(inventoryItem.getQuantity()));
        productSellerView.setText("Supplier : "+inventoryItem.getSupplier());
        productNameView.setText("Name : "+inventoryItem.getItemName());
        productPriceView.setText("Price($) per item : " + Integer.toString(inventoryItem.getPrice()));
        Bitmap bitmap = BitmapFactory.decodeByteArray(inventoryItem.getPhoto(), 0, inventoryItem.getPhoto().length);

        productImageView.setImageBitmap(bitmap);
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
        inventoryItem.setQuantity(tempQuantity);
        mySQLiteHelper.updateItem(inventoryItem);


        //Toast.makeText(this, "Don't forget to click on Order for making changes in the inventory list", Toast.LENGTH_SHORT).show();
    }

    public void deleteProduct(final View view){

        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
        alertDialog.setTitle("Delete Product Confirmation");
        alertDialog.setMessage("Are you sure you want to delete " + inventoryItem.getItemName() + " ?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mySQLiteHelper.deleteItem(inventoryItem);
                        dialog.dismiss();
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        intent.putExtra("source-activity", "ProductDetails");
                        startActivity(intent);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
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
        //inventoryItem.setQuantity(productQuantity);
        //mySQLiteHelper.updateItem(inventoryItem);

        //Intent launchGoogleChrome = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        //launchGoogleChrome.putExtra("browser_fallback_url", inventoryItem.getItemName());
        //startActivity(launchGoogleChrome);

        String subject = "Order stock for "+inventoryItem.getItemName();
        String message = "We would like to place an order of 10 "+inventoryItem.getItemName();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        //intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{inventoryItem.getSupplier()});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        Intent mailer = Intent.createChooser(intent, null);
        startActivity(mailer);

    }

    public void onDestroy() {

        super.onDestroy();
        mySQLiteHelper.close();
    }
}
