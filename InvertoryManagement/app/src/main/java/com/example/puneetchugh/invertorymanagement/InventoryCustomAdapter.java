package com.example.puneetchugh.invertorymanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by puneetchugh on 7/2/16.
 */
public class InventoryCustomAdapter extends BaseAdapter {

    private TextView listProductView;
    private TextView listProductPrice;
    private TextView listProductQuantity;
    private TextView listProductSeller;
    private ImageView imageView;
    private ArrayList<InventoryItem> inventoryItemArrayList;
    private Context context;
    private MySQLiteHelper mySQLiteHelper;

    public InventoryCustomAdapter(ArrayList<InventoryItem> inventoryItemArrayList, Context context){

        this.inventoryItemArrayList = inventoryItemArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {

        return inventoryItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return inventoryItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        imageView = (ImageView) convertView.findViewById(R.id.image);
        listProductView = (TextView) convertView.findViewById(R.id.product);
        listProductQuantity = (TextView) convertView.findViewById(R.id.quantity);
        listProductPrice = (TextView) convertView.findViewById(R.id.price);
        listProductSeller = (TextView) convertView.findViewById(R.id.seller);

        Bitmap bitmap = BitmapFactory.decodeByteArray(inventoryItemArrayList.get(position).getPhoto() , 0, inventoryItemArrayList.get(position).getPhoto().length);
        imageView.setImageBitmap(bitmap);
        listProductView.setText("Product : "+inventoryItemArrayList.get(position).getItemName());
        listProductPrice.setText("Price($) per unit : "+Integer.toString(inventoryItemArrayList.get(position).getPrice()));
        listProductSeller.setText("Seller : "+inventoryItemArrayList.get(position).getSupplier());
        listProductQuantity.setText("Quantity " + Integer.toString(inventoryItemArrayList.get(position).getQuantity()));

        Button detailButton = (Button) convertView.findViewById(R.id.detail);
        detailButton.setClickable(true);
        detailButton.setFocusable(true);
        detailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View parent) {
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("inventory_item", inventoryItemArrayList.get(position));
                context.startActivity(intent);
            }
        });

        Button sellButton = (Button) convertView.findViewById(R.id.sell);
        sellButton.setClickable(true);
        sellButton.setFocusable(true);
        sellButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View parent) {
                InventoryItem inventoryItem = inventoryItemArrayList.get(position);
                mySQLiteHelper = new MySQLiteHelper(context);
                int temQuantity = inventoryItem.getQuantity();
                if (temQuantity != 0) {
                    temQuantity--;
                }
                listProductQuantity.setText("Quantity " + Integer.toString(inventoryItemArrayList.get(position).getQuantity()));
                inventoryItem.setQuantity(temQuantity);
                mySQLiteHelper.updateItem(inventoryItem);
                mySQLiteHelper.close();
            }
        });
        return convertView;

    }
}
