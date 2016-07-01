package com.example.puneetchugh.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by puneetchugh on 6/29/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {


    private static final String[] TABLE_1_COLUMNS = {Contract.HabitsLeft.COLUMN_ID, Contract.HabitsLeft.COLUMN_HABIT, Contract.HabitsLeft.COLUMN_DATE_LEFT};
    private static final String[] TABLE_2_COLUMNS = {Contract.HabitsAdopted.COLUMN_ID, Contract.HabitsAdopted.COLUMN_HABIT, Contract.HabitsAdopted.COLUMN_DATE_ADOPTED};
    public MySQLiteHelper(Context context){
        super(context, Contract.DATABASE, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE "+Contract.TABLE_HABITS_LEFT+" ( "
                                             +Contract.HabitsLeft.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                                             +Contract.HabitsLeft.COLUMN_HABIT+" TEXT NOT NULL, "
                                             +Contract.HabitsLeft.COLUMN_DATE_LEFT+" TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
        CREATE_TABLE = "CREATE TABLE "+Contract.TABLE_HABITS_ADOPTED+" ( "
                                      +Contract.HabitsAdopted.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                                      +Contract.HabitsAdopted.COLUMN_HABIT+" TEXT NOT NULL, "
                                      +Contract.HabitsAdopted.COLUMN_DATE_ADOPTED+" TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + Contract.TABLE_HABITS_LEFT);
        db.execSQL("DROP TABLE IF EXISTS" + Contract.TABLE_HABITS_ADOPTED);
        this.onCreate(db);
    }

    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(Contract.DATABASE);
    }

    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+ Contract.TABLE_HABITS_LEFT);
        db.execSQL("delete from"+ Contract.TABLE_HABITS_ADOPTED);
    }
    public void insert( String habit, String date, String habitStatus){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(habitStatus.matches("left")) {
            contentValues.put(Contract.HabitsLeft.COLUMN_HABIT, habit);
            contentValues.put(Contract.HabitsLeft.COLUMN_DATE_LEFT, date);
            db.insert(Contract.TABLE_HABITS_LEFT, null, contentValues);
        }
        else{
            contentValues.put(Contract.HabitsAdopted.COLUMN_HABIT, habit);
            contentValues.put(Contract.HabitsAdopted.COLUMN_DATE_ADOPTED, date);
            db.insert(Contract.TABLE_HABITS_ADOPTED, null, contentValues);

        }
        db.close();
    }

    public void read(String habit, String habitStatus){
        SQLiteDatabase db = getWritableDatabase();
        if(habitStatus.matches("left")){
            Cursor cursor = db.query(Contract.TABLE_HABITS_LEFT, new String[]{ Contract.HabitsLeft.COLUMN_ID,
                                                                               Contract.HabitsLeft.COLUMN_HABIT,
                                                                               Contract.HabitsLeft.COLUMN_DATE_LEFT},
                                                                               Contract.HabitsLeft.COLUMN_ID+" _id = ?",
                                                                               new String[]{habit}, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }
            Contract.HabitsLeft habitsLeft = new Contract.HabitsLeft(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        else if(habitStatus.matches("adopted")){
            Cursor cursor = db.query(Contract.TABLE_HABITS_ADOPTED, new String[]{Contract.HabitsAdopted.COLUMN_ID,
                                                                                 Contract.HabitsAdopted.COLUMN_HABIT,
                                                                                 Contract.HabitsAdopted.COLUMN_DATE_ADOPTED},
                                                                                 Contract.HabitsAdopted.COLUMN_ID+" _id = ?",
                                                                                 new String[]{habit}, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }
            Contract.HabitsAdopted habitsAdopted = new Contract.HabitsAdopted(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
    }


    public void deleteHabit(String habit, String habitStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (habitStatus.matches("left")) {
            db.delete(Contract.TABLE_HABITS_LEFT, Contract.HabitsLeft.COLUMN_HABIT + " = ?", new String[]{habit});
        }
        else if (habitStatus.matches("adopted")) {
            db.delete(Contract.TABLE_HABITS_ADOPTED, Contract.HabitsAdopted.COLUMN_HABIT + " = ?", new String[]{habit});
        }
        db.close();

    }

    public void updateHabit(String habit, String date, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(status.matches("left")) {
            contentValues.put(Contract.HabitsLeft.COLUMN_HABIT, habit);
            contentValues.put(Contract.HabitsLeft.COLUMN_DATE_LEFT, date);
            db.update(Contract.TABLE_HABITS_LEFT, contentValues, Contract.HabitsLeft.COLUMN_HABIT + " = ?", new String[]{habit});
        }
        else if(status.matches("adopted")) {
            contentValues.put(Contract.HabitsAdopted.COLUMN_HABIT, habit);
            contentValues.put(Contract.HabitsAdopted.COLUMN_DATE_ADOPTED, date);
            db.update(Contract.TABLE_HABITS_ADOPTED, contentValues, Contract.HabitsAdopted.COLUMN_HABIT + " = ?", new String[]{habit});
        }
        db.close();
    }
}

