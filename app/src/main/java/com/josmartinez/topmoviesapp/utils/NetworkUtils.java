package com.josmartinez.topmoviesapp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.josmartinez.topmoviesapp.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static final String MOVIES_BASE_URL = "http://api.themoviedb.org/3";
    public static final String TOP_RATED_MOVIES = "movie/top_rated";
    public static final String MOST_POPULAR_MOVIES = "movie/popular";
    private static final String API_KEY = "api_key";
    private static final String TAG = "NetworkUtils";
    private static String API_VALUE;

    public static URL buildUrl(String querySegment, Context context){

        API_VALUE = context.getString(R.string.the_movie_db_api_key);

        Uri builtUri = Uri.parse(MOVIES_BASE_URL)
                .buildUpon()
                .appendPath(querySegment)
                .appendQueryParameter(API_KEY, API_VALUE)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.e(TAG, "buildUrl: ", e);
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) {

        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return scanner(in);
        } catch (IOException e) {
            Log.e(TAG, "fecthData: ", e);
        } finally {
            urlConnection.disconnect();
        }
        return null;


    }
    private static String scanner(InputStream in) {
        Scanner s = new Scanner(in).useDelimiter("\\A");
        return s.hasNext() ? s.next() : null;
    }

}
