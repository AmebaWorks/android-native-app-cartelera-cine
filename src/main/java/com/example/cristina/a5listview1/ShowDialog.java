package com.example.cristina.a5listview1;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cristina.a5listview1.activities.A6_AddMovie;
import com.example.cristina.a5listview1.activities.AutocompleteActorAdapter;
import com.example.cristina.a5listview1.activities.AutocompleteGenreAdapter;
import com.example.cristina.a5listview1.dataModel.Actor;
import com.example.cristina.a5listview1.dataModel.Genre;
import com.example.cristina.a5listview1.dataModel.MovieManager;

import java.util.ArrayList;

/**
 * Created by Cristina on 29/04/2018.
 */

public class ShowDialog {

    private AlertDialog.Builder builder;
    private View alertView;
    private AutoCompleteTextView autocompleteTV;
    private int counter = 0;
    private ListView list;
    private ArrayAdapter<String> listSelected;
    private boolean isOnList = false;

    //Genre
    private AutocompleteGenreAdapter adapterAutocompleteG;
    private ArrayList<Genre> dataOfGenre;
    private Genre selectedG;
    //Movie
    private AutocompleteActorAdapter adapterAutocompleteA;
    private ArrayList<Actor> dataOfActor;
    private Actor selectedA;

    private int[] genres = new int[10];
    private int[] actors = new int[10];

    public ShowDialog(String title, final Context context){
        dataOfActor = MovieManager.getInstance().getDataOfActors();
        dataOfGenre = MovieManager.getInstance().getDataOfGenres();

        builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);

        alertView = View.inflate(context,R.layout.alert_dialog_custom, null);
        autocompleteTV = (AutoCompleteTextView) alertView.findViewById(R.id.autoCompleteDialog);

        list = (ListView) alertView.findViewById(R.id.listDialog);
        listSelected = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
        list.setAdapter(listSelected);

        Button addFromAutocomplete = (Button) alertView.findViewById(R.id.addAutocompl);

        if(title.equals("Add Actor")){
            adapterAutocompleteA = new AutocompleteActorAdapter(context, android.R.layout.simple_dropdown_item_1line, dataOfActor);
            autocompleteTV.setAdapter(adapterAutocompleteA);
            autocompleteTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    selectedA = (Actor) parent.getItemAtPosition(position);
                    autocompleteTV.setText(selectedA.getName());
                    isOnList = true;
                    Toast.makeText(context,"You Selected Actor "+selectedA.getName(),Toast.LENGTH_LONG).show();
                }
            });

            addFromAutocomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isOnList) {
                        int id = MovieManager.getInstance().addActor(autocompleteTV.getText().toString());
                        actors[counter] = id;
                    } else {
                        actors[counter] = selectedA.getId();
                    }

                    counter++;

                    listSelected.add(autocompleteTV.getText().toString());
                    listSelected.notifyDataSetChanged();

                    autocompleteTV.setText("");
                    isOnList = false;
                }
            });

        }
        else {
            adapterAutocompleteG = new AutocompleteGenreAdapter(context, android.R.layout.simple_dropdown_item_1line, dataOfGenre);
            autocompleteTV.setAdapter(adapterAutocompleteG);
            autocompleteTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    selectedG = (Genre) parent.getItemAtPosition(position);
                    autocompleteTV.setText(selectedG.getName());
                    isOnList = true;
                }
            });

            addFromAutocomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isOnList) {
                        int id = MovieManager.getInstance().addGenre(autocompleteTV.getText().toString());
                        genres[counter] = id;

                    } else
                    {
                        genres[counter] = selectedG.getId();
                    }

                    counter++;

                    listSelected.add(autocompleteTV.getText().toString());
                    listSelected.notifyDataSetChanged();

                    autocompleteTV.setText("");
                    isOnList = false;
                }
            });
        }

        builder.setView(alertView);
        builder.create();
        builder.show();
    }

    public int[] getGenres(){ return genres;}
    public int[] getActors(){ return actors;}

}
