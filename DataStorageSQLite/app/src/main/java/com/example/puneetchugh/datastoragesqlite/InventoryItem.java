package com.example.puneetchugh.datastoragesqlite;

import android.content.Intent;

/**
 * Created by puneetchugh on 6/28/16.
 */
public class InventoryItem {

    private int id;
    private String itemName;
    private int quantity;
    private String supplier;

    public InventoryItem(int id,String itemName, int quantity, String supplier){
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public int getId(){
        return id;
    }

    public String getItemName(){
        return itemName;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getSupplier(){
        return supplier;
    }
}
