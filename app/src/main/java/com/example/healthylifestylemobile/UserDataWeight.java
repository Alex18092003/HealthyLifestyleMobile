package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDataWeight extends AppCompatActivity {


    TextView Hint;

    String login, password, idActivities, idGender, age, height;
    ProgressBar progressBar;
    EditText textWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_weight);
        Intent intent = getIntent();
        login = intent.getStringExtra("login");
        password = intent.getStringExtra("password");
        idActivities = intent.getStringExtra("idActivities");
        idGender = intent.getStringExtra("idGender");
        age = intent.getStringExtra("age");
        height = intent.getStringExtra("height");



        Hint= findViewById(R.id.Hint);
        textWeight= findViewById(R.id.textWeight);
        progressBar = findViewById(R.id.pbLoading);
        textWeight.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                textWeight.setHint("");
            else
                textWeight.setHint("Введите свой рост");
        });
    }

    public void nextHeight(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, UserDataHeight.class);
        intent.putExtra("login", login);
        intent.putExtra("password", password);
        intent.putExtra("idActivities", idActivities);
        intent.putExtra("idGender", idGender);
        intent.putExtra("age", age);
        startActivity(intent);
        progressBar.setVisibility(View.GONE);

    }
    public void nextGoal(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        String weight = String.valueOf(textWeight.getText());
        if(weight.replaceAll("\\s+", " ").equals(""))
        {
            Hint.setText("Необходимо ввести свой рост");
            progressBar.setVisibility(View.GONE);
            return;
        }
        else {
            Intent intent = new Intent(this, UserGoal.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            intent.putExtra("idGender", idGender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", textWeight.getText().toString());
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }

    }
}