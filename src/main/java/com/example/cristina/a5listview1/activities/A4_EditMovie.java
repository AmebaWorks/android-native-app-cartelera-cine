package com.example.cristina.a5listview1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cristina.a5listview1.R;
import com.example.cristina.a5listview1.ShowDialog;
import com.example.cristina.a5listview1.dataModel.Movie;
import com.example.cristina.a5listview1.dataModel.MovieManager;

public class A4_EditMovie extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private EditText director;
    private EditText year;
    private EditText runtime;
    private EditText rating;
    private EditText votes;
    private EditText revenue;
    private Button buttonAddA;
    private Button buttonAddG;
    private Button accept;

    private int[] genres = new int[10];
    private int[] actors = new int[10];
    private boolean genresNull = true;
    private boolean actorsNull = true;

    private ShowDialog showDialog;
    private int pos_movie;
    private Movie copyMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a6__add_movie);

        pos_movie = getIntent().getExtras().getInt("position_movie");

        copyMovie = MovieManager.getInstance().getItem(pos_movie);

        title = (EditText) findViewById(R.id.title_I_added);
        description = (EditText) findViewById(R.id.description_I_added);
        director = (EditText) findViewById(R.id.director_I_added);
        year = (EditText) findViewById(R.id.year_I_added);
        runtime = (EditText) findViewById(R.id.runtime_I_added);
        rating = (EditText) findViewById(R.id.rating_I_added);
        votes = (EditText) findViewById(R.id.votes_I_added);
        revenue = (EditText) findViewById(R.id.revenue_I_added);

        genres = MovieManager.getInstance().getItem(pos_movie).getGenre();
        actors = MovieManager.getInstance().getItem(pos_movie).getActors();

        buttonAddG = (Button) findViewById(R.id.addG_a6);
        buttonAddG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog = new ShowDialog("Add Genre", A4_EditMovie.this);
                genres = showDialog.getGenres();
            }
        });

        buttonAddA = (Button) findViewById(R.id.addA_a6);
        buttonAddA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog = new ShowDialog("Add Actor", A4_EditMovie.this);
                actors = showDialog.getActors();
            }
        });

        title.setText(copyMovie.getTitle());
        director.setText(copyMovie.getDirector());
        description.setText(copyMovie.getDescription());
        String yearString = Integer.toString(copyMovie.getYear());
        year.setText(yearString);
        String runtimeString = Integer.toString(copyMovie.getRuntime());
        runtime.setText(runtimeString);
        String ratingString = Float.toString(copyMovie.getRating());
        rating.setText(ratingString);
        String votesString = Integer.toString(copyMovie.getVotes());
        votes.setText(votesString);
        String revenueString = Float.toString(copyMovie.getRevenue());
        revenue.setText(revenueString);

        accept = (Button) findViewById(R.id.accept_added);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(title.getText().toString().equals("") || director.getText().toString().equals("") || description.getText().toString().equals("")||
                     year.getText().toString().equals("") || runtime.getText().toString().equals("") ||votes.getText().toString().equals("")||
                     revenue.getText().toString().equals("") || rating.getText().toString().equals("") || genresNull)
                {
                    Toast.makeText(A4_EditMovie.this,"Error, a cell has null the change not been do it",Toast.LENGTH_LONG).show();
                }
                else
                {
                    copyMovie.setTitle(title.getText().toString());
                    copyMovie.setDirector(director.getText().toString());
                    copyMovie.setDescription(description.getText().toString());
                    copyMovie.setYear(Integer.parseInt(year.getText().toString()));
                    copyMovie.setRuntime(Integer.parseInt(runtime.getText().toString()));
                    copyMovie.setRating(Float.parseFloat(rating.getText().toString()));
                    copyMovie.setVotes(Integer.parseInt(votes.getText().toString()));
                    copyMovie.setRevenue(Float.parseFloat(revenue.getText().toString()));
                    copyMovie.setGenre(genres);
                    copyMovie.setActors(actors);

                    MovieManager.getInstance().updateMovie(copyMovie);
                }
                finish();
            }
        });
    }
}
