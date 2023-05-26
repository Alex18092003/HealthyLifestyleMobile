package com.example.healthylifestylemobile;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static UserModel userModel;

    ProgressBar loading, pbLoading;
    EditText etextRost, etextVes,
            etextAge;

    Button Entry, LoginPas;
    TextView Hint;
    ConstraintLayout tt , clRost, clVes, clAge, clActive, clGoal, clBtn;
    Spinner spActive, spGoal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    int r2=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        etextRost = (EditText) v.findViewById(R.id.etextRost);
        etextVes = (EditText) v.findViewById(R.id.etextVes);
        etextAge = (EditText) v.findViewById(R.id.etextAge);

        loading = v.findViewById(R.id.loading);
        pbLoading = v.findViewById(R.id.pbLoading);
       tt = v.findViewById(R.id.tt);
//        clRost = v.findViewById(R.id.clRost);
//        clVes = v.findViewById(R.id.clVes);
//        clAge = v.findViewById(R.id.clAge);
//        clBtn = v.findViewById(R.id.clBtn);
       loading.setVisibility(View.VISIBLE);


        clGoal =  v.findViewById(R.id.clGoal);
        clActive =  v.findViewById(R.id.clActive);

        spActive = v.findViewById(R.id.spActive);
        spGoal = v.findViewById(R.id.spGoal);

        Hint = v.findViewById(R.id.Hint);

        Entry = v.findViewById(R.id.Entry);
        Entry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ButtonEdit();
            }
        });

        LoginPas = v.findViewById(R.id.LoginPas);
        LoginPas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                UpdateLoginFragment fragment = new UpdateLoginFragment();
                ft.replace(R.id.UserProfilePerehod, fragment);
                ft.commit();
            }
        });


        new GetActivities().execute();
        new GetGoals().execute();

        ff();
        return v;
    }
    String DateOfBirth, Weight, Height, Activ, Goal;
    public  void ff()
    {
        if (r2 == 3) {
           // v, clRost, clVes, clAge, clActive, clGoal, clBtn;
            loading.setVisibility(View.GONE);
            tt.setVisibility(View.VISIBLE);
//            clRost.setVisibility(View.VISIBLE);
//            clVes.setVisibility(View.VISIBLE);
//            clAge.setVisibility(View.VISIBLE);
//            clActive.setVisibility(View.VISIBLE);
//            clGoal.setVisibility(View.VISIBLE);
//            clBtn.setVisibility(View.VISIBLE);
        }
    }
    public void callGetUser()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<UserModel> call = retrofitAPI.getDATAUser(HomePageWithCalories.index);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(!response.isSuccessful())
                {
                    Hint.setText("При выводе данных возникла ошибка");

                    return;
                }
                userModel = new UserModel(0, response.body().getGenderId(), response.body().getLogin(), response.body().getWeight(), response.body().getHeight(),
                        response.body().getActivityId(),
                        response.body().getGoalId(),  response.body().getCalories(),
                        response.body().getSquirrels(), response.body().getDateOfBirth(), response.body().getPassword(),
                        response.body().getFats(), response.body().getCarbohydrates());
                Height = String.valueOf(response.body().getHeight());
                Weight= String.valueOf(response.body().getWeight());
                etextRost.setText(Height);
                etextVes.setText(Weight);
                DateOfBirth = String.valueOf(response.body().getDateOfBirth());
                etextAge.setText(DateOfBirth);
                spActive.setSelection(getPositionActiv(response.body().getActivityId()));
                spGoal.setSelection(getPositionGoal(response.body().getGoalId()));
                r2++;
                ff();
                Hint.setText("");
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Hint.setText("При выводе данных возникла ошибка!");

            }
        });
    }

    String[][] ActiveArray;
    String[][] GoalArray;

    private class GetActivities extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {

                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Activities");
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

                JSONArray tempArray = new JSONArray(s);
                String[] str_array = new String[tempArray.length()];
                ActiveArray = new String[tempArray.length()][2];
                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    str_array[i]=productJson.getString("Title");
                    ActiveArray[i][0] = productJson.getString("ActivityId");
                    ActiveArray[i][1] = productJson.getString("Title");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, str_array);
                spActive.setAdapter(adapter);
                callGetUser();
                Hint.setText("");
                r2++;
                ff();
            }
            catch (Exception ignored)
            {
                Hint.setText("При выводе данных активности возникла ошибка!");
            }
        }
    }

    private class GetGoals extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Goals");
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

                JSONArray tempArray = new JSONArray(s);
                String[] str_array = new String[tempArray.length()];
                GoalArray = new String[tempArray.length()][2];
                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    str_array[i]=productJson.getString("Title");
                    GoalArray[i][0] = productJson.getString("GoalId");
                    GoalArray[i][1] = productJson.getString("Title");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, str_array);
                spGoal.setAdapter(adapter);
