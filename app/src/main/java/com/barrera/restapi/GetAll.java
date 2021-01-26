package com.barrera.restapi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.barrera.restapi.Adapters.GetAllAdapter;
import com.barrera.restapi.Models.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAll extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all);

        rv = findViewById(R.id.getAllRV);
        LinearLayoutManager manager = new LinearLayoutManager(GetAll.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(manager);

        getPosts();
    }

    private void getPosts() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://juanpablo-1cddc-default-rtdb.firebaseio.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PostService postService = retrofit.create(PostService.class);
        Call<ArrayList<Post>> call = postService.getPosts();
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                rv.setAdapter(new GetAllAdapter(GetAll.this, response.body()));
                for (Post post : response.body()) {
                    System.out.println(post.getTitle());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}