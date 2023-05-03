package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserData extends AppCompatActivity {

    TextView Hint;

    String login, password, idActivities, idGender;
    CheckBox GenderM, GenderG;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        Intent intent = getIntent();
        login = intent.getStringExtra("login");
        password = intent.getStringExtra("password");
        idActivities = intent.getStringExtra("idActivities");

        GenderM= findViewById(R.id.GenderM);
        GenderG= findViewById(R.id.GenderG);
        Hint = findViewById(R.id.Hint);
        progressBar = findViewById(R.id.pbLoading);


        GenderM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    GenderG.setChecked(false);
                }
            }
        });
        GenderG.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    GenderM.setChecked(false);
                }
            }
        });

    }

    public void nextActivities(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, Activities.class);
        intent.putExtra("login", login);
        intent.putExtra("password", password);
        startActivity(intent);
        progressBar.setVisibility(View.GONE);

    }

    public void nextAge(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        if(GenderM.isChecked() == true)
        {
            idGender = "1";
            Intent intent = new Intent(this, UserDataAge.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            intent.putExtra("idGender", idGender);
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
        if(GenderG.isChecked() == true)
        {
            idGender = "2";
            Intent intent = new Intent(this, UserDataAge.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            intent.putExtra("idGender", idGender);
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
        if(GenderG.isChecked() == false && GenderM.isChecked() == false)
        {
            Hint.setText( "Необходимо выбрать свой пол");
            progressBar.setVisibility(View.GONE);
        }
    }
}