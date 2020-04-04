package com.example.cristina.a5listview1.dataModel;

import android.util.Log;

public class Movie {

    private int id;
    private String title;
    private String description;
    private String director;
    private int year;
    private int runtime;
    private float rating;
    private int votes;
    private float revenue;
    private int[] genres = null;
    private int[] actors = null;

    private transient  boolean favorite = false;

    public Movie(int id, String title, String description, String director, int year, int runtime,
                 float rating, int votes, float revenue, int[] genres, int[] actors ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
        this.year = year;
        this.runtime= runtime;
        this.rating = rating;
        this.votes = votes;
        this.revenue = revenue;
        this.genres = genres;
        this.actors = actors;
    }

    //GET
    public int getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public String getDirector() {
        return this.director;
    }
    public int getYear() {
        return this.year;
    }
    public int getRuntime() { return this.runtime; }
    public float getRating() { return this.rating; }
    public int getVotes() { return this.votes; }
    public float getRevenue() { return this.revenue; }
    public int[] getGenre() {
        Log.i("tengo tantos generos","  m "+genres.length);
        return this.genres;
    }
    public int getGenre(int i) {return this.genres[i];}
    public int[] getActors() { return this.actors; }
    public int getActor(int i) { return this.actors[i];}
    public boolean getFavorite() {return this.favorite;}

    //SET
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
         this.description = description;
    }
    public void setDirector(String director) {
         this.director = director;
    }
    public void setYear(int year) {
         this.year = year;
    }
    public void setRuntime(int runtime) {  this.runtime = runtime; }
    public void setRating(float rating) {  this.rating = rating; }
    public void setVotes(int votes) {  this.votes = votes; }
    public void setRevenue(float revenue) {  this.revenue = revenue; }
    public void setGenre(int[] genres) {this.genres = genres;}
    public void setActors(int[] actors) {this.actors = actors;}
    public void setFavorite(boolean favorite){this.favorite = favorite;
    if (favorite)Log.i("estoy poniendolo a true","true"+id);}
}
