package com.silvia.bookrepository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "db_book1";
    public static final int VERSION = 1;
    public static final String BOOK_TABLE_NAME = "Book1";
    public static final String BOOK_COL_ID = "Id";
    public static final String BOOK_COL_TITLE = "BookTitle";
    public static final String AUTHOR_COL_NAME = "AuthorName";
    public static final String SELF_LINK_COL = "SelfLink";
    public static final String PUBLISH_DATE_COL = "PublishDate";
    public static final String PAGE_COUNT_COL = "PageCount";
    public static final String LANGUAGE_COL = "Language";
    public static final String DESCRIPTION_COL = "Description";

    public static final String CREATE_TABLE_BOOK = "CREATE TABLE " +
            BOOK_TABLE_NAME + "(" +
            BOOK_COL_ID + " TEXT PRIMARY KEY, " +
            BOOK_COL_TITLE + " TEXT, " +
            AUTHOR_COL_NAME + " TEXT, " +
            SELF_LINK_COL + " TEXT, " +
            PUBLISH_DATE_COL + " TEXT, " +
            PAGE_COUNT_COL + " TEXT, " +
            LANGUAGE_COL + " TEXT, " +
            DESCRIPTION_COL + " TEXT)";

    public BookDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOK);
        //db.execSQL("CREATE TABLE Book(Id TEXT PRIMARY KEY, BookTitle TEXT, AuthorName TEXT, SelfLink TEXT, PublishDate TEXT, PageCount TEXT, Language TEXT, Description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
