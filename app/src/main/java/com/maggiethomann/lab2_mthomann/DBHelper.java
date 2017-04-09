package com.maggiethomann.lab2_mthomann;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import java.util.ArrayList;

/**
 * Created by bchaudhr on 3/20/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "teams.db";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_TEAM = "Team";
    public static String COL_ID = "id";
    public static String COL_LOGO = "logo";
    public static String COL_NAME = "name";
    public static String COL_DATE = "date";
    public static String COL_TIME = "time";
    public static String COL_LOCATION = "location";
    public static String COL_NICKNAME = "nickname";
    public static String COL_RECORD = "record";
    public static String COL_SCORE = "score";



    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_TEAM + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_LOGO + " TEXT, "
                + COL_DATE + " TEXT, "
                + COL_TIME + " TEXT, "
                + COL_LOCATION + " TEXT, "
                + COL_NICKNAME + " TEXT, "
                + COL_RECORD + " TEXT, "
                + COL_SCORE + " TEXT "
                + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists " + TABLE_TEAM );
        onCreate(db);
    }

    public void insertData(String tblname, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();

        long ret = db.insert(tblname, null, contentValues );

        if (ret > -1) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Insert Unsuccessful");
        }

        db.close();
    }

    public void deleteData(String tblname, String clause, int _id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(tblname, clause, new String[]{Integer.toString(_id)});
        db.close();
    }


    public Cursor getAllEntries(String tblname, String[] columns) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblname, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getSelectEntries(String tblname, String[] columns, String where, String[] args) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblname, columns, where, args, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String[] getTableFields(String tblname) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor dbCursor = db.query(tblname, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;
    }

    public ArrayList<Team> get_teams() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + COL_NAME, new String[]{});

        ArrayList<Team> games = new ArrayList<Team>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    String [] input = new String [9];

                    input[0] = (cursor.getString(cursor.getColumnIndex(COL_NAME)));
                    input[1] = (cursor.getString(cursor.getColumnIndex(COL_DATE)));
                    input[2] = (cursor.getString(cursor.getColumnIndex(COL_ID)));
                    input[3] = (cursor.getString(cursor.getColumnIndex(COL_LOCATION)));
                    input[4] = (cursor.getString(cursor.getColumnIndex(COL_LOGO)));
                    input[5] = (cursor.getString(cursor.getColumnIndex(COL_NICKNAME)));
                    input[6] = (cursor.getString(cursor.getColumnIndex(COL_RECORD)));
                    input[7] = (cursor.getString(cursor.getColumnIndex(COL_SCORE)));
                    input[8] = (cursor.getString(cursor.getColumnIndex(COL_TIME)));

                    Team team = new Team(input);

                    games.add(team);

                }while (cursor.moveToNext());
            }
        }

        cursor.close();

        return games;
    }

}
