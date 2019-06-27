package com.codepath.debuggingchallenges.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codepath.debuggingchallenges.R;
import com.codepath.debuggingchallenges.adapters.MoviesAdapter;
import com.codepath.debuggingchallenges.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    private static final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private static final String API_PARAM = "api_key";

    RecyclerView rvMovies;
    MoviesAdapter adapter;
    ArrayList<Movie> movies;
    //move here
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        //initialize
        client = new AsyncHttpClient();
        movies = new ArrayList<>();
        // Create the adapter to convert the array to views, get rid of redefinition
        adapter = new MoviesAdapter(movies);
        rvMovies = findViewById(R.id.rvMovies);
        // Attach the adapter to a ListView
        rvMovies.setAdapter(adapter);
        // layout manager
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        fetchMovies();

    }


    private void fetchMovies() {
        // Space error, set key
        String url = "https://api.themoviedb.org/3/movie/now_playing";
        // Set up params
        RequestParams params = new RequestParams();
        params.add(API_PARAM, API_KEY);
        // add params
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray moviesJson = response.getJSONArray("results");
                    // need to add individual items, cant use an equal
                    movies.addAll(Movie.fromJSONArray(moviesJson));
                    //notify data set change
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
