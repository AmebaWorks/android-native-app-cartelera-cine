package com.example.cristina.a5listview1.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.cristina.a5listview1.R;
import com.example.cristina.a5listview1.dataModel.Actor;
import com.example.cristina.a5listview1.dataModel.Genre;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AutocompleteGenreAdapter extends BaseAdapter implements Filterable {

    private List<Genre> originalItems;
    private List<Genre> filteredItems;
    private int layoutResource;
    private Context context;

    public AutocompleteGenreAdapter(Context context, int resource, List<Genre> items) {
        this.context = context;
        this.layoutResource = resource;
        this.originalItems = items;
        this.filteredItems = new ArrayList<Genre>(items);
    }

    @Override
    public int getCount() {
        return filteredItems.size();
    }

    @Override
    public Genre getItem(int position) {
        return filteredItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Genre item = getItem(position);
        if(convertView == null) {
            convertView = inflater.inflate(layoutResource, parent, false);
        }
        ((TextView) convertView).setText(item.getName());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new AutocompleteGenreAdapter.SpecialFilter();
    }

    private class SpecialFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            // If there is no constraint, just return the original list of items
            if (constraint == null || constraint.length() == 0) {
                ArrayList<Genre> list = new ArrayList<Genre>(originalItems);
                results.values = list;
                results.count = list.size();
            }
            // Else, only return the strings that start with the constraint
            else {
                String prefixString = constraint.toString().toLowerCase();
                ArrayList<Genre> values = new ArrayList<Genre>(originalItems);
                final ArrayList<Genre> newValues = new ArrayList<Genre>();

                for (Genre value : values) {
                    final String valueText = value.getName().toLowerCase();
                    if (valueText.startsWith(prefixString)) {
                        newValues.add(value);
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredItems = (List<Genre>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
