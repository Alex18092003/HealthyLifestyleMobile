package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class UserDataHeight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_height);
    }

    public void nextAge(View view)
    {
        startActivity(new Intent(this, UserDataAge.class));
    }

    public void nextWeight(View view)
    {
        startActivity(new Intent(this, UserDataWeight.class));
    }
}