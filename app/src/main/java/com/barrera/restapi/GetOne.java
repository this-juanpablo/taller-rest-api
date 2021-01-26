package com.barrera.restapi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

public class GetOne extends AppCompatActivity {

    private EditText eTxtId;
    private Button btnSearch;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_one);
        init();

        btnSearch.setOnClickListener(click -> {
            String id = eTxtId.getText().toString();
            if (id.isEmpty()) {

            } else {
                getPost(Integer.parseInt(id));
            }
        });
    }

    private void init() {
        eTxtId = findViewById(R.id.eTxtId);
        btnSearch = findViewById(R.id.btnSearchOne);
        rv = findViewById(R.id.getOneRV);
        LinearLayoutManager manager = new LinearLayoutManager(GetOne.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(manager);
    }

    private void getPost(int id) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://juanpablo-1cddc-default-rtdb.firebaseio.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PostService postService = retrofit.create(PostService.class);
        Call<Post> call = postService.getPost(id);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                ArrayList<Post> posts = new ArrayList<>();
                if (response.body() != null) {
                    posts.add(response.body());
                }
                rv.setAdapter(new GetAllAdapter(GetOne.this, posts));
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

}