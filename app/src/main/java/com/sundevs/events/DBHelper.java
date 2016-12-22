package com.sundevs.events;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Latitude on 06/12/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "events";
    public static final String CONTACTS_TABLE_NAME = "events";
    public static final String EVENT_COLUMN_ID = "id";
    public static final String EVENT_COLUMN_STUNAME = "name";
    public static final String EVENT_COLUMN_DESCRIPTION = "description";
    public static final String EVENT_COLUMN_INITIAL_HOUR = "start";
    public static final String EVENT_COLUMN_FINAL_HOUR = "end";
    public static final String EVENT_COLUMN_FINAL_DATE = "date";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE table events (id integer primary key autoincrement, name TEXT, description TEXT, start integer, end integer,date TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS events");

    }

    public boolean addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", event.getName());
        contentValues.put("description", event.getDescription());
        contentValues.put("start", event.getStart());
        contentValues.put("end", event.getEnd());
        contentValues.put("date", event.getDate());
        boolean test = db.insert("events", null, contentValues) > 0;
        db.close();
        return test;
    }

    public Cursor getData(String date1) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM events WHERE date = ?";
        String[] whereArgs = new String[]{
                date1
        };
        Cursor res = db.rawQuery(query, whereArgs);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getWritableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public ArrayList<String> getAllEvents() {
        ArrayList<String> arraylist = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from events", null);

        if (cursor.moveToFirst()) {
            do {
                arraylist.add(cursor.getString(cursor.getColumnIndex(EVENT_COLUMN_STUNAME)));
            } while (cursor.moveToNext());
        }
        return arraylist;
    }
}
