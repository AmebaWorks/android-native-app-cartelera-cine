package com.example.cristina.a5listview1.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristina.a5listview1.R;
import com.example.cristina.a5listview1.dataModel.Movie;
import com.example.cristina.a5listview1.dataModel.MovieManager;

import java.util.ArrayList;

public class A2_MovieList extends AppCompatActivity {

    public CustomAdapter adapter;
    public CustomAdapter adapterFavs;
    public ListView listView;
    static final int TAKE_A_RESULT = 1;
    private int moviePosition = 0;
    private int[] arrayFavorites;
    private ArrayList<Movie> dataOfMovies;
    private boolean seeFavs = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex3);

        dataOfMovies = MovieManager.getInstance().getDataOfMovies();

        GetSavedText();

        adapter = new CustomAdapter(this, R.layout.customlayout, dataOfMovies);
        adapter.notifyDataSetChanged();

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        addListener();

    }

    //Menu toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    void GetSavedText(){

        SharedPreferences sharedPref = this.getPreferences(this.MODE_PRIVATE);
        String savedText = sharedPref.getString("Saved text", "");
        String[] arrayString = savedText.split(",");
        arrayFavorites = new int[arrayString.length];

        for(int i = 0; i < arrayString.length; i++)
        {
            if(arrayString[i]!= null && !arrayString[i].equals("") && Integer.parseInt(arrayString[i]) != 0) {
                arrayFavorites[i] = Integer.parseInt(arrayString[i]) ;
                if(MovieManager.getInstance().getItembyId(arrayFavorites[i]) != null)
                MovieManager.getInstance().getItembyId(arrayFavorites[i]).setFavorite(true);
            }
        }
    }

    public void SaveText(){
        String textToSave = new String();
        for(int i  = 0; i < arrayFavorites.length; i++)
        {
            if(arrayFavorites[i]!=0)
            textToSave += arrayFavorites[i]+",";
        }
        SharedPreferences sharedPref = this.getPreferences(this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Saved text", textToSave);
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_contact:
                Intent intent = new Intent(A2_MovieList.this, A3_Contact.class);
                startActivity(intent);
                return true;
            case R.id.action_search:
                //desplegar autocompletar con pelis


                return true;
            case R.id.action_favorites:
                if(seeFavs) {
                    ArrayList<Movie> arrayMoviesFav = new ArrayList<Movie>();
                    for (int i = 0; i < dataOfMovies.size(); i++) {
                        for (int j = 0; j < arrayFavorites.length; j++) {
                            if (dataOfMovies.get(i).getId() == arrayFavorites[j])
                                arrayMoviesFav.add(dataOfMovies.get(i));
                        }

                    }
                    adapterFavs = new CustomAdapter(this, R.layout.customlayout, arrayMoviesFav);
                    listView.setAdapter(adapterFavs);
                    adapterFavs.notifyDataSetChanged();
                    seeFavs = false;
                }else
                {
                    listView.setAdapter(adapter);
                    adapterFavs.notifyDataSetChanged();
                    seeFavs = true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
                moviePosition = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(A2_MovieList.this);
                builder.setCancelable(true);
                builder.setTitle("Options");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(A2_MovieList.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("VIEW");
                arrayAdapter.add("EDIT");
                arrayAdapter.add("DELETE");
                if(MovieManager.getInstance().getItem(i).getFavorite())
                {
                    arrayAdapter.add("IT IS FAVORITE");
                }
                else arrayAdapter.add("SAVE FAVORITE");

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(A2_MovieList.this);

                        if(strName.equals("VIEW")){
                            Intent intent = new Intent(A2_MovieList.this, A5_ViewMovie.class);
                            intent.putExtra("position_movie", moviePosition);
                            startActivity(intent);
                        }
                        if(strName.equals("EDIT")){
                            Intent intent = new Intent(A2_MovieList.this, A4_EditMovie.class);
                            intent.putExtra("position_movie", moviePosition);
                            startActivity(intent);
                        }

                        if(strName.equals("DELETE")){
                            Toast toast1 = Toast.makeText(getApplicationContext(),"Movie "+ moviePosition +" has been deleted", Toast.LENGTH_SHORT);
                            toast1.show();

                            MovieManager.getInstance().deleteMovie(moviePosition);
                            adapter.notifyDataSetChanged();
                        }

                        if(strName.equals("SAVE FAVORITE"))
                        {
                            int[] copyarrayFavorites = arrayFavorites;
                            arrayFavorites = new int[copyarrayFavorites.length+1];

                            for(int i = 0; i < copyarrayFavorites.length; i++)
                            {
                                arrayFavorites[i] = copyarrayFavorites[i];
                            }
                            arrayFavorites[copyarrayFavorites.length] = MovieManager.getInstance().getItem(moviePosition).getId();
                            MovieManager.getInstance().getItem(moviePosition).setFavorite(true);
                        }
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    public void add_Movie(View view)
    {
        Intent intent = new Intent(A2_MovieList.this, A6_AddMovie.class);
        startActivityForResult(intent, TAKE_A_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == TAKE_A_RESULT) {
            if(resultCode == RESULT_OK)
            {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onStop() {
        SaveText();
        super.onStop();
    }
}
