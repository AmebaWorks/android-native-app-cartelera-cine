package com.example.cristina.a5listview1.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.cristina.a5listview1.Constants;
import com.example.cristina.a5listview1.utils.AsynkGet;
import com.example.cristina.a5listview1.R;
import com.example.cristina.a5listview1.dataModel.Actor;
import com.example.cristina.a5listview1.dataModel.Genre;
import com.example.cristina.a5listview1.dataModel.Movie;
import com.example.cristina.a5listview1.dataModel.MovieManager;
import com.google.gson.Gson;

public class A1_Splash extends AppCompatActivity {

    private int chargeComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chargeComplete = 0;

        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_a1__splash);

        @SuppressLint("StaticFieldLeak")
        AsynkGet getMovies = new AsynkGet(Constants.baseUrl+Constants.fileGetMovie +Constants.userPass) {
            @Override
            protected void onPostExecute(String string) {

                Gson gson = new Gson();
                Movie[] movies = gson.fromJson(string, Movie[].class);

                if (movies != null) {
                    for (int i = 0; i < movies.length; i++) {
                        MovieManager.getInstance().copyMovie(movies[i]);
                    }
                    chargeComplete();
                }
            }
        };
        getMovies.execute();

        @SuppressLint("StaticFieldLeak")
        AsynkGet getGenres = new AsynkGet(Constants.baseUrl+Constants.fileGetGenres +Constants.userPass) {
            @Override
            protected void onPostExecute(String string) {
                Gson gson = new Gson();

                Genre[] genres = gson.fromJson(string, Genre[].class);

                if (genres != null) {
                    for (int i = 0; i < genres.length; i++) {
                        MovieManager.getInstance().copyGenre(genres[i]);
                    }
                    chargeComplete();
                }
            }

        };
        getGenres.execute();

        @SuppressLint("StaticFieldLeak")
        AsynkGet getActors = new AsynkGet(Constants.baseUrl+Constants.fileGetActors +Constants.userPass)
        {
            @Override
            protected void onPostExecute(String string)
            {
                Gson gson = new Gson();

                Actor[] actors = gson.fromJson(string, Actor[].class);

                if (actors != null)
                {
                    for (int i = 0; i < actors.length; i++)
                    {
                        MovieManager.getInstance().copyActor(actors[i]);
                    }
                    chargeComplete();
                }
            }
        };
        getActors.execute();
    }

    public void chargeComplete() {
        chargeComplete++ ;
        if(chargeComplete == 3)
        {
            Intent mainIntent = new Intent().setClass(A1_Splash.this, A2_MovieList.class);
            startActivity(mainIntent);
        }
    }
}
