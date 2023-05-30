package com.example.healthylifestylemobile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiaryFragment newInstance(String param1, String param2) {
        DiaryFragment fragment = new DiaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    AdapterDairyRation pAdapterDairy;
    private List<DairyModel> listDairy= new ArrayList<>();
    AdapterTitle pAdapterDairyTitle;
    private List<RecepesModel> listDairyTitle= new ArrayList<>();
    AdapterDairyRation pAdapterDairy3;
    private List<DairyModel> listDairy3= new ArrayList<>();
    AdapterDairyRation pAdapterDairy2;
    private List<DairyModel> listDairy2 = new ArrayList<>();
    AdapterDairyRation pAdapterDairy4;
    ProgressBar loading;
    ConstraintLayout tt;

    AdapterTitle pAdapterDairyTitle2;
    private List<RecepesModel> listDairyTitle2 = new ArrayList<>();
    AdapterTitle pAdapterDairyTitle3;
    private List<RecepesModel> listDairyTitle3 = new ArrayList<>();
    AdapterTitle pAdapterDairyTitle4;
    private List<RecepesModel> listDairyTitle4 = new ArrayList<>();

    private List<DairyModel> listDairy4= new ArrayList<>();
    TextView Breakfast,  Lunch, Dinner,Snack;
    ConstraintLayout vv, v2, v3, v4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        loading = view.findViewById(R.id.loading);
        tt = view.findViewById(R.id.tt);
        loading.setVisibility(View.VISIBLE);

        vv = view.findViewById(R.id.vv);
        v2 = view.findViewById(R.id.v2);
        v3 = view.findViewById(R.id.v3);
        v4 = view.findViewById(R.id.v4);

        Breakfast = view.findViewById(R.id.Breakfast);
        Lunch = view.findViewById(R.id.Lunch);
        Dinner = view.findViewById(R.id.Dinner);
        Snack = view.findViewById(R.id.Snack);

        Snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v4.getVisibility() == View.VISIBLE)
                {
                    v4.setVisibility(View.GONE);
                    Snack.setText("Перекус ▼");
                }
                else {
                    v4.setVisibility(View.VISIBLE);
                    Snack.setText("Перекус ▲");

                }
            }
        });
        Dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v3.getVisibility() == View.VISIBLE)
                {
                    v3.setVisibility(View.GONE);
                    Dinner.setText("Ужин ▼");
                }
                else {
                    v3.setVisibility(View.VISIBLE);
                    Dinner.setText("Ужин ▲");
                }
            }
        });
        Lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v2.getVisibility() == View.VISIBLE)
                {
                    v2.setVisibility(View.GONE);
                    Lunch.setText("Обед ▼");
                }
                else {
                    v2.setVisibility(View.VISIBLE);
                    Lunch.setText("Обед ▲");
                }
            }
        });
        Breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vv.getVisibility() == View.VISIBLE)
                {
                    vv.setVisibility(View.GONE);
                    Breakfast.setText("Завтрак ▼");
                }
                else {
                    vv.setVisibility(View.VISIBLE);
                    Breakfast.setText("Завтрак ▲");
                }
            }
        });

        ListView lvSnackTitle = view.findViewById(R.id.lvSnackTitle);
        pAdapterDairyTitle4 = new AdapterTitle(getActivity(), listDairyTitle4);
        lvSnackTitle.setAdapter(pAdapterDairyTitle4);

        ListView lvDinnerTitle = view.findViewById(R.id.lvDinnerTitle);
        pAdapterDairyTitle3 = new AdapterTitle(getActivity(), listDairyTitle3);
        lvDinnerTitle.setAdapter(pAdapterDairyTitle3);

        ListView lvLunchTitle = view.findViewById(R.id.lvLunchTitle);
        pAdapterDairyTitle2 = new AdapterTitle(getActivity(), listDairyTitle2);
        lvLunchTitle.setAdapter(pAdapterDairyTitle2);

        ListView lvDataBreakfastTitle = view.findViewById(R.id.lvDataBreakfastTitle);
        pAdapterDairyTitle = new AdapterTitle(getActivity(), listDairyTitle);
        lvDataBreakfastTitle.setAdapter(pAdapterDairyTitle);

        ListView lvDataBreakfast = view.findViewById(R.id.lvDataBreakfast);
        pAdapterDairy = new AdapterDairyRation(getActivity(), listDairy);
        lvDataBreakfast.setAdapter(pAdapterDairy);

        ListView lvDataLunch = view.findViewById(R.id.lvDataLunch);
        pAdapterDairy2 = new AdapterDairyRation(getActivity(), listDairy2);
        lvDataLunch.setAdapter(pAdapterDairy2);

        ListView lvDataDinner = view.findViewById(R.id.lvDataDinner);
        pAdapterDairy3 = new AdapterDairyRation(getActivity(), listDairy3);
        lvDataDinner.setAdapter(pAdapterDairy3);

        ListView lvDataSnack = view.findViewById(R.id.lvDataSnack);
        pAdapterDairy4 = new AdapterDairyRation(getActivity(), listDairy4);
        lvDataSnack.setAdapter(pAdapterDairy4);

        new callGetDairyBreakfast().execute();
        new callGetDairyLunch().execute();
        new callGetDairyDinner().execute();
        new callGetDairySnack().execute();
        new callGetDairySnackTitle().execute();
        new callGetDairyBreakfastTitle().execute();
        new callGetDairyLunchTitle().execute();
        new callGetDairyDinneTitle().execute();

        ff();
        return view;
    }
    int r2= 0;
    public  void ff()
    {
        if (r2 == 8) {
            // v, clRost, clVes, clAge, clActive, clGoal, clBtn;
            loading.setVisibility(View.GONE);
            tt.setVisibility(View.VISIBLE);

        }
    }
    private class callGetDairySnackTitle extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Recipes?t=" +3+ "&iddd="+HomePageWithCalories.index+ "&m=" +0);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }

                return result.toString();
            }
            catch (Exception exception)
            {

                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listDairyTitle4.clear();
                pAdapterDairyTitle4.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);

                for (int i = 0;i<tempArray.length();i++)
                {

                    JSONObject productJson = tempArray.getJSONObject(i);
                    RecepesModel tempProduct = new RecepesModel(
                            productJson.getInt("RecipeId"),
                            productJson.getString("Title"),
                            productJson.getInt("MinutesOfCooking"),
                            productJson.getString("Description"),
                            productJson.getString("Comment"),
                            productJson.getString("Photo"),
                            productJson.getInt("RecipeType"),
                            productJson.getInt("MealId"),
                            productJson.getInt("DietId"),
                            productJson.getInt("SpecificsId"),
                            productJson.getInt("DifficultyId"),
                            productJson.getInt("CookingId"),
                            productJson.getInt("KitchenId"),
                            productJson.getDouble("Calories"),
                            productJson.getDouble("Squirrels"),
                            productJson.getDouble("Fats"),
                            productJson.getDouble("Carbohydrates"),
                            productJson.getString("PhotoAnd")

                    );

                    listDairyTitle4.add(tempProduct);
                    pAdapterDairyTitle4.notifyDataSetInvalidated();
                }
                r2++;
                ff();
            }
            catch (Exception ignored)
            {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        }
    }

    private class callGetDairyDinneTitle extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Recipes?t=" +4+ "&iddd="+HomePageWithCalories.index+ "&m=" +0);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }

                return result.toString();
            }
            catch (Exception exception)
            {

                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listDairyTitle3.clear();
                pAdapterDairyTitle3.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);

                for (int i = 0;i<tempArray.length();i++)
                {

                    JSONObject productJson = tempArray.getJSONObject(i);
                    RecepesModel tempProduct = new RecepesModel(
                            productJson.getInt("RecipeId"),
                            productJson.getString("Title"),
                            productJson.getInt("MinutesOfCooking"),
                            productJson.getString("Description"),
                            productJson.getString("Comment"),
                            productJson.getString("Photo"),
                            productJson.getInt("RecipeType"),
                            productJson.getInt("MealId"),
                            productJson.getInt("DietId"),
                            productJson.getInt("SpecificsId"),
                            productJson.getInt("DifficultyId"),
                            productJson.getInt("CookingId"),
                            productJson.getInt("KitchenId"),
                            productJson.getDouble("Calories"),
                            productJson.getDouble("Squirrels"),
                            productJson.getDouble("Fats"),
                            productJson.getDouble("Carbohydrates"),
                            productJson.getString("PhotoAnd")

                    );

                    listDairyTitle3.add(tempProduct);
                    pAdapterDairyTitle3.notifyDataSetInvalidated();
                }
                r2++;
                ff();
            }
            catch (Exception ignored)
            {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        }
    }

    private class callGetDairyLunchTitle extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Recipes?t=" +2+ "&iddd="+HomePageWithCalories.index+ "&m=" +0);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }

                return result.toString();
            }
            catch (Exception exception)
            {

                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listDairyTitle2.clear();
                pAdapterDairyTitle2.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);

                for (int i = 0;i<tempArray.length();i++)
                {

                    JSONObject productJson = tempArray.getJSONObject(i);
                    RecepesModel tempProduct = new RecepesModel(
                            productJson.getInt("RecipeId"),
                            productJson.getString("Title"),
                            productJson.getInt("MinutesOfCooking"),
                            productJson.getString("Description"),
                            productJson.getString("Comment"),
                            productJson.getString("Photo"),
                            productJson.getInt("RecipeType"),
                            productJson.getInt("MealId"),
                            productJson.getInt("DietId"),
                            productJson.getInt("SpecificsId"),
                            productJson.getInt("DifficultyId"),
                            productJson.getInt("CookingId"),
                            productJson.getInt("KitchenId"),
                            productJson.getDouble("Calories"),
                            productJson.getDouble("Squirrels"),
                            productJson.getDouble("Fats"),
                            productJson.getDouble("Carbohydrates"),
                            productJson.getString("PhotoAnd")

                    );

                    listDairyTitle2.add(tempProduct);
                    pAdapterDairyTitle2.notifyDataSetInvalidated();
                }
                r2++;
                ff();
            }
            catch (Exception ignored)
            {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        }
    }

    private class callGetDairyBreakfastTitle extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Recipes?t=" +1+ "&iddd="+HomePageWithCalories.index+ "&m=" +0);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }

                return result.toString();
            }
            catch (Exception exception)
            {

                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listDairyTitle.clear();
                pAdapterDairyTitle.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);

                for (int i = 0;i<tempArray.length();i++)
                {

                    JSONObject productJson = tempArray.getJSONObject(i);
                    RecepesModel tempProduct = new RecepesModel(
                            productJson.getInt("RecipeId"),
                            productJson.getString("Title"),
                            productJson.getInt("MinutesOfCooking"),
                            productJson.getString("Description"),
                            productJson.getString("Comment"),
                            productJson.getString("Photo"),
                            productJson.getInt("RecipeType"),
                            productJson.getInt("MealId"),
                            productJson.getInt("DietId"),
                            productJson.getInt("SpecificsId"),
                            productJson.getInt("DifficultyId"),
                            productJson.getInt("CookingId"),
                            productJson.getInt("KitchenId"),
                            productJson.getDouble("Calories"),
                            productJson.getDouble("Squirrels"),
                            productJson.getDouble("Fats"),
                            productJson.getDouble("Carbohydrates"),
                            productJson.getString("PhotoAnd")

                    );

                    listDairyTitle.add(tempProduct);
                    pAdapterDairyTitle.notifyDataSetInvalidated();
                }
                r2++;
                ff();
            }
            catch (Exception ignored)
            {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        }
    }

    private class callGetDairySnack extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/DailyRations?t=" +3+ "&iddd="+HomePageWithCalories.index+ "&m=" +0);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }

                return result.toString();
            }
            catch (Exception exception)
            {

                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listDairy4.clear();
                pAdapterDairy4.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);

                for (int i = 0;i<tempArray.length();i++)
                {

                    JSONObject productJson = tempArray.getJSONObject(i);
                    DairyModel tempProduct = new DairyModel(
                            productJson.getInt("DailyRationId"),
                            productJson.getInt("UserId"),
                            productJson.getInt("RecepeId"),
                            productJson.optJSONObject("Dates"),
                            productJson.getDouble("Calories"),
                            productJson.getDouble("Squirrels"),
                            productJson.getDouble("Fats"),
                            productJson.getDouble("Carbohydrates"),
                            productJson.getDouble("CaloriesUsers"),
                            productJson.getInt("MealId")

                    );

                    listDairy4.add(tempProduct);
                    pAdapterDairy4.notifyDataSetInvalidated();
                }
                r2++;
                ff();
            }
            catch (Exception ignored)
            {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        }
    }
    private class callGetDairyDinner extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/DailyRations?t=" +4+ "&iddd="+HomePageWithCalories.index+ "&m=" +0);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }

                return result.toString();
            }
            catch (Exception exception)
            {

                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listDairy3.clear();
                pAdapterDairy3.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);

                for (int i = 0;i<tempArray.length();i++)
                {

                    JSONObject productJson = tempArray.getJSONObject(i);
                    DairyModel tempProduct = new DairyModel(
                            productJson.getInt("DailyRationId"),
                            productJson.getInt("UserId"),
                            productJson.getInt("RecepeId"),
                            productJson.optJSONObject("Dates"),
                            productJson.getDouble("Calories"),
                            productJson.getDouble("Squirrels"),
                            productJson.getDouble("Fats"),
                            productJson.getDouble("Carbohydrates"),
                            productJson.getDouble("CaloriesUsers"),
                            productJson.getInt("MealId")

                    );

                    listDairy3.add(tempProduct);
                    pAdapterDairy3.notifyDataSetInvalidated();
                }
                r2++;
                ff();
            }
            catch (Exception ignored)
            {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        }
    }
    private class callGetDairyLunch extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/DailyRations?t=" +2+ "&iddd="+HomePageWithCalories.index+ "&m=" +0);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }

                return result.toString();
            }
            catch (Exception exception)
            {

                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listDairy2.clear();
                pAdapterDairy2.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);

                for (int i = 0;i<tempArray.length();i++)
                {

                    JSONObject productJson = tempArray.getJSONObject(i);
                    DairyModel tempProduct = new DairyModel(
                            productJson.getInt("DailyRationId"),
                            productJson.getInt("UserId"),
                            productJson.getInt("RecepeId"),
                            productJson.optJSONObject("Dates"),
                            productJson.getDouble("Calories"),
                            productJson.getDouble("Squirrels"),
                            productJson.getDouble("Fats"),
                            productJson.getDouble("Carbohydrates"),
                            productJson.getDouble("CaloriesUsers"),
                            productJson.getInt("MealId")

                    );

                    listDairy2.add(tempProduct);
                    pAdapterDairy2.notifyDataSetInvalidated();
                }
                r2++;
                ff();
            }
            catch (Exception ignored)
            {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        }
    }
    private class callGetDairyBreakfast extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/DailyRations?t=" +1+ "&iddd="+HomePageWithCalories.index+ "&m=" +0);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }

                return result.toString();
            }
            catch (Exception exception)
            {

                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listDairy.clear();
                pAdapterDairy.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);

                for (int i = 0;i<tempArray.length();i++)
                {

                    JSONObject productJson = tempArray.getJSONObject(i);
                    DairyModel tempProduct = new DairyModel(
                            productJson.getInt("DailyRationId"),
                            productJson.getInt("UserId"),
                            productJson.getInt("RecepeId"),
                            productJson.optJSONObject("Dates"),
                            productJson.getDouble("Calories"),
                            productJson.getDouble("Squirrels"),
                            productJson.getDouble("Fats"),
                            productJson.getDouble("Carbohydrates"),
                            productJson.getDouble("CaloriesUsers"),
                            productJson.getInt("MealId")

                    );

                    listDairy.add(tempProduct);
                    pAdapterDairy.notifyDataSetInvalidated();
                }
                r2++;
                ff();
            }
            catch (Exception ignored)
            {
               Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        }
    }
}