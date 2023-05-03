package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class UserDataAge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_age);
    }
    public void nextData(View view)
    {
        startActivity(new Intent(this, UserData.class));
    }

    public void nextHeight(View view)
    {
        startActivity(new Intent(this, UserDataHeight.class));
    }
}