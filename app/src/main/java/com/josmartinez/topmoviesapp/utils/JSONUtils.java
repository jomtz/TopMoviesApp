package com.josmartinez.topmoviesapp.utils;

import com.josmartinez.topmoviesapp.Poster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtils {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String SIZE = "w185";

    public static ArrayList<Poster> parseJson(String json) {
        ArrayList<Poster> posters = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject movieObject = results.getJSONObject(i);
                long id = movieObject.getLong("id");
                double voteAverage = movieObject.getDouble("vote_average");
                String originalTitle = movieObject.getString("original_title");
                String overview = movieObject.getString("overview");
                String posterUrl = buildPosterPath(movieObject.getString("poster_path"));
                String releaseDate = movieObject.getString("release_date");
                Poster movie = new Poster(id, originalTitle, posterUrl, overview, voteAverage, releaseDate);
                posters.add(movie);
            }
            return posters;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static String buildPosterPath(String poster_path) {
        return IMAGE_BASE_URL.concat(SIZE).concat(poster_path);
    }


}
