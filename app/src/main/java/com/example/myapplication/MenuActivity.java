package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemRequest.Callback{

    /** In the onCreate function, there will be a request made to load to menus. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        MenuItemRequest menurequest = new MenuItemRequest(this);
        menurequest.getMenuItems(this, category);
    }

    /**If the menu items are loaded set adapter to the listView*/
    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {

        MenuItemAdapter menu_adapter = new MenuItemAdapter(this, R.layout.content_category, menuItems);
        ListView listView = (ListView) findViewById(R.id.menu_list);
        listView.setAdapter(menu_adapter);
        listView.setOnItemClickListener(new MenuItemClickListener());
    }

    /** If there is a problem loading the categories, display the error. */
    @Override
    public void gotMenuItemsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class MenuItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);
            intent.putExtra("item", menuItem);
            startActivity(intent);
        }
    }



}
