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

public class Activities extends AppCompatActivity {
    TextView Hint;
    ProgressBar progressBar;
    CheckBox Ch1, Ch2, Ch3, Ch4, Ch5;
    private AdapterMaskActivities pAdapter;
    String login, password , idActivities;
    private List<MaskActivities> listProduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        Hint = findViewById(R.id.Hint);
        progressBar = findViewById(R.id.pbLoading);
        Ch1= findViewById(R.id.Ch1);
        Ch2= findViewById(R.id.Ch2);
        Ch3= findViewById(R.id.Ch3);
        Ch4= findViewById(R.id.Ch4);
        Ch5= findViewById(R.id.Ch5);

        Intent intent = getIntent();
         login = intent.getStringExtra("login");
         password = intent.getStringExtra("password");


        ListView ivProducts = findViewById(R.id.lvData);
        pAdapter = new AdapterMaskActivities(Activities.this, listProduct);
        ivProducts.setAdapter(pAdapter);
        new GetActivities().execute();

        Ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ch2.setChecked(false);
                    Ch3.setChecked(false);
                    Ch4.setChecked(false);
                    Ch5.setChecked(false);
                }
            }
        });
        Ch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ch1.setChecked(false);
                    Ch3.setChecked(false);
                    Ch4.setChecked(false);
                    Ch5.setChecked(false);
                }
            }
        });
        Ch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ch1.setChecked(false);
                    Ch2.setChecked(false);
                    Ch4.setChecked(false);
                    Ch5.setChecked(false);
                }
            }
        });
        Ch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ch1.setChecked(false);
                    Ch2.setChecked(false);
                    Ch3.setChecked(false);
                    Ch5.setChecked(false);
                }
            }
        });
        Ch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ch1.setChecked(false);
                    Ch2.setChecked(false);
                    Ch3.setChecked(false);
                    Ch4.setChecked(false);
                }
            }
        });
    }




    // вывод данных
    private class GetActivities extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                progressBar.setVisibility(View.VISIBLE);
                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Activities");
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
                    MaskActivities tempActivities = new MaskActivities(
                            productJson.getInt("ActivityId"),
                            productJson.getString("Title"),
                            productJson.getDouble("Coefficient")
                    );
                    listProduct.add(tempActivities);
                    pAdapter.notifyDataSetInvalidated();
                    progressBar.setVisibility(View.GONE);

                }
            } catch (Exception ex) {

                Hint.setText( "Что-то пошло не так с выводом данных");

            }
        }
    }


    public void nextGender(View view)
    {
        progressBar.setVisibility(View.VISIBLE);

        if(Ch1.isChecked() == true)
        {
            idActivities = "1";
            Intent intent = new Intent(this, UserData.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
        if(Ch2.isChecked() == true)
        {
            idActivities = "2";
            Intent intent = new Intent(this, UserData.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
        if(Ch3.isChecked() == true)
        {
            idActivities = "3";
            Intent intent = new Intent(this, UserData.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
        if(Ch4.isChecked() == true)
        {
            idActivities = "4";
            Intent intent = new Intent(this, UserData.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
        if(Ch5.isChecked() == true)
        {
            idActivities = "5";
            Intent intent = new Intent(this, UserData.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("idActivities", idActivities);
            startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
        if(Ch1.isChecked() == false && Ch2.isChecked() == false &&
                Ch3.isChecked() == false && Ch4.isChecked() == false &&
                Ch5.isChecked() == false) {
            Hint.setText( "Необходимо выбрать активность");
            progressBar.setVisibility(View.GONE);
        }

    }


    public void nextRegistration(View view)
    {
        startActivity(new Intent(this, Registration.class));
    }


}