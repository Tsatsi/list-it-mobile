package com.thoughtworks.list_it_mobile;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface Api {

    @POST("/api/items")
    void addItem(@Body Item item, Callback<Item> callback);

    @GET("/api/user/items/{id}")
    void getUserItems(@Path("id") int id,Callback< List<Item> > callback);

    @PUT("/api/items/{id}")
    void updateItem(@Path("id") int itemId, Callback<Item> callback);

    @DELETE("/api/items/{id}")
    void deleteItem(@Path("id") int itemId, Callback<Item> callback);

    @POST("/api/user/register")
    void registerUser(@Body User user, Callback<User> callback);

}



