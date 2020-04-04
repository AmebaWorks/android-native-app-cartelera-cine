package com.example.cristina.a5listview1.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cristina.a5listview1.R;
import com.example.cristina.a5listview1.ShowDialog;
import com.example.cristina.a5listview1.dataModel.Actor;
import com.example.cristina.a5listview1.dataModel.Genre;
import com.example.cristina.a5listview1.dataModel.Movie;
import com.example.cristina.a5listview1.dataModel.MovieManager;

import java.util.ArrayList;

public class A6_AddMovie extends AppCompatActivity {

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

    private ShowDialog showDialog;
    private Intent returnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a6__add_movie);

        returnIntent = new Intent();

        title = (EditText) findViewById(R.id.title_I_added);
        description = (EditText) findViewById(R.id.description_I_added);
        director = (EditText) findViewById(R.id.director_I_added);
        year = (EditText) findViewById(R.id.year_I_added);
        runtime = (EditText) findViewById(R.id.runtime_I_added);
        rating = (EditText) findViewById(R.id.rating_I_added);
        votes = (EditText) findViewById(R.id.votes_I_added);
        revenue = (EditText) findViewById(R.id.revenue_I_added);

        buttonAddG = (Button) findViewById(R.id.addG_a6);
        buttonAddG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog = new ShowDialog("Add Genre", A6_AddMovie.this);
                genres = showDialog.getGenres();
            }
        });

        buttonAddA = (Button) findViewById(R.id.addA_a6);
        buttonAddA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog = new ShowDialog("Add Actor", A6_AddMovie.this);
                actors = showDialog.getActors();
            }
        });

        accept = (Button) findViewById(R.id.accept_added);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.getText().toString().equals("") || description.getText().toString().equals("") || director.getText().toString().equals("") ||
                        year.getText().toString().equals("") || runtime.getText().toString().equals("") || rating.getText().toString().equals("") ||
                        votes.getText().toString().equals("") || revenue.getText().toString().equals("") ) {

                    Toast toast1 = Toast.makeText(getApplicationContext(),"Error, a cell has null", Toast.LENGTH_SHORT);
                    toast1.show();
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                }
                else
                {
                    int id = MovieManager.getInstance().getItem(MovieManager.getInstance().getDataOfMovies().size()-1).getId()+1;
                    int yearInt = Integer.parseInt(year.getText().toString());
                    int lengthInt = Integer.parseInt(runtime.getText().toString());
                    int votesF = Integer.parseInt(votes.getText().toString());
                    float revenueF = Float.parseFloat(revenue.getText().toString());
                    float ratingF = Float.parseFloat(rating.getText().toString());

                    Movie movie = new Movie(id ,title.getText().toString(), description.getText().toString(), director.getText().toString(),
                            yearInt, lengthInt, ratingF, votesF, revenueF, genres, actors);

                    MovieManager.getInstance().addMovie(movie);

                    setResult(Activity.RESULT_OK, returnIntent);
                }

                finish();
            }
        });
    }
}
