package com.josmartinez.topmoviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.josmartinez.topmoviesapp.utils.JSONUtils;
import com.josmartinez.topmoviesapp.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener{

    private RecyclerView mMovieList;
    private ArrayList<Poster> posters;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieList = findViewById(R.id.rv_movies);

        mMovieList.setHasFixedSize(true);
        mErrorMessageDisplay = findViewById(R.id.error_message);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        //Use of grid layout manager
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mMovieList.setLayoutManager(mLayoutManager);


        //Specification of adapter
        populateMovies(posters);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        //Write intent
    }

    private void populateMovies(List<Poster> posterList){
        showMoviesListView();
        this.posters = new ArrayList<>(posterList);
        MovieAdapter mAdapter = new MovieAdapter(posters, MainActivity.this);
        mMovieList.setAdapter(mAdapter);
    }


    private void showMoviesListView(){
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mMovieList.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage(){
        mMovieList.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
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

        URL movieSearchUrl = NetworkUtils.buildUrl(startHttpRequest, getApplicationContext());
        new MoviesBackgroundTask().execute(movieSearchUrl);

    }




    @SuppressLint("StaticFieldLeak")
    public class MoviesBackgroundTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            return NetworkUtils.getResponseFromHttpUrl(searchUrl);
        }

        @Override
        protected void onPostExecute(String r) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (r != null && !r.isEmpty()){
                showMoviesListView();
                ArrayList<Poster> posters = JSONUtils.parseJson(r);
                populateMovies(posters);
            } else{
                showErrorMessage();
            }
            super.onPostExecute(r);
        }
    }



}
