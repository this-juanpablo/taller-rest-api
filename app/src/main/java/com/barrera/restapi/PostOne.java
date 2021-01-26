package com.barrera.restapi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.barrera.restapi.Models.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostOne extends AppCompatActivity {

    private EditText eTxtId, eTxtTitle, eTxtBody;
    private Button btnSave;
    private int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_one);
        init();
        getTotal();

        btnSave.setOnClickListener(click -> {
            String id = eTxtId.getText().toString();
            String title = eTxtTitle.getText().toString();
            String body = eTxtBody.getText().toString();

            if (title.isEmpty() || body.isEmpty()) {

            } else {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://juanpablo-1cddc-default-rtdb.firebaseio.com")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                PostService postService = retrofit.create(PostService.class);
                Call<Post> call = postService.savePost(new Post(total, title, body, 1), total);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Toast.makeText(PostOne.this, "Post saved", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void getTotal() {
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
                total = response.body().size();
                eTxtId.setText(String.valueOf(total));
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    private void init() {
        btnSave = findViewById(R.id.btnSaveOne);
        eTxtId = findViewById(R.id.eTxtId);
        eTxtTitle = findViewById(R.id.eTxtTitle);
        eTxtBody = findViewById(R.id.eTxtBody);
    }
}