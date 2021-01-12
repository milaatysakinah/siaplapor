package com.app.siaplapor.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.siaplapor.data.model.User;

import java.util.ArrayList;

public class UserTableHandler implements TableHandler<User> {
    DatabaseHelper dbHelper;

    public UserTableHandler(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void create(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FeedUser.COLUMN_NAME, user.getName());
        values.put(DatabaseContract.FeedUser.COLUMN_EMAIL, user.getEmail());
        values.put(DatabaseContract.FeedUser.COLUMN_USERNAME, user.getUsername());
        values.put(DatabaseContract.FeedUser.COLUMN_PASSWORD, user.getPassword());
        values.put(DatabaseContract.FeedUser.COLUMN_PHONE, user.getPhone());
        values.put(DatabaseContract.FeedUser.COLUMN_ADDRESS, user.getAddress());
        
        long newRowId = db.insert(DatabaseContract.FeedUser.TABLE_NAME, null, values);
    }

    @Override
    public User readById(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] projection = {
                DatabaseContract.FeedUser._ID,
                DatabaseContract.FeedUser.COLUMN_NAME,
                DatabaseContract.FeedUser.COLUMN_EMAIL,
                DatabaseContract.FeedUser.COLUMN_USERNAME,
                DatabaseContract.FeedUser.COLUMN_PASSWORD,
                DatabaseContract.FeedUser.COLUMN_PHONE,
                DatabaseContract.FeedUser.COLUMN_ADDRESS
        };

        String selection = DatabaseContract.FeedUser._ID + " = ?";
        String[] selectionArgs = { id };

        String sortOrder = DatabaseContract.FeedUser.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(
                DatabaseContract.FeedUser.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(
                cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.FeedUser._ID))+"",
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6));

        return user;
    }

    @Override
    public ArrayList<User> readAll() {
        ArrayList<User> userList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DatabaseContract.FeedUser.TABLE_NAME;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User(
                        cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.FeedUser._ID))+"",
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }

    @Override
    public void update(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FeedUser.COLUMN_NAME, user.getName());
        values.put(DatabaseContract.FeedUser.COLUMN_EMAIL, user.getEmail());
        values.put(DatabaseContract.FeedUser.COLUMN_USERNAME, user.getUsername());
        values.put(DatabaseContract.FeedUser.COLUMN_PASSWORD, user.getPassword());
        values.put(DatabaseContract.FeedUser.COLUMN_PHONE, user.getPhone());
        values.put(DatabaseContract.FeedUser.COLUMN_ADDRESS, user.getAddress());

        String selection = DatabaseContract.FeedUser._ID + " LIKE ?";
        String[] selectionArgs = { user.getId() };

        int count = db.update(
                DatabaseContract.FeedUser.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    @Override
    public void delete(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = DatabaseContract.FeedUser._ID + " LIKE ?";
        String[] selectionArgs = { user.getId() };

        int deletedRows = db.delete(DatabaseContract.FeedUser.TABLE_NAME, selection, selectionArgs);
    }
}
