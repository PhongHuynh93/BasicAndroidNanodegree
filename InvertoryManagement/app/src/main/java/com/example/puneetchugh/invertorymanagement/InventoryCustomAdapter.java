package com.example.puneetchugh.invertorymanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    private ArrayList<InventoryItem> inventoryItemArrayList;
    private Context context;


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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        listProductView = (TextView) convertView.findViewById(R.id.product);
        listProductQuantity = (TextView) convertView.findViewById(R.id.quantity);
        listProductPrice = (TextView) convertView.findViewById(R.id.price);
        listProductSeller = (TextView) convertView.findViewById(R.id.seller);

        listProductView.setText("Product : "+inventoryItemArrayList.get(position).getItemName());
        listProductPrice.setText("Price($) per unit : "+Integer.toString(inventoryItemArrayList.get(position).getPrice()));
        listProductSeller.setText("Seller : "+inventoryItemArrayList.get(position).getSupplier());
        listProductQuantity.setText("Quantity " +Integer.toString(inventoryItemArrayList.get(position).getQuantity()));
        return convertView;

    }
}
