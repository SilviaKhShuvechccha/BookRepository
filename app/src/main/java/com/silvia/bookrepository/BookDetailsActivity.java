package com.silvia.bookrepository;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class BookDetailsActivity extends AppCompatActivity {
    String selfLinkUrl;
    TextView TitleTV, AuthorTV, PublisherTV, PublishedDateTV, SubTitleTV, PageCountTV, AverageRatingTV, RatingCountTV, LanguageTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        TitleTV = findViewById(R.id.Title);
        SubTitleTV = findViewById(R.id.Subtitle);
        AuthorTV = findViewById(R.id.Author);
        PublisherTV = findViewById(R.id.Publisher);
        PublishedDateTV = findViewById(R.id.PublishedDate);
        PageCountTV = findViewById(R.id.PageCount);
        AverageRatingTV = findViewById(R.id.AverageRating);
        RatingCountTV = findViewById(R.id.RatingsCount);
        LanguageTV = findViewById(R.id.Language);

        Intent intent = getIntent();
        selfLinkUrl = intent.getStringExtra("selfLink");
        new FetchBookData().execute();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class FetchBookData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /* Showing the ProgressBar, Making the main design GONE */
            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.errorText).setVisibility(View.GONE);
        }
        protected String doInBackground(String... args) {
            String response = HttpRequest.excuteGet(selfLinkUrl);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject item = new JSONObject(result);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                String subtitle = volumeInfo.getString("subtitle");
                String author = volumeInfo.getJSONArray("authors").get(0).toString();
                String publisher = volumeInfo.getString("publisher");
                String publishedDate = volumeInfo.getString("publishedDate");
                String pageCount = volumeInfo.getString("pageCount");
                String averageRating = volumeInfo.getString("averageRating");
                String ratingsCount = volumeInfo.getString("ratingsCount");
                String language = volumeInfo.getString("language");

                TitleTV.setText(title);
                SubTitleTV.setText(subtitle);
                AuthorTV.setText(author);
                PublishedDateTV.setText(publishedDate);
                PublisherTV.setText(publisher);
                PageCountTV.setText(pageCount);
                AverageRatingTV.setText(averageRating);
                RatingCountTV.setText(ratingsCount);
                LanguageTV.setText(language);

                findViewById(R.id.loader).setVisibility(View.GONE);
                findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);
                findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);
            } catch (JSONException e) {
                findViewById(R.id.loader).setVisibility(View.GONE);
                findViewById(R.id.errorText).setVisibility(View.VISIBLE);
            }


        }
    }
}
