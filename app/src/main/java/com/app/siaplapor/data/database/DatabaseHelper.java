package com.app.siaplapor.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SiapLapor.db";
    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + DatabaseContract.FeedUser.TABLE_NAME + " (" +
                    DatabaseContract.FeedUser._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.FeedUser.COLUMN_NAME + " TEXT," +
                    DatabaseContract.FeedUser.COLUMN_EMAIL + " TEXT," +
                    DatabaseContract.FeedUser.COLUMN_USERNAME + " TEXT," +
                    DatabaseContract.FeedUser.COLUMN_PASSWORD + " TEXT," +
                    DatabaseContract.FeedUser.COLUMN_PHONE + " TEXT," +
                    DatabaseContract.FeedUser.COLUMN_ADDRESS + " TEXT)";

    private static final String SQL_CREATE_REPORT =
            "CREATE TABLE " + DatabaseContract.FeedReport.TABLE_NAME + " (" +
                    DatabaseContract.FeedReport._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.FeedReport.COLUMN_NIK + " TEXT," +
                    DatabaseContract.FeedReport.COLUMN_NAME + " TEXT," +
                    DatabaseContract.FeedReport.COLUMN_PHONE + " TEXT," +
                    DatabaseContract.FeedReport.COLUMN_ADDRESS + " TEXT," +
                    DatabaseContract.FeedReport.COLUMN_REPORT + " TEXT," +
                    DatabaseContract.FeedReport.COLUMN_USERID + " INTEGER)";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + DatabaseContract.FeedUser.TABLE_NAME;

    private static final String SQL_DELETE_REPORT =
            "DROP TABLE IF EXISTS " + DatabaseContract.FeedReport.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_REPORT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USER);
        onCreate(db);
        db.execSQL(SQL_DELETE_REPORT);
        onCreate(db);
    }
}
