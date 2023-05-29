package com.example.healthylifestylemobile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
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
    AdapterDairyRation pAdapterDairy3;
    private List<DairyModel> listDairy3= new ArrayList<>();
    AdapterDairyRation pAdapterDairy2;
    private List<DairyModel> listDairy2 = new ArrayList<>();
    AdapterDairyRation pAdapterDairy4;
    ProgressBar loading;
    ConstraintLayout tt;
    private List<DairyModel> listDairy4= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        loading = view.findViewById(R.id.loading);
        tt = view.findViewById(R.id.tt);
        loading.setVisibility(View.VISIBLE);

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

        ff();
        return view;
    }
    int r2= 0;
    public  void ff()
    {
        if (r2 == 4) {
            // v, clRost, clVes, clAge, clActive, clGoal, clBtn;
            loading.setVisibility(View.GONE);
            tt.setVisibility(View.VISIBLE);

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