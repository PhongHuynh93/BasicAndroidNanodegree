package com.example.puneetchugh.invertorymanagement;

/**
 * Created by puneetchugh on 7/2/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by puneetchugh on 6/28/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "inventory.db";
    private static String TABLE_NAME = "inventory_table";
    private static String TABLE_COLUMN_NAME = "name";
    private static String TABLE_COLUMN_QUANTITY = "quantity";
    private static String TABLE_COLUMN_SUPPLIER = "supplier";
    private static String TABLE_COLUMN_PRICE = "price";
    private static String TABLE_COLUMN_ID = "_id";
    private SQLiteDatabase inventoryDatabase;
    private Context context;
    private SQLiteDatabase database;

    private static final String[] COLUMNS = {TABLE_COLUMN_ID, TABLE_COLUMN_NAME, TABLE_COLUMN_QUANTITY, TABLE_COLUMN_SUPPLIER, TABLE_COLUMN_PRICE};
    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "+TABLE_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TABLE_COLUMN_NAME+" TEXT NOT NULL, "+TABLE_COLUMN_QUANTITY+" INTEGER NOT NULL, "+TABLE_COLUMN_SUPPLIER+" TEXT NOT NULL, "+TABLE_COLUMN_PRICE+" INTEGER NOT NULL);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        this.onCreate(db);
    }

    public void insert(String name, int quantity, String supplier, int price){


        String nameToBeInserted = name.toLowerCase().trim();
        String supplierToBeInserted = supplier.trim();
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COLUMN_NAME, nameToBeInserted);
        contentValues.put(TABLE_COLUMN_QUANTITY, quantity);
        contentValues.put(TABLE_COLUMN_SUPPLIER, supplier);
        contentValues.put(TABLE_COLUMN_PRICE, price);

        Cursor cursor = db.query(TABLE_NAME,
                COLUMNS, " name=?", new String[]{nameToBeInserted}, null, null, null, null);
        if(cursor.moveToFirst()){
            db.update(TABLE_NAME, contentValues, TABLE_COLUMN_NAME + " = ?", new String[]{nameToBeInserted});
            Toast.makeText(context, "Product already existed. Now the product will show updated quantity. Also more quantity ordered", Toast.LENGTH_SHORT ).show();
        }
        else {
            db.insert(TABLE_NAME, null, contentValues);
            Toast.makeText(context, "New product ordered and updated", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public InventoryItem read(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                COLUMNS, " _id = ?",new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        InventoryItem inventoryItem = new InventoryItem(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4));
        return inventoryItem;
    }

    public ArrayList<InventoryItem> getListOfInventoryItem(){

        ArrayList<InventoryItem> inventoryItemList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToNext()){
            do{
                InventoryItem inventoryItem = new InventoryItem(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4));
                inventoryItemList.add(inventoryItem);

            }while (cursor.moveToNext());
        }

        return inventoryItemList;

    }

    public void update(int id){


    }

    public void deleteItem(InventoryItem inventoryItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,   TABLE_COLUMN_ID +" = ?", new String[] { String.valueOf(inventoryItem.getId()) });
        db.close();

    }

    public void updateItem(InventoryItem inventoryItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COLUMN_NAME, inventoryItem.getItemName());
        contentValues.put(TABLE_COLUMN_QUANTITY, inventoryItem.getQuantity());
        contentValues.put(TABLE_COLUMN_SUPPLIER, inventoryItem.getSupplier());
        contentValues.put(TABLE_COLUMN_PRICE, inventoryItem.getPrice());
        int returnValue = db.update(TABLE_NAME, contentValues, TABLE_COLUMN_ID + " = ?", new String[]{String.valueOf(inventoryItem.getId())});
        db.close();
    }

    public void deleteDatabase(){

    }
}

