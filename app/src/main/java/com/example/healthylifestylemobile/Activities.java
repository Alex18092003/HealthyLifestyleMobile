package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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
    private AdapterMaskActivities pAdapter;
    private List<MaskActivities> listProduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        Hint = findViewById(R.id.Hint);
        progressBar = findViewById(R.id.pbLoading);

        Intent intent = getIntent();
        String login = intent.getStringExtra("login");
        String password = intent.getStringExtra("password");


        ListView ivProducts = findViewById(R.id.lvData);
        pAdapter = new AdapterMaskActivities(Activities.this, listProduct);
        ivProducts.setAdapter(pAdapter);
        new GetActivities().execute();
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
        startActivity(new Intent(this, UserData.class));
    }


    public void nextRegistration(View view)
    {
        startActivity(new Intent(this, Registration.class));
    }
}