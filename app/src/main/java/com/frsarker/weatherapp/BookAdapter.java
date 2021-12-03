package com.frsarker.weatherapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter {
    Context context;
    String bookTitle[];
    String authorName[];
    LayoutInflater inflter;

    public BookAdapter(Context applicationContext, String[] bookTitle, String[] authorName) {
        this.context = context;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return bookTitle.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row, null);
        TextView country = view.findViewById(R.id.BookTitle);
        TextView icon = view.findViewById(R.id.AuthorName);
        country.setText(bookTitle[i]);
        icon.setText(authorName[i]);
        return view;
    }
}
