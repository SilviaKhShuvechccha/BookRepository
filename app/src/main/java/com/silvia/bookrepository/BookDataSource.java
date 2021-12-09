package com.silvia.bookrepository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class BookDataSource {
    BookDatabaseHelper bookDatabaseHelper;
    SQLiteDatabase db;

    public BookDataSource(Context context){
        bookDatabaseHelper = new BookDatabaseHelper(context);
    }

    private void openConnection(){
        db = bookDatabaseHelper.getWritableDatabase();
    }
    private void closeConnection(){
        db.close();
    }

    public boolean saveBook(Book book){
        openConnection();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BookDatabaseHelper.BOOK_COL_TITLE, book.getBookTitle());
        contentValues.put(BookDatabaseHelper.AUTHOR_COL_NAME, book.getAuthorName());
        contentValues.put(BookDatabaseHelper.SELF_LINK_COL, book.getSelfLink());

       long rowAffacted = db.insert(BookDatabaseHelper.BOOK_TABLE_NAME, null, contentValues);
       Log.d("rowaffacted", rowAffacted+"");
       closeConnection();

       if(rowAffacted>0){
           return true;
       }
       else{
           return false;
       }
    }

    public ArrayList<Book> getAllStudent(){
        ArrayList<Book> bookList = new ArrayList<Book>();

        openConnection();
        Cursor cursor = db.query(BookDatabaseHelper.BOOK_TABLE_NAME, null, null,
                null, null, null, null);

        if(cursor!= null && cursor.getCount()>0){
            cursor.moveToFirst();
            int idIndex = cursor.getColumnIndex(BookDatabaseHelper.BOOK_COL_ID);
            int nameIndex = cursor.getColumnIndex(BookDatabaseHelper.BOOK_COL_TITLE);
            int authorNameIndex = cursor.getColumnIndex(BookDatabaseHelper.AUTHOR_COL_NAME);
            int selfLinkIndex = cursor.getColumnIndex(BookDatabaseHelper.SELF_LINK_COL);

            do{
                int id = cursor.getInt(idIndex);
                String title = cursor.getString(nameIndex);
                String authorName = cursor.getString(authorNameIndex);
                String selfLink = cursor.getString(selfLinkIndex);
                Book book = new Book(title, authorName, selfLink);
                bookList.add(book);
            }while (cursor.moveToNext());

        }
        closeConnection();
        return bookList;
    }


}
