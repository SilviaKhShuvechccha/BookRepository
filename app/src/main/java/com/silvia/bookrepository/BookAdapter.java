package com.silvia.bookrepository;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    LayoutInflater inflter;

    public BookAdapter(Context applicationContext, List<Book> bookList) {
        super(applicationContext, 0, bookList);
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        TextView tvName = convertView.findViewById(R.id.BookTitle);
        TextView tvHome = convertView.findViewById(R.id.AuthorName);
        TextView selfLink = convertView.findViewById(R.id.SelfLink);
        tvName.setText(user.getBookTitle());
        tvHome.setText(user.getAuthorName());
        selfLink.setText(user.getSelfLink());
        return convertView;
    }


}



