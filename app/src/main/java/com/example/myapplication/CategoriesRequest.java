package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private static Context context;
    private ArrayList<String> categories_array = new ArrayList<String>();
    private String cat;
    private static Callback activity_set = null;

    public CategoriesRequest(Context c) {
        context = c;
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public void getCategories(Callback activity){
        activity_set = activity;
        RequestQueue request_queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/categories";
        JsonObjectRequest json_request = new JsonObjectRequest(url, null, this, this);
        request_queue.add(json_request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity_set.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            JSONArray categories_json = response.getJSONArray("categories");
            for (int i = 0; i < categories_json.length(); i++) {
                cat = categories_json.getString(i);
                if (cat != null) {
                    categories_array.add(cat);
                }
            }
            activity_set.gotCategories(categories_array);
        }
        catch (JSONException e){
            Log.d("Error JSON", e.toString());

        }

    }
}
