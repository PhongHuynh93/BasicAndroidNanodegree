package com.example.puneetchugh.datastoragesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static String TABLE_COLUMN_ID = "_id";
    private SQLiteDatabase inventoryDatabase;

    private static final String[] COLUMNS = {TABLE_COLUMN_ID, TABLE_COLUMN_NAME, TABLE_COLUMN_QUANTITY, TABLE_COLUMN_SUPPLIER};
    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "+TABLE_COLUMN_ID+" INTEGER PRIMARYKEY AUTOINCREMENT, "+TABLE_NAME+" TEXT NOT NULL, "+TABLE_COLUMN_QUANTITY+" INTEGER NOT NULL"+TABLE_COLUMN_SUPPLIER+" TEXT NOT NULL";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        this.onCreate(db);
    }

    public void insert(String name, int quantity, String supplier){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COLUMN_NAME, name);
        contentValues.put(TABLE_COLUMN_QUANTITY, quantity);
        contentValues.put(TABLE_COLUMN_SUPPLIER, supplier);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public InventoryItem read(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                COLUMNS, " _id = ?",new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        InventoryItem inventoryItem = new InventoryItem(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
        return inventoryItem;
    }

    public List getListOfInventoryItem(){

        List<InventoryItem> inventoryItemList = new LinkedList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToNext()){
            do{
                InventoryItem inventoryItem = new InventoryItem(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
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

        db.update(TABLE_NAME, contentValues, TABLE_COLUMN_ID + " = ?", new String[]{String.valueOf(inventoryItem.getId())});
        db.close();
    }

    public void deleteDatabase(){

    }
}
