package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDataAge extends AppCompatActivity {

    TextView Hint;

    String login, password, idActivities, idGender;
    ProgressBar progressBar;
    EditText textAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_age);
        Intent intent = getIntent();
        login = intent.getStringExtra("login");
        password = intent.getStringExtra("password");
        idActivities = intent.getStringExtra("idActivities");
        idGender = intent.getStringExtra("idGender");



        Hint= findViewById(R.id.Hint);
        textAge= findViewById(R.id.textAge);
        progressBar = findViewById(R.id.pbLoading);

        textAge.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                textAge.setHint("");
            else
                textAge.setHint("Введите свой возраст");
        });
    }
    public void nextData(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, UserData.class);
        intent.putExtra("login", login);
        intent.putExtra("password", password);
        intent.putExtra("idActivities", idActivities);
        startActivity(intent);
        progressBar.setVisibility(View.GONE);
    }

    public void nextHeight(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        String age = String.valueOf(textAge.getText());
        if(age.replaceAll("\\s+", " ").equals(""))
        {
            Hint.setText("Необходимо ввести свой возраст");
            progressBar.setVisibility(View.GONE);
            return;
        }
        else {
            Intent intent = new Intent(this, UserDataHeight.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            intent.putExtra("idGender", idGender);
            intent.putExtra("age", textAge.getText().toString());
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }

    }
}