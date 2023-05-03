package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDataHeight extends AppCompatActivity {

    TextView Hint;

    String login, password, idActivities, idGender, age;
    ProgressBar progressBar;
    EditText textHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_height);
        Intent intent = getIntent();
        login = intent.getStringExtra("login");
        password = intent.getStringExtra("password");
        idActivities = intent.getStringExtra("idActivities");
        idGender = intent.getStringExtra("idGender");
        age = intent.getStringExtra("age");


        Hint= findViewById(R.id.Hint);

        textHeight= findViewById(R.id.textHeight);
        progressBar = findViewById(R.id.pbLoading);
        textHeight.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                textHeight.setHint("");
            else
                textHeight.setHint("Введите свой вес");
        });
    }

    public void nextAge(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, UserDataAge.class);
        intent.putExtra("login", login);
        intent.putExtra("password", password);
        intent.putExtra("idActivities", idActivities);
        intent.putExtra("idGender", idGender);
        startActivity(intent);
        progressBar.setVisibility(View.GONE);
    }

    public void nextWeight(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        String height = String.valueOf(textHeight.getText());
        if(height.replaceAll("\\s+", " ").equals(""))
        {
            Hint.setText("Необходимо ввести свой вес");
            progressBar.setVisibility(View.GONE);
            return;
        }
        else {
            Intent intent = new Intent(this, UserDataWeight.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            intent.putExtra("idGender", idGender);
            intent.putExtra("age", age);
            intent.putExtra("height", textHeight.getText().toString());
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }

    }
}