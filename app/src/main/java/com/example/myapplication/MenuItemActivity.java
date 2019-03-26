package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    /** Displays the menu item attributes: title, price, description and image.
     * Using Picasso for loading the images from internet and turn into ImageViews. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("item");
        setTitle(item.getName());

        TextView p = (TextView) findViewById(R.id.name);
        String price = Integer.toString(item.getPrice()) + " euro";
        p.setText(price);

        TextView description = findViewById(R.id.description);
        description.setText(item.getDescription());

        ImageView image = findViewById(R.id.image);
        Picasso.with(getApplicationContext()).load(item.getImageUrl()).into(image);
    }
}
