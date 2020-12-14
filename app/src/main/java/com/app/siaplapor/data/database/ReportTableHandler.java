package com.app.siaplapor.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.siaplapor.data.model.Report;
import com.app.siaplapor.data.model.User;

import java.util.ArrayList;

public class ReportTableHandler implements TableHandler<Report>{
    DatabaseHelper dbHelper;

    public ReportTableHandler(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void create(Report report) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FeedReport.COLUMN_NIK, report.getNik());
        values.put(DatabaseContract.FeedReport.COLUMN_NAME, report.getName());
        values.put(DatabaseContract.FeedReport.COLUMN_PHONE, report.getPhone());
        values.put(DatabaseContract.FeedReport.COLUMN_ADDRESS, report.getAddress());
        values.put(DatabaseContract.FeedReport.COLUMN_REPORT, report.getReport());
        values.put(DatabaseContract.FeedReport.COLUMN_USERID, report.getUserId());

        long newRowId = db.insert(DatabaseContract.FeedReport.TABLE_NAME, null, values);
    }

    @Override
    public Report readById(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] projection = {
                DatabaseContract.FeedReport._ID,
                DatabaseContract.FeedReport.COLUMN_NIK,
                DatabaseContract.FeedReport.COLUMN_NAME,
                DatabaseContract.FeedReport.COLUMN_PHONE,
                DatabaseContract.FeedReport.COLUMN_ADDRESS,
                DatabaseContract.FeedReport.COLUMN_REPORT,
                DatabaseContract.FeedReport.COLUMN_USERID
        };

        String selection = DatabaseContract.FeedReport._ID + " = ?";
        String[] selectionArgs = { id };

        String sortOrder = DatabaseContract.FeedReport.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(
                DatabaseContract.FeedReport.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        if (cursor != null)
            cursor.moveToFirst();

        Report report = new Report(
                cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.FeedReport._ID))+"",
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6));

        return report;
    }

    public Report readByUserId(String userId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] projection = {
                DatabaseContract.FeedReport._ID,
                DatabaseContract.FeedReport.COLUMN_NIK,
                DatabaseContract.FeedReport.COLUMN_NAME,
                DatabaseContract.FeedReport.COLUMN_PHONE,
                DatabaseContract.FeedReport.COLUMN_ADDRESS,
                DatabaseContract.FeedReport.COLUMN_REPORT,
                DatabaseContract.FeedReport.COLUMN_USERID
        };

        String selection = DatabaseContract.FeedReport.COLUMN_USERID + " = ?";
        String[] selectionArgs = { userId };

        String sortOrder = DatabaseContract.FeedReport.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(
                DatabaseContract.FeedReport.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        if (cursor != null)
            cursor.moveToFirst();

        Report report = new Report(
                cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.FeedReport._ID))+"",
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6));

        return report;
    }

    @Override
    public ArrayList<Report> readAll() {
        return null;
    }

    @Override
    public void update(Report report) {

    }

    @Override
    public void delete(Report report) {

    }
}
