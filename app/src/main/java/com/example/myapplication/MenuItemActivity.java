package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("item");
        setTitle(item.getName());

//        new DownloadImageTask((ImageView) findViewById(R.id.image)).execute(item.getImageUrl());
        TextView p = (TextView) findViewById(R.id.name);
        String price = "\u20BF"+ Integer.toString(item.getPrice());
        p.setText(price);

        TextView description = findViewById(R.id.description);
        description.setText(item.getDescription());

        ImageView image = findViewById(R.id.image);
        Picasso.with(getApplicationContext()).load(item.getImageUrl()).into(image);
    }
}
