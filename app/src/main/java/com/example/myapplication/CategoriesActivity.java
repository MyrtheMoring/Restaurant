package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {


    CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        CategoriesRequest categories_request = new CategoriesRequest(this);
        categories_request.getCategories(this);

        // If the listView is clicked go to CategoryClickListener
//        listView.setOnItemClickListener(new CategoryClickListener());
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        ListView listview = findViewById(R.id.menu_list);
        categoriesAdapter = new CategoriesAdapter(this, R.layout.content_category, categories);
        listview.setAdapter(categoriesAdapter);
        listview.setOnItemClickListener(new CategoryClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    private class CategoryClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);

            String category = parent.getItemAtPosition(position).toString();
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
