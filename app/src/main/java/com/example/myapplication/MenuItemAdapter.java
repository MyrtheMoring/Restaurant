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

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> listMenuItems;

    /** Constructor for the adapter. */
    public MenuItemAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        listMenuItems = objects;
    }

    /** Checks if the item has been loaded before and returns the convertview. */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Load item if it has not been loaded before
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_menu, parent, false);
        }

        MenuItem menuItem = listMenuItems.get(position);

        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.price);
        ImageView imageView = convertView.findViewById(R.id.image);

        name.setText(menuItem.getName());
        price.setText("\u20ac" + Integer.toString(menuItem.getPrice()));
        Picasso.with(getContext()).load(menuItem.getImageUrl()).into(imageView);

        return convertView;
    }
}
