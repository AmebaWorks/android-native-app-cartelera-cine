package com.example.cristina.a5listview1.activities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cristina.a5listview1.R;
import com.example.cristina.a5listview1.dataModel.Movie;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private int customLayout;
    private ArrayList<Movie> data;

    public CustomAdapter(Context context, int customlayout, ArrayList<Movie> data) {
        this.data = data;
        this.context = context;
        this.customLayout = customlayout;
    }

    @Override
    public int getCount() {

        return data.size();
    }

    @Override
    public Object getItem(int i) {

        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(customLayout, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Movie movie = (Movie) getItem(i);
        viewHolder.movieTitle.setText(movie.getTitle());

        String ratingString = Float.toString(movie.getRating());
        viewHolder.movieRating.setText(ratingString+"/10");

        String yearString = Integer.toString(movie.getYear());
        viewHolder.movieYear.setText("Year: " + yearString);

        String runtimeString = Integer.toString(movie.getRuntime());
        viewHolder.movieRuntime.setText(runtimeString + "min");

        if(movie.getFavorite()) {
            viewHolder.imgFav.setImageResource(R.drawable.favorites);
            Log.i("oyeeeeeeeee ","que pinto amarilloooooOO");
        }
        else
        {
            viewHolder.imgFav.setImageResource(R.drawable.favorite_sin);
        }

        return view;
    }

    private class ViewHolder
    {
        TextView movieTitle;
        TextView movieRating;
        TextView movieYear;
        TextView movieRuntime;
        ImageView imgFav;

        public ViewHolder(View view) {
            movieTitle = (TextView)view.findViewById(R.id.title_custom);
            movieRating = (TextView) view.findViewById(R.id.rating_custom);
            movieYear = (TextView) view.findViewById(R.id.year_custom);
            movieRuntime = (TextView) view.findViewById(R.id.runtime_custom);
            imgFav = (ImageView) view.findViewById(R.id.imgFav);
        }
    }
}