//                callGetUser();
                Hint.setText("");
                r2++;
                ff();
            }
            catch (Exception ignored)
            {
                Hint.setText("При выводе данных цели возникла ошибка!");
            }
        }
    }



    public void ButtonEdit()
    {
        if(clGoal .getVisibility() == View.GONE) {
            etextRost.setEnabled(true);
            etextVes.setEnabled(true);
            etextAge.setEnabled(true);
            Entry.setText("Сохранить");
            etextRost.getForeground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
            etextVes.getForeground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
            etextAge.getForeground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
            clGoal.setVisibility(View.VISIBLE);
            clActive.setVisibility(View.VISIBLE);
        }
        else {
            updData();
        }
    }

    public void updData()
    {
        pbLoading.setVisibility(View.VISIBLE);
        if(etextRost.getText().toString().equals("") ||
                etextVes.getText().toString().equals("") ||
                etextAge.getText().toString().equals("") )
        {
            Hint.setText("Заполните все поля");
            pbLoading.setVisibility(View.GONE);
            return;
        }
        String age = String.valueOf(etextAge.getText());
        String rost = String.valueOf(etextRost.getText());
        String ves = String.valueOf(etextVes.getText());
        final int value = Integer.valueOf(age);
        if(value < 14 || value > 80)
        {
            Hint.setText("Возраст введен некорректно");
            pbLoading.setVisibility(View.GONE);
            return;
        }
        final Float value2 = Float.valueOf(ves);
        if(value2 < 30 || value2 > 500)
        {
            Hint.setText("Вес введен некорректно");
            pbLoading.setVisibility(View.GONE);
            return;
        }
        final Float value3 = Float.valueOf(rost);
        if(value3 < 50 || value3 > 265)
        {
            Hint.setText("Рост введен некорректно");
           pbLoading.setVisibility(View.GONE);
            return;
        }

        etextRost.setEnabled(false);
        etextVes.setEnabled(false);
        etextAge.setEnabled(false);
        Entry.setText("Редактировать");
        etextRost.getForeground().setColorFilter(Color.parseColor("#70274025"), PorterDuff.Mode.SRC_ATOP);
        etextVes.getForeground().setColorFilter(Color.parseColor("#70274025"), PorterDuff.Mode.SRC_ATOP);
        etextAge.getForeground().setColorFilter(Color.parseColor("#70274025"), PorterDuff.Mode.SRC_ATOP);
        clGoal.setVisibility(View.GONE);
        clActive.setVisibility(View.GONE);

        callPUTDataMethod(
                Float.parseFloat(etextVes.getText().toString()),
                Float.parseFloat(etextRost.getText().toString()),
                getIdActive(spActive.getSelectedItem().toString()),
                getIdTGoal(spGoal.getSelectedItem().toString()),
                Integer.parseInt(etextAge.getText().toString()));
    }

    private void callPUTDataMethod(
                                   Float Weight,
                                   Float Height,
                                   int ActivityId,
                                   int GoalId,
                                   int  DateOfBirth) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);


        UserModel modal = new UserModel(0,0,
                null, Weight, Height, ActivityId, GoalId,
                0, 0, DateOfBirth,null,
                0, 0);

        Call<UserModel> call = retrofitAPI.updateUser(HomePageWithCalories.index, modal);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(!response.isSuccessful())
                {
                    Hint.setText( "При изменение данных возникла ошибка");
                    pbLoading.setVisibility(View.GONE);
                    return;
                }
                Hint.setText("");
                Toast.makeText(getActivity(),"Данные изменены", Toast.LENGTH_LONG).show();
               pbLoading.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(getActivity(), "При изменение записи возникла ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private int getIdTGoal(String typeLocality)
    {
        for (int i = 0; i < GoalArray.length; i++)
        {
            if(GoalArray[i][1].equals(typeLocality))
            {
                return Integer.parseInt(GoalArray[i][0]);
            }
        }
        return 0;
    }

    private int getPositionGoal(int id_country)
    {
        for (int i = 0; i < GoalArray.length; i++)
        {
            if(GoalArray[i][0] == String.valueOf(id_country))
            {
                return i;
            }
        }
        return 0;
    }
    private int getPositionActiv(int id_typeLocality)
    {
        for (int i = 0; i < ActiveArray.length; i++)
        {
            if(ActiveArray[i][0] == String.valueOf(id_typeLocality))
            {
                return i;
            }
        }
        return 0;
    }

    private int getIdActive(String country)
    {
        for (int i = 0; i < ActiveArray.length; i++)
        {
            if(ActiveArray[i][1].equals(country))
            {
                return Integer.parseInt(ActiveArray[i][0]);
            }
        }
        return 0;
    }
}