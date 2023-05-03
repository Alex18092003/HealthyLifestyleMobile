package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserGoal extends AppCompatActivity {
    TextView Hint;
    String login, password, idActivities, idGender, age, height, weight, idGoal;
    ProgressBar progressBar;
    CheckBox Ch1, Ch2, Ch3;
    private AdapterMaskGoal pAdapter;
    private List<MaskGoal> listProduct = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_goal);

        Hint = findViewById(R.id.Hint);
        Ch1= findViewById(R.id.Ch1);
        Ch2= findViewById(R.id.Ch2);
        Ch3= findViewById(R.id.Ch3);

        ListView ivProducts = findViewById(R.id.lvData);
        pAdapter = new AdapterMaskGoal(UserGoal.this, listProduct);
        ivProducts.setAdapter(pAdapter);
        new GetGoal().execute();
        progressBar = findViewById(R.id.pbLoading);

        Intent intent = getIntent();
        login = intent.getStringExtra("login");
        password = intent.getStringExtra("password");
        idActivities = intent.getStringExtra("idActivities");
        idGender = intent.getStringExtra("idGender");
        age = intent.getStringExtra("age");
        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        Hint.setText("" + login + " " + password + " " + idActivities + " " + idGender +" " + age +" " + height + " " + weight);

        Ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ch2.setChecked(false);
                    Ch3.setChecked(false);
                }
            }
        });
        Ch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ch1.setChecked(false);
                    Ch3.setChecked(false);
                }
            }
        });
        Ch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ch1.setChecked(false);
                    Ch2.setChecked(false);
                }
            }
        });
    }

    // вывод данных
    private class GetGoal extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Goals");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } catch (Exception exception) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listProduct.clear();
                JSONArray tempArray = new JSONArray(s);
                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    MaskGoal tempGoal = new MaskGoal(
                            productJson.getInt("GoalId"),
                            productJson.getString("Title")
                    );
                    listProduct.add(tempGoal);
                    pAdapter.notifyDataSetInvalidated();
                }
            } catch (Exception ex) {

                Hint.setText( "Что-то пошло не так с выводом данных");
            }
        }
    }


    public void nextWeight(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, UserDataWeight.class);
        intent.putExtra("login", login);
        intent.putExtra("password", password);
        intent.putExtra("idActivities", idActivities);
        intent.putExtra("idGender", idGender);
        intent.putExtra("age", age);
        intent.putExtra("height", height);
        startActivity(intent);
        progressBar.setVisibility(View.GONE);

    }

    public void nexWithCalories(View view)
    {
        progressBar.setVisibility(View.VISIBLE);

        if(Ch1.isChecked() == true)
        {
            idGoal = "1";
            Intent intent = new Intent(this, HomePageWithCalories.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            intent.putExtra("idGender", idGender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("idGoal", idGoal);
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
        if(Ch2.isChecked() == true)
        {
            idGoal = "2";
            Intent intent = new Intent(this, HomePageWithCalories.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            intent.putExtra("idGender", idGender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("idGoal", idGoal);
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
        if(Ch3.isChecked() == true)
        {
            idGoal = "3";
            Intent intent = new Intent(this, HomePageWithCalories.class);
            intent.putExtra("login", login);
            intent.putExtra("assword", password);
            intent.putExtra("idActivities", idActivities);
            intent.putExtra("idGender", idGender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("idGoal", idGoal);
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
        if(Ch1.isChecked() == false && Ch2.isChecked() == false &&
                Ch3.isChecked() == false ) {
            Hint.setText( "Необходимо выбрать цель");
            progressBar.setVisibility(View.GONE);
        }

    }
}