package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
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

    private AdapterMaskGoal pAdapter;
    private List<MaskGoal> listProduct = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_goal);

        Hint = findViewById(R.id.Hint);

        ListView ivProducts = findViewById(R.id.lvData);
        pAdapter = new AdapterMaskGoal(UserGoal.this, listProduct);
        ivProducts.setAdapter(pAdapter);
        new GetGoal().execute();
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
        startActivity(new Intent(this, UserDataWeight.class));
    }

    public void nexWithCalories(View view)
    {
        startActivity(new Intent(this, HomePageWithCalories.class));
    }
}