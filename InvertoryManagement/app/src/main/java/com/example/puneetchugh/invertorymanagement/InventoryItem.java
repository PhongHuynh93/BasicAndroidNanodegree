package com.example.puneetchugh.invertorymanagement;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by puneetchugh on 7/2/16.
 */
public class InventoryItem implements Parcelable {

    private int id;
    private String itemName;
    private int quantity;
    private String supplier;
    private int price;
    private byte[] photo;

    public InventoryItem(int id,String itemName, int quantity, String supplier, int price, byte[] photo){
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.supplier = supplier;
        this.price = price;
        this.photo = photo;
    }

    public static final Parcelable.Creator<InventoryItem> CREATOR = new Parcelable.Creator<InventoryItem>() {
        public InventoryItem createFromParcel(Parcel p) {
            return new InventoryItem(p);
        }

        public InventoryItem[] newArray(int size) {
            return new InventoryItem[size];
        }
    };

    public InventoryItem(Parcel p) {

        this.id = p.readInt();
        this.itemName = p.readString();
        this.quantity = p.readInt();
        this.supplier = p.readString();
        this.price = p.readInt();
        this.photo = new byte[p.readInt()];
        p.readByteArray(this.photo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(itemName);
        dest.writeInt(quantity);
        dest.writeString(supplier);
        dest.writeInt(price);
        dest.writeInt(photo.length);
        dest.writeByteArray(photo);
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
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

    public int getPrice(){
        return price;
    }

    public byte[] getPhoto(){
        return photo;
    }
}