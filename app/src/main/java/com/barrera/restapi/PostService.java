package com.barrera.restapi;

import com.barrera.restapi.Models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostService {

    @GET("/posts.json/")
    Call<ArrayList<Post>> getPosts();

    @GET("/posts/{id}.json/")
    Call<Post> getPost(@Path("id") int id);

    @PUT("/posts/{id}.json/")
    Call<Post> savePost(@Body Post post, @Path("id") int id);
}
