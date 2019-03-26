package com.example.myapplication;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private static Callback activity_set;
    private ArrayList<MenuItem> menuitems_list = new ArrayList<MenuItem>();
    private static Context context;

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> categories);
        void gotMenuItemsError(String message);
    }

    public MenuItemRequest(Context c) {
        context = c;
    }

    /** Get the menu items from the JSON URL request. */
    public void getMenuItems(Callback activity, String category) {

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://resto.mprog.nl/menu?category="+category;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);

        activity_set = activity;
    }

    /** This method is called when there is an error with the volley. */
    @Override
    public void onErrorResponse(VolleyError error) {
        activity_set.gotMenuItemsError(error.getMessage());
    }

    /** This method is called when there is no error with the volley.
     * It will extract all the items from the response object and add them to the MenuList. */
    @Override
    public void onResponse(JSONObject response) {

        try {
            JSONArray menuitems_json = response.getJSONArray("items");
            for (int i = 0; i < menuitems_json.length(); i++) {
                JSONObject item = menuitems_json.getJSONObject(i);
                String name = item.getString("name");
                String description= item.getString("description");
                String image_url = item.getString("image_url");
                int price =item.getInt("price");
                String category = item.getString("category");
                MenuItem menuItem = new MenuItem(name,description, image_url, price, category);
                menuitems_list.add(menuItem);
            }
            activity_set.gotMenuItems(menuitems_list);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }
}
