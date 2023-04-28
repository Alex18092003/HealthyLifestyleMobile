package com.example.healthylifestylemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeAutorization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_autorization);
    }

    public void nextAutorization(View view)
    {
        startActivity(new Intent(this, Autorization.class));
    }
    public void nextRegistation(View view)
    {
        startActivity(new Intent(this, Registration.class));
    }
}