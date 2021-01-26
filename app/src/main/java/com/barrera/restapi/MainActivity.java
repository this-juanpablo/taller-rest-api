package com.barrera.restapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnGetAll, btnGetOne, btnPostOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnGetAll.setOnClickListener(click -> {
            Intent open = new Intent(this, GetAll.class);
            startActivity(open);
        });
        btnGetOne.setOnClickListener(click -> {
            Intent open = new Intent(this, GetOne.class);
            startActivity(open);
        });
        btnPostOne.setOnClickListener(click -> {
            Intent open = new Intent(this, PostOne.class);
            startActivity(open);
        });
    }

    private void init() {
        btnGetAll = findViewById(R.id.btnGetAll);
        btnGetOne = findViewById(R.id.btnGetOne);
        btnPostOne = findViewById(R.id.btnPostOne);
    }

}