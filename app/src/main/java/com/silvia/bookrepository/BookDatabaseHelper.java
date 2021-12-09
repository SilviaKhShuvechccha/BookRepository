package com.silvia.bookrepository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "db_book";
    public static final int VERSION = 1;
    public static final String BOOK_TABLE_NAME = "Book";
    public static final String BOOK_COL_ID = "Id";
    public static final String BOOK_COL_TITLE = "BookTitle";
    public static final String AUTHOR_COL_NAME = "AuthorName";
    public static final String SELF_LINK_COL = "SelfLink";

    public static final String CREATE_TABLE_BOOK = "CREATE TABLE " +
            BOOK_TABLE_NAME + "(" +
            BOOK_COL_ID + " INTEGER PRIMARY KEY, " +
            BOOK_COL_TITLE + " TEXT, " +
            AUTHOR_COL_NAME + " TEXT, " +
            SELF_LINK_COL + " TEXT)";

    public BookDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
