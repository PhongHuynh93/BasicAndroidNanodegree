package com.example.puneetchugh.habittracker;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by puneetchugh on 6/29/16.
 */
public class Contract {

    public static String DATABASE="habits.db";
    public static String TABLE_HABITS_LEFT="habitsleft";
    public static String TABLE_HABITS_ADOPTED="habisadopted";
    public static class HabitsLeft{
        public static String COLUMN_ID = "_id";
        public static String COLUMN_HABIT = "habit";
        public static String COLUMN_DATE_LEFT="date_adopted";

        int id;
        String habit;
        String date;

        public HabitsLeft(int id, String habit, String date){
            this.date = date;
            this.habit = habit;
            this.id = id;
        }


    }

    public static class HabitsAdopted{
        public static String COLUMN_ID = "_id";
        public static String COLUMN_HABIT = "habit";
        public static String COLUMN_DATE_ADOPTED="date_adopted";

        int id;
        String habit;
        String date;

        public HabitsAdopted(int id, String habit, String date){
            this.date = date;
            this.habit = habit;
            this.id = id;
        }
    }
}
