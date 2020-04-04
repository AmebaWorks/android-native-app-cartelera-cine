package com.example.cristina.a5listview1.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cristina.a5listview1.Constants;
import com.example.cristina.a5listview1.R;
import com.example.cristina.a5listview1.dataModel.Actor;
import com.example.cristina.a5listview1.dataModel.Movie;
import com.example.cristina.a5listview1.dataModel.MovieManager;
import com.example.cristina.a5listview1.utils.AsynkGet;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.cristina.a5listview1.Constants.fileDeleteActor;

public class A7_Actors extends AppCompatActivity {

    private TextView titleActor;
    private ListView actorAppear;
    private Button deleteActor;
    private int idActor;
    private ArrayList<Movie> dataOfMovies;
    private ArrayList<Actor> dataOfActor;
    private ArrayAdapter<String> adapterActorAppear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a7__actors);

        idActor = getIntent().getExtras().getInt("actor");

        dataOfMovies = MovieManager.getInstance().getDataOfMovies();
        dataOfActor = MovieManager.getInstance().getDataOfActors();

        //setText
        titleActor = (TextView) findViewById(R.id.title_a7);
        for (int i = 0; i < dataOfActor.size(); i++)
        {
            if( idActor == dataOfActor.get(i).getId() )
            {
                titleActor.setText(dataOfActor.get(i).getName());
            }
        }

        //button
        deleteActor = (Button) findViewById(R.id.delete_a7);
        deleteActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MovieManager.getInstance().deleteActor(idActor);
            }
        });


        //list
        actorAppear = (ListView) findViewById(R.id.list_a7);
        adapterActorAppear = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for(int i = 0 ; i < dataOfMovies.size(); i++)
        {
            for(int j = 0; j < dataOfMovies.get(i).getActors().length; j++)
            {
                if(idActor == dataOfMovies.get(i).getActor(j))
                {
                    adapterActorAppear.add(dataOfMovies.get(i).getTitle());
                }
            }
        }
        actorAppear.setAdapter(adapterActorAppear);
    }
}
