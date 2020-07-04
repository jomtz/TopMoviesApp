package com.josmartinez.topmoviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.josmartinez.topmoviesapp.MovieAdapter.PosterViewHolder;
import com.josmartinez.topmoviesapp.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_movies);
        mRecyclerView.setHasFixedSize(true);

        //Use of grid layout manager
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Specification of adapter
        //mAdapter = new MovieAdapter(movieData);
        //mRecyclerView.setAdapter(mAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        switch (menuItemThatWasSelected){
            case R.id.most_popular_movies:
                makeHttpRequest(NetworkUtils.MOST_POPULAR_MOVIES);
                Context context_1 = MainActivity.this;
                String message_1 = "Most popular movies";
                Toast.makeText(context_1, message_1, Toast.LENGTH_LONG).show();
                return true;
            case R.id.top_rated_movies:
                makeHttpRequest(NetworkUtils.TOP_RATED_MOVIES);
                Context context_2 = MainActivity.this;
                String message_2 = "Top rated movies";
                Toast.makeText(context_2, message_2, Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void makeHttpRequest(String startHttpRequest) {

        URL githubSearchUrl = NetworkUtils.buildUrl(startHttpRequest, getApplicationContext());
        new MoviesBackgroundTask().execute(githubSearchUrl);

    }


    public class MoviesBackgroundTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String moviesSearchResults = null;
            try {
                moviesSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e){
                e.printStackTrace();
            }
            return moviesSearchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }



}
