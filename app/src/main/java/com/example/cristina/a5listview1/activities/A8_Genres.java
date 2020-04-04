package com.example.cristina.a5listview1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cristina.a5listview1.R;
import com.example.cristina.a5listview1.dataModel.Actor;
import com.example.cristina.a5listview1.dataModel.Genre;
import com.example.cristina.a5listview1.dataModel.Movie;
import com.example.cristina.a5listview1.dataModel.MovieManager;

import java.util.ArrayList;

public class A8_Genres extends AppCompatActivity {

    private TextView titleGenre;
    private ListView genreAppear;
    private Button deleteGenre;
    private int idGenre;
    private ArrayList<Movie> dataOfMovies;
    private ArrayList<Genre> dataOfGenre;
    private ArrayAdapter<String> adapterGenreAppear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a7__actors);

        idGenre = getIntent().getExtras().getInt("genre");

        dataOfMovies = MovieManager.getInstance().getDataOfMovies();
        dataOfGenre = MovieManager.getInstance().getDataOfGenres();

        //setText
        titleGenre = (TextView) findViewById(R.id.title_a7);
        for (int i = 0; i < dataOfGenre.size(); i++)
        {
            if( idGenre == dataOfGenre.get(i).getId() )
            {
                titleGenre.setText(dataOfGenre.get(i).getName());
            }
        }

        //button
        deleteGenre = (Button) findViewById(R.id.delete_a7);
        deleteGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieManager.getInstance().deleteGenre(idGenre);
            }
        });

        //list
        genreAppear = (ListView) findViewById(R.id.list_a7);
        adapterGenreAppear = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for(int i = 0 ; i < dataOfMovies.size(); i++)
        {
            for(int j = 0; j < dataOfMovies.get(i).getGenre().length; j++)
            {
                if(idGenre == dataOfMovies.get(i).getGenre(j))
                {
                    adapterGenreAppear.add(dataOfMovies.get(i).getTitle());
                }
            }
        }
        genreAppear.setAdapter(adapterGenreAppear);
    }
}
