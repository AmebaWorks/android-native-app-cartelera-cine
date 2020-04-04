package com.example.cristina.a5listview1.dataModel;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.cristina.a5listview1.Constants;
import com.example.cristina.a5listview1.utils.AsynkGet;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MovieManager {

    private static MovieManager instance = null;
    private ArrayList<Movie> dataOfMovies;
    private ArrayList<Genre> dataOfGenres;
    private ArrayList<Actor> dataOfActors;

    //constructor
    private MovieManager()
    {
        dataOfMovies = new ArrayList<Movie>();
        dataOfGenres = new ArrayList<Genre>();
        dataOfActors = new ArrayList<Actor>();
    }

    //Singleton
    public static MovieManager getInstance()
    {
        if(instance == null)
        {
            instance = new MovieManager();
        }
        return instance;
    }

    public Movie getItem(int i)
    {
        return dataOfMovies.get(i);
    }

    public Movie getItembyId(int id)
    {
        for(int i  = 0; i < dataOfMovies.size(); i++)
        {
            if(dataOfMovies.get(i).getId() == id)
            {
                return dataOfMovies.get(i);
            }
        }
        return null;
    }
    public ArrayList<Movie> getDataOfMovies(){ return dataOfMovies;}
    public ArrayList<Genre> getDataOfGenres(){ return dataOfGenres;}
    public ArrayList<Actor> getDataOfActors(){ return dataOfActors;}

    public void deleteMovie(int posMov) {
        String stringDeleteMovie = "{\"id\":\""+posMov+"\"}";

        @SuppressLint("StaticFieldLeak")
        AsynkGet deleteM = new AsynkGet(Constants.baseUrl+Constants.fileDeleteMovie+Constants.userPass, stringDeleteMovie) {
            @Override
            protected void onPostExecute(String string) {

            }
        };
        deleteM.execute();
        dataOfMovies.remove(posMov);
    }


    public void deleteActor(int idActor){

        String stringDeleteMovie = "{\"id\":\""+idActor+"\"}";

        @SuppressLint("StaticFieldLeak")
        AsynkGet deleteActor = new AsynkGet(Constants.baseUrl+ Constants.fileDeleteActor +Constants.userPass, stringDeleteMovie) {
            @Override
            protected void onPostExecute(String string) {

            }
        };
        deleteActor.execute();

        for(int j = 0; j < dataOfActors.size(); j++)
        {
            if(dataOfActors.get(j).getId() == idActor)
            {
                dataOfActors.remove(j);
            }
        }
    }

    public void deleteGenre(int idGenre){

        String stringDeleteMovie = "{\"id\":\""+idGenre+"\"}";

        @SuppressLint("StaticFieldLeak")
        AsynkGet deleteGenre = new AsynkGet(Constants.baseUrl+ Constants.fileDeleteGenre +Constants.userPass, stringDeleteMovie) {
            @Override
            protected void onPostExecute(String string) {

            }
        };
        deleteGenre.execute();

        for(int j = 0; j < dataOfGenres.size(); j++)
        {
            if(dataOfGenres.get(j).getId() == idGenre)
            {
                dataOfGenres.remove(j);
            }
        }
    }

    public void updateMovie(Movie movie)
    {
        Gson gson = new Gson();
        String stringMovie = gson.toJson(movie);

        @SuppressLint("StaticFieldLeak")
        AsynkGet updateMovie = new AsynkGet(Constants.baseUrl + Constants.fileUpadteMovie + Constants.userPass, stringMovie) {
            @Override
            protected void onPostExecute(String string) {

            }
        };
        updateMovie.execute();
    }

    public void addMovie(Movie movie)
    {
        Gson gson = new Gson();
        String stringMovie = gson.toJson(movie);

        @SuppressLint("StaticFieldLeak")
        AsynkGet movieAdded = new AsynkGet(Constants.baseUrl + Constants.fileAddMovie + Constants.userPass, stringMovie) {
            @Override
            protected void onPostExecute(String string) {

            }
        };
        movieAdded.execute();
        copyMovie(movie);
    }

    public int addGenre(String nameGenre)
    {
        int id = dataOfGenres.size()+1;
        String stringGenreAddded = "{\"id\":\""+id+"\",\"name\":\""+nameGenre+"\"}";

        @SuppressLint("StaticFieldLeak")
        AsynkGet deleteGenre = new AsynkGet(Constants.baseUrl+ Constants.fileAddGenre +Constants.userPass, stringGenreAddded) {
            @Override
            protected void onPostExecute(String string) {

            }
        };
        deleteGenre.execute();

        dataOfGenres.add(new Genre(id, nameGenre));
        return id;
    }

    public int addActor(String nameActor)
    {
        int id = dataOfActors.size()+2;
        String stringActorAddded = "{\"id\":\""+id+"\",\"name\":\""+nameActor+"\"}";

        @SuppressLint("StaticFieldLeak")
        AsynkGet deleteActor = new AsynkGet(Constants.baseUrl+ Constants.fileAddActor +Constants.userPass, stringActorAddded) {
            @Override
            protected void onPostExecute(String string) {

            }
        };
        deleteActor.execute();

        dataOfActors.add(new Actor(id, nameActor));
        return id;
    }

    public void copyMovie(Movie movie){ dataOfMovies.add(movie); }

    public void copyGenre(Genre genre)
    {
        dataOfGenres.add(genre);
    }

    public void copyActor(Actor actor)
    {
        dataOfActors.add(actor);
    }
}
