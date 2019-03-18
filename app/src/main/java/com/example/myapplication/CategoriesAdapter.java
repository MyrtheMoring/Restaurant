package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriesAdapter extends ArrayAdapter {

    private ArrayList<String> listCategories;

    // Create constructor
    public CategoriesAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        listCategories = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Load item if it has not been loaded before
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_category, parent, false);
        }

        String category = listCategories.get(position);
        TextView categoryView = convertView.findViewById(R.id.category_text);
        categoryView.setText(category);

        return convertView;
    }
}
