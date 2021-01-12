package com.app.siaplapor.data.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static class FeedUser implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_ADDRESS = "address";
    }

    public static class FeedReport implements BaseColumns {
        public static final String TABLE_NAME = "report";
        public static final String COLUMN_NIK = "nik";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_REPORT = "report";
        public static final String COLUMN_USERID = "user_id";
    }
}
