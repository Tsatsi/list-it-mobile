package com.thoughtworks.list_it_mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class RegisterActivity extends Activity {

    EditText userNameEditText;
    EditText emailEditText;
    private Api api;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameEditText = (EditText) findViewById(R.id.username);
        emailEditText = (EditText) findViewById(R.id.email);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("http://10.112.124.128:5000")
                .setConverter(new GsonConverter(gson))
                .build();

        api = retrofit.create(Api.class);
    }


    public void registerUser(View view) {
        String username = userNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        api.registerUser(new User(username, email), new Callback<User>() {

            @Override
            public void success(User user, Response response) {

                saveResponseToSharedPreferences(user);
                startActivity(new Intent(context, ListItemActivity.class));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Registration", error.toString());
            }
        });

    }

    private void saveResponseToSharedPreferences(User user) {

        SharedPreferences sharedPref = getSharedPreferences("com.thoughtworks.list_it_mobile.preference_file_key", Context.MODE_PRIVATE);
        int userId = user.getId();
        sharedPref.edit().putString("id", Integer.toString(userId)).apply();
        sharedPref.edit().putString("email", user.getEmail()).apply();
        sharedPref.edit().putString("username", user.getUserName()).apply();
    }
}
