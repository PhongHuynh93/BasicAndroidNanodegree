package com.example.puneetchugh.mybooklist;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MyAdapter myAdapter;
    private ArrayList<BookItem> itemArrayList;
    private EditText searchBookEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);
        searchBookEditText = (EditText) findViewById(R.id.search_book_id);
        itemArrayList = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void makeSearch(String bookToBeSearched) {
        String urlstring;
        urlstring = "https://www.googleapis.com/books/v1/volumes?q="+bookToBeSearched+"&key=AIzaSyAlaSk5tIXhhNTZk9wgTN8u32nYdCZQjvI&fields=kind,items(volumeInfo(title,authors))&maxResults=20";
        new CallAPI().execute(urlstring);
    }

    private void response(String responseData) {

        try {
            JSONObject responseObject = new JSONObject(responseData);
            JSONArray jsonItemArray = responseObject.getJSONArray("items");

            for (int i = 0; i < jsonItemArray.length(); i++) {

                JSONObject jsonObject = jsonItemArray.getJSONObject(i);
                JSONObject volumeInfoObject = jsonObject.getJSONObject("volumeInfo");
                String title = volumeInfoObject.getString("title");
                JSONArray authorsArray = volumeInfoObject.getJSONArray("authors");
                String author = authorsArray.getString(0);
                itemArrayList.add(new BookItem(author,title));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        myAdapter = new MyAdapter(this, itemArrayList);
        listView.setAdapter(myAdapter);
    }

    public void searchBook(View view) {

        String bookToBeSearched = searchBookEditText.getText().toString();
        makeSearch(bookToBeSearched);
    }


    private class CallAPI extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            InputStream in = null;
            int resCode = -1;
            try {
                URL url = new URL(params[0]);
                URLConnection urlConn = url.openConnection();

                if (!(urlConn instanceof HttpURLConnection)) {
                    throw new IOException("URL is not an Http URL");
                }
                HttpURLConnection httpConn = (HttpURLConnection) urlConn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();
                resCode = httpConn.getResponseCode();

                if (resCode == HttpURLConnection.HTTP_OK) {
                    in = httpConn.getInputStream();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String json = convertStreamToString(in);
            return json;
        }

        public String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;

            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            return sb.toString();
        }

        protected void onPostExecute(String stream_url) {
            super.onPostExecute(stream_url);
            response(stream_url);
        }
    }
}
