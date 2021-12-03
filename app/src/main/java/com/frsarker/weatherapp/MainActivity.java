package com.frsarker.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String CITY = "dhaka,bd";
    String API = "8118ed6ee68db2debfaaa5a44c832918";

    TextView addressTxt, updated_atTxt, statusTxt, tempTxt, temp_minTxt, temp_maxTxt, sunriseTxt,
            sunsetTxt, windTxt, pressureTxt, humidityTxt;
    ListView dataLV;
    EditText searchET;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataLV = findViewById(R.id.dataLV);
        /*searchET = findViewById(R.id.searchET);
        searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new weatherTask().execute();
            }
        });*/

        String bookTitle[] = {"Computer Fundamental", "Algorithms", "australia", "Portugle", "America", "NewZealand"};
        String authorName[] = {"Silvia", "Sohid Ullah", "australia", "Portugle", "America", "NewZealand"};

        BookAdapter adapter = new BookAdapter(getApplicationContext(), bookTitle, authorName);
        dataLV.setAdapter(adapter);
    }


    class weatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /* Showing the ProgressBar, Making the main design GONE */
            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.errorText).setVisibility(View.GONE);
        }

        protected String doInBackground(String... args) {
            String input = searchET.getText().toString();
            String response = HttpRequest.excuteGet("https://www.googleapis.com/books/v1/volumes?q="+input);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject items = jsonObj.getJSONArray("items").getJSONObject(0);
                JSONObject volumeInfo =  items.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                String subtitle = volumeInfo.getString("subtitle");
                String selfLink = items.getString("selfLink");
                JSONArray author =volumeInfo.getJSONArray("authors");
                String publishedDate = volumeInfo.getString("publishedDate");
                String pageCount = volumeInfo.getString("pageCount");
                String printType = volumeInfo.getString("printType");
                JSONArray categories = volumeInfo.getJSONArray("categories");
                String language = volumeInfo.getString("language");
                JSONObject saleInfo =  items.getJSONObject("saleInfo");
                String country = saleInfo.getString("country");
                String saleability = saleInfo.getString("saleability");

                /* Populating extracted data into our views */
                /*addressTxt.setText(address);
                updated_atTxt.setText(updatedAtText);*/

                /* Views populated, Hiding the loader, Showing the main design */
                //findViewById(R.id.loader).setVisibility(View.GONE);
                //findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);


            } catch (JSONException e) {
                findViewById(R.id.loader).setVisibility(View.GONE);
                findViewById(R.id.errorText).setVisibility(View.VISIBLE);
            }

        }
    }
}
