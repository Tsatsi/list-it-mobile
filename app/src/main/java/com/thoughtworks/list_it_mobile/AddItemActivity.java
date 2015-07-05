package com.thoughtworks.list_it_mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class AddItemActivity extends Activity {

    Api api;
    EditText itemNameEditText;
    EditText descriptionEditText;
    Spinner prioritySpinner;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemNameEditText = (EditText) findViewById(R.id.itemName);
        descriptionEditText = (EditText) findViewById(R.id.description);
        prioritySpinner = (Spinner) findViewById(R.id.priority_spinner);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("http://10.112.124.128:5000")
                .setConverter(new GsonConverter(gson))
                .build();

        api = retrofit.create(Api.class);
    }


    public void addItem(View view) {

        String itemName = itemNameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String priority = prioritySpinner.getSelectedItem().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("com.thoughtworks.list_it_mobile.preference_file_key", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("id","");

        api.addItem(new Item(Integer.parseInt(userId), itemName, priority, description), new Callback<Item>() {
            @Override
            public void success(Item item, Response response) {
                startActivity(new Intent(context, ListItemActivity.class));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Registration", error.toString());
            }
        });

    }

}
