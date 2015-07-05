package com.thoughtworks.list_it_mobile;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.list_it_mobile.Adapters.ItemListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class ListItemActivity extends Activity {

    private Api api;
    List<Item> userItems = new ArrayList<Item>();
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        SharedPreferences sharedPreferences = getSharedPreferences("com.thoughtworks.list_it_mobile.preference_file_key", Context.MODE_PRIVATE);
        final String userId = sharedPreferences.getString("id", "");

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("http://10.112.124.128:5000")
                .setConverter(new GsonConverter(gson))
                .build();

        api = retrofit.create(Api.class);

        api.getUserItems(Integer.parseInt(userId), new Callback<List<Item>>() {
            @Override
            public void success(List<Item> items, Response response) {

                userItems = items;
                ListView listView = (ListView) findViewById(R.id.listItems);
                ItemListAdapter itemListAdapter = new ItemListAdapter(context,R.layout.row_layout,userItems);
                listView.setAdapter(itemListAdapter);
                Toast toast = Toast.makeText(context, "success : " + userId, Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast toast = Toast.makeText(context, error.toString(), Toast.LENGTH_LONG);
                toast.show();
                Log.d("Registration", error.toString());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        startActivity(new Intent(context, AddItemActivity.class));

        return true;
    }

}
