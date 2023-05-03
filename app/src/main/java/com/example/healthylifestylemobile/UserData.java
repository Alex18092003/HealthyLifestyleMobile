package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserData extends AppCompatActivity {

    TextView Hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        Hint = findViewById(R.id.Hint);
    }

    public void nextActivities(View view)
    {
        startActivity(new Intent(this, Activities.class));
    }

    public void nextAge(View view)
    {
        startActivity(new Intent(this, UserDataAge.class));
    }
}