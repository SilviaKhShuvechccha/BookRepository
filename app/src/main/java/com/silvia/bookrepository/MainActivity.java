package com.silvia.bookrepository;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView dataLV;
    EditText searchET;
    Button searchBtn;
    public ArrayList<Book> bookList;
    private final BookDataSource bookDataSource = new BookDataSource(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataLV = findViewById(R.id.dataLV);
        searchET = findViewById(R.id.searchET);
        searchBtn = findViewById(R.id.searchBtn);

        getSupportActionBar().setTitle("Book Repository"); // for set actionbar title
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchBooksData().execute();
            }
        });

        dataLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book) parent.getItemAtPosition(position);

                //boolean saved = bookDataSource.saveBook(book);
                //Log.d("svinginfo",saved+"");
                String selfLink = book.getSelfLink();
                Intent intent = new Intent(getApplicationContext(), BookDetailsActivity.class);
                //intent.putExtra("selfLink", selfLink);
                intent.putExtra("bookId", book.getId().trim());
                startActivity(intent);
                registerForContextMenu(dataLV);

            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_cancel, menu);
        Log.d("Test1", "Came here");
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Book book = bookList.get(info.position);
        Log.d("Test2", "Came here");
        switch(itemID){
            case R.id.SaveBtn:
                boolean isSaved =  bookDataSource.saveBook(book);
                if(isSaved)
                    Toast.makeText(this, "Book Saved Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Saving Failed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.CancelBtn:
                break;

        }
        return super.onContextItemSelected(item);
    }
    class FetchBooksData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /* Showing the ProgressBar, Making the main design GONE */
            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.errorText).setVisibility(View.GONE);
        }

        protected String doInBackground(String... args) {
            String input = searchET.getText().toString();
            String response = HttpRequest.excuteGet("https://www.googleapis.com/books/v1/volumes?q=" + input);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                bookList = new ArrayList<Book>();
                JSONObject jsonObj = new JSONObject(result);
                JSONArray items = jsonObj.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = (JSONObject) items.get(i);
                    JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                    String id = item.getString("id");
                    String title = volumeInfo.getString("title");
                    String author = volumeInfo.getJSONArray("authors").get(0).toString();
                    String selfLink = item.getString("selfLink");
                    String pageCount = volumeInfo.getString("pageCount");
                    String language = volumeInfo.getString("language");
                    String publishedDate = volumeInfo.getString("publishedDate");
                    String description = volumeInfo.getString("description");

                   // Book book = new Book(title, author, selfLink);
                    Book book = new Book(id, title, author, selfLink, publishedDate, pageCount, language, description);
                    bookList.add(book);
                }
                BookAdapter adapter = new BookAdapter(getApplicationContext(), bookList);
                dataLV.setAdapter(adapter);

                /* Views populated, Hiding the loader, Showing the main design */
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
