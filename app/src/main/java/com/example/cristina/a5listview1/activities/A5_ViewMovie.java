package com.example.cristina.a5listview1.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cristina.a5listview1.R;
import com.example.cristina.a5listview1.dataModel.Actor;
import com.example.cristina.a5listview1.dataModel.Genre;
import com.example.cristina.a5listview1.dataModel.Movie;
import com.example.cristina.a5listview1.dataModel.MovieManager;

import java.util.ArrayList;

public class A5_ViewMovie extends AppCompatActivity {

    private TextView title;
    private TextView director;
    private TextView description;
    private TextView year;
    private TextView id;
    private TextView runtime;
    private TextView rating;
    private TextView votes;
    private TextView revenue;
    private ListView genres;
    private ListView actors;
    private int pos_movie;
    private Movie movie;

    private ArrayAdapter<String> arrayAdapterGenre;
    private ArrayAdapter<String> arrayAdapterActor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a5__view_movie);

        pos_movie = getIntent().getExtras().getInt("position_movie");
        movie = MovieManager.getInstance().getItem(pos_movie);

        title = (TextView) findViewById(R.id.title_view);
        director = (TextView) findViewById(R.id.director_view);
        description = (TextView) findViewById(R.id.description_view);
        year = (TextView) findViewById(R.id.year_view);
        id = (TextView) findViewById(R.id.id_view);
        runtime = (TextView) findViewById(R.id.runtime_view);
        rating = (TextView) findViewById(R.id.rating_view);
        votes = (TextView) findViewById(R.id.votes_view);
        revenue = (TextView) findViewById(R.id.revenue_view);

        genres = (ListView) findViewById(R.id.genres_view);
        actors = (ListView) findViewById(R.id.actors_view);

        setText();
        addListener();

    }

    private void addListener()
    {
        genres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(A5_ViewMovie.this, A8_Genres.class);
                intent.putExtra("genre", movie.getGenre(i));
                startActivity(intent);
            }
        });

        actors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(A5_ViewMovie.this, A7_Actors.class);
                intent.putExtra("actor", movie.getActor(i));
                startActivity(intent);
            }
        });
    }

    private void setText()
    {
        title.setText(movie.getTitle());
        director.setText( movie.getDirector());
        description.setText("Description: \n" + movie.getDescription());
        String yearString = Integer.toString(movie.getYear());
        year.setText(yearString);
        String runtimeString = Integer.toString(movie.getRuntime());
        runtime.setText(runtimeString + " min");
        String ratingString = Float.toString(movie.getRating());
        rating.setText(ratingString + "/10");
        String votesString = Float.toString(movie.getVotes());
        votes.setText("Votes: " + votesString);
        String revenueString = Float.toString(movie.getRevenue());
        revenue.setText("Revenue: " + revenueString + "€");
        viewGenres();
        viewActors();
    }

    private void viewActors()
    {
        //copy of all genres
        ArrayList<Genre> genresList;
        genresList = MovieManager.getInstance().getDataOfGenres();

        //copy of genres of movie
        int[] genresOfMovie = movie.getGenre();
        arrayAdapterGenre = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for(int i = 0; i < genresOfMovie.length; i++)
        {
            for(int j = 0; j < genresList.size(); j++)
            {
                if(genresOfMovie[i] == genresList.get(j).getId())
                {
                    arrayAdapterGenre.add(genresList.get(j).getName());
                }
            }
        }
        genres.setAdapter(arrayAdapterGenre);
    }

    private void viewGenres()
    {
        //Copy of all actors
        ArrayList<Actor> actorList;
        actorList = MovieManager.getInstance().getDataOfActors();

        //Copy of actors of movie
        int[] actorsString = movie.getActors();
        arrayAdapterActor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for(int i = 0; i < actorsString.length; i++)
        {
            for(int j = 0; j < actorList.size(); j++)
            {
                if(actorsString[i] == actorList.get(j).getId())
                {
                    arrayAdapterActor.add(actorList.get(j).getName());
                }
            }
        }
        actors.setAdapter(arrayAdapterActor);

    }
}
