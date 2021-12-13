package com.silvia.bookrepository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class BookDataSource {
    BookDatabaseHelper bookDatabaseHelper;
    SQLiteDatabase db;

    public BookDataSource(Context context) {
        bookDatabaseHelper = new BookDatabaseHelper(context);
    }

    private void openConnection() {
        db = bookDatabaseHelper.getWritableDatabase();
    }

    private void closeConnection() {
        db.close();
    }

    public boolean saveBook(Book book) {
        try {
            openConnection();
            ContentValues contentValues = new ContentValues();
            contentValues.put(BookDatabaseHelper.BOOK_COL_ID, book.getId());
            contentValues.put(BookDatabaseHelper.BOOK_COL_TITLE, book.getBookTitle());
            contentValues.put(BookDatabaseHelper.AUTHOR_COL_NAME, book.getAuthorName());
            contentValues.put(BookDatabaseHelper.SELF_LINK_COL, book.getSelfLink());
            contentValues.put(BookDatabaseHelper.PUBLISH_DATE_COL, book.getPublishedDate());
            contentValues.put(BookDatabaseHelper.PAGE_COUNT_COL, book.getPageCount());
            contentValues.put(BookDatabaseHelper.LANGUAGE_COL, book.getLanguage());
            contentValues.put(BookDatabaseHelper.DESCRIPTION_COL, book.getDescription());

            long rowAffacted = db.insert(BookDatabaseHelper.BOOK_TABLE_NAME, null, contentValues);
            Log.d("rowaffacted", rowAffacted + "");

            if (rowAffacted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        finally {
            closeConnection();
        }

    }

    public ArrayList<Book> getAllStudent() {
        ArrayList<Book> bookList = new ArrayList<Book>();

        openConnection();
        Cursor cursor = db.query(BookDatabaseHelper.BOOK_TABLE_NAME, null, null,
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int idIndex = cursor.getColumnIndex(BookDatabaseHelper.BOOK_COL_ID);
            int nameIndex = cursor.getColumnIndex(BookDatabaseHelper.BOOK_COL_TITLE);
            int authorNameIndex = cursor.getColumnIndex(BookDatabaseHelper.AUTHOR_COL_NAME);
            int selfLinkIndex = cursor.getColumnIndex(BookDatabaseHelper.SELF_LINK_COL);
            int languageIndex = cursor.getColumnIndex(BookDatabaseHelper.LANGUAGE_COL);
            int descriptionIndex = cursor.getColumnIndex(BookDatabaseHelper.DESCRIPTION_COL);
            int publishDateIndex = cursor.getColumnIndex(BookDatabaseHelper.PUBLISH_DATE_COL);
            int pageCountIndex = cursor.getColumnIndex(BookDatabaseHelper.PAGE_COUNT_COL);

            do {
                int id = cursor.getInt(idIndex);
                String title = cursor.getString(nameIndex);
                String authorName = cursor.getString(authorNameIndex);
                String selfLink = cursor.getString(selfLinkIndex);
                String language = cursor.getString(languageIndex);
                String description = cursor.getString(descriptionIndex);
                String publishDate = cursor.getString(publishDateIndex);
                String pageCount = cursor.getString(pageCountIndex);
                Book book = new Book(title, authorName, selfLink, publishDate, pageCount, language, description);
                bookList.add(book);
            } while (cursor.moveToNext());

        }
        closeConnection();
        return bookList;
    }

    public Book getBook(String bookId) {
        openConnection();

        Cursor cursor = db.query(BookDatabaseHelper.BOOK_TABLE_NAME, null, BookDatabaseHelper.BOOK_COL_ID + "=?",
                new String[]{bookId}, null, null, null);

        Book book = null;
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int idIndex = cursor.getColumnIndex(BookDatabaseHelper.BOOK_COL_ID);
            int nameIndex = cursor.getColumnIndex(BookDatabaseHelper.BOOK_COL_TITLE);
            int authorNameIndex = cursor.getColumnIndex(BookDatabaseHelper.AUTHOR_COL_NAME);
            int selfLinkIndex = cursor.getColumnIndex(BookDatabaseHelper.SELF_LINK_COL);
            int languageIndex = cursor.getColumnIndex(BookDatabaseHelper.LANGUAGE_COL);
            int descriptionIndex = cursor.getColumnIndex(BookDatabaseHelper.DESCRIPTION_COL);
            int publishDateIndex = cursor.getColumnIndex(BookDatabaseHelper.PUBLISH_DATE_COL);
            int pageCountIndex = cursor.getColumnIndex(BookDatabaseHelper.PAGE_COUNT_COL);

            String title = cursor.getString(cursor.getColumnIndex(BookDatabaseHelper.BOOK_COL_TITLE));
            String authorName = cursor.getString(cursor.getColumnIndex(BookDatabaseHelper.AUTHOR_COL_NAME));
            String selfLink = cursor.getString(selfLinkIndex);
            String language = cursor.getString(languageIndex);
            String description = cursor.getString(descriptionIndex);
            String publishDate = cursor.getString(publishDateIndex);
            String pageCount = cursor.getString(pageCountIndex);

            book = new Book(title, authorName, selfLink, publishDate, pageCount, language, description);

            Log.d("xyz", "came here");
        }
        closeConnection();
        return book;
    }


}
