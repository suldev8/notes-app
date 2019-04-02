package com.example.notes_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

    //Notes database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes.db";

    //Table name
    public static final String TABLE_NAME = "note";

    //Table columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_NOTE = "note";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStmt = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_NOTE + " TEXT" + ");";
        db.execSQL(sqlStmt);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.d("DB", "The table has been removed!");
        onCreate(db);
    }

    //Add new note to the database
    public void addNote(String title, String note) {
        SQLiteDatabase db = getWritableDatabase();

        //Put the note values in ContentValues
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_NOTE, note);

        //Insert the new note to the database
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Delete a note from the database
    public void delNote(String id) {
        SQLiteDatabase db = getWritableDatabase();

        String[] whereArgs = {id};

        //Delete the note from the database
        db.delete(TABLE_NAME,
                COLUMN_ID + " = ?",
                whereArgs);
        db.close();
    }

    public void updNote(String id,String title, String note) {
        SQLiteDatabase db = getWritableDatabase();

        String[] whereArgs = {id};

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_NOTE, note);

        db.update(TABLE_NAME, values,
                COLUMN_ID + " = ?",
                whereArgs);

    }


    public String[][] getNotes() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        String[][] titles = new String[c.getCount()][c.getColumnCount()];
        c.moveToFirst();
        for(int i = 0; !c.isAfterLast(); i++) {
            int j =0;
            while(j < c.getColumnCount()) {
                titles[i][j++] = c.getString(c.getColumnIndex(COLUMN_ID));
                titles[i][j++] = c.getString(c.getColumnIndex(COLUMN_TITLE));
                titles[i][j++] = c.getString(c.getColumnIndex(COLUMN_NOTE));
            }
            c.moveToNext();
        }
        return titles;
    }
}
