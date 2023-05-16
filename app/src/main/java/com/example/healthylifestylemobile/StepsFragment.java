package com.example.healthylifestylemobile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link StepsFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class StepsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int id;
    public StepsFragment(int id) {
        // Required empty public constructor
        this.id = id;
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment StepsFragment.
//     */
    // TODO: Rename and change types and number of parameters
//    public static StepsFragment newInstance(String param1, String param2) {
//        StepsFragment fragment = new StepsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    TextView textDescription, textComment,TextMinutesRecipes;

    AdapterSteps pAdapterStep;
    AdapterIngredient pAdapterIngredient ;
    AdapterIngForRep pAdapterIngForRep;
    ProgressBar loading;
    Spinner spMeal;
    EditText etextSData;

    private List<IngredientForRecipeModel> listIng = new ArrayList<>();
    private List<StepsModel> listSteps = new ArrayList<>();
    private List<IngrediensModel> listIngredient = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        // Inflate the layout for this fragment



        loading = view.findViewById(R.id.pbLoading);
        TextMinutesRecipes = view.findViewById(R.id.TextMinutesRecipes);
        textComment = view.findViewById(R.id.textComment);
        textDescription = view.findViewById(R.id.textDescription);
        loading.setVisibility(View.VISIBLE);

        etextSData = view.findViewById(R.id.etextSData);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        etextSData.setText(dateTime);

        ListView lvDataI = view.findViewById(R.id.lvDataI);
        pAdapterIngredient = new AdapterIngredient(getActivity(), listIngredient);
        lvDataI.setAdapter(pAdapterIngredient);

        ListView lvDataIng = view.findViewById(R.id.lvDataIng);
        pAdapterIngForRep = new AdapterIngForRep(getActivity(), listIng);
        lvDataIng.setAdapter(pAdapterIngForRep);

        ListView lvDataSteps = view.findViewById(R.id.lvDataSteps);
        pAdapterStep = new AdapterSteps(getActivity(), listSteps);
        lvDataSteps.setAdapter(pAdapterStep);
        //lvDataSteps = view.findViewById(R.id.lvDataSteps);
        spMeal = view.findViewById(R.id.spMeal);


        new callGetSteps().execute();
        new callGetIngred().execute();
        new callGetI().execute();
        new GetMeals().execute();
        callGetRecepis();

        loading.setVisibility(View.GONE);
        return view;
    }


    String[][] spMealsArray;
    private class GetMeals extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {

                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Meals");
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
            try {

                JSONArray tempArray = new JSONArray(s);
                String[] str_array = new String[tempArray.length()+1];
                str_array[0] = "Прием пищи";
                spMealsArray = new String[tempArray.length()+1][2];
                spMealsArray[0][0] = "0";
                spMealsArray[0][1] = "";
                for (int i = 0; i < tempArray.length(); i++) {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    str_array[i+1] = productJson.getString("Title");
                    spMealsArray[i+1][0] = productJson.getString("MealId");
                    spMealsArray[i+1][1] = productJson.getString("Title");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, str_array);
                spMeal.setAdapter(adapter);

            }
            catch (Exception ignored)
            {

            }
        }
    }

    private int getIdMeals(String Meals)
    {
        for (int i = 0; i < spMealsArray.length; i++)
        {
            if(spMealsArray[i][1].equals(Meals))
            {
                return Integer.parseInt(spMealsArray[i][0]);
            }
        }
        return 0;
    }
    private int getPositionMeals(int id_typeLocality)
    {
        for (int i = 0; i < spMealsArray.length; i++)
        {
            if(spMealsArray[i][0] == String.valueOf(id_typeLocality))
            {
                return i;
            }
        }
        return 0;
    }

    private class callGetI extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Ingredients/?index=" + id);
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

                listIngredient.clear();
                pAdapterIngredient.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);
                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    IngrediensModel tempProduct = new IngrediensModel(
                            productJson.getInt("IngredientId"),
                            productJson.getString("Title")


                    );
                    listIngredient.add(tempProduct);
                    pAdapterIngredient.notifyDataSetInvalidated();
                }

            }
            catch (Exception ignored)
            {

            }
        }
    }

    private class callGetIngred extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/IngredientForRecipes/?index=" + id);
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

                listIng.clear();
                pAdapterIngForRep.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);
                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    IngredientForRecipeModel tempProduct = new IngredientForRecipeModel(
                            productJson.getInt("IngredientForRecipeId"),
                            productJson.getInt("IngredientId"),
                            productJson.getInt("RecipeId"),
                            productJson.getInt("UnitsOfMeasurementId"),
                            productJson.getDouble("Quantity")

                    );
                    listIng.add(tempProduct);
                    pAdapterIngForRep.notifyDataSetInvalidated();
                }

            }
            catch (Exception ignored)
            {

            }
        }
    }

    public static RecepesModel recepesModel;
    public void callGetRecepis()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);


        Call<RecepesModel> call = retrofitAPI.getDATARecip(id);
        call.enqueue(new Callback<RecepesModel>() {
            @Override
            public void onResponse(Call<RecepesModel> call, Response<RecepesModel> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();
                    return;
                }

                 recepesModel = new RecepesModel(0,
                        response.body().getTitle(),
                        response.body().getMinutesOfCooking(),
                        response.body().getDescription(),
                        response.body().getComment(),
                        response.body().getPhoto(),
                response.body().getRecipeType(),
                        response.body().getMealId(),
                        response.body().getDietId(),
                        response.body().getSpecificsId(),
                        response.body().getDifficultyId(),
                        response.body().getCookingId(),
                        response.body().getKitchenId(),
                        response.body().getCalories(),
                        response.body().getSquirrels(),
                        response.body().getFats(),
                        response.body().getCarbohydrates(),
                        response.body().getPhotoAnd());
                String Minutes = String.valueOf(response.body().getMinutesOfCooking());
                TextMinutesRecipes.setText(Minutes);
                textComment.setText(response.body().getComment());
                textDescription.setText(response.body().getDescription());
            }
            @Override
            public void onFailure(Call<RecepesModel> call, Throwable t) {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        });
    }


    private class callGetSteps extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Steps/?index=" + id);
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
                loading.setVisibility(View.VISIBLE);
                listSteps.clear();
                pAdapterStep.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);
                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    StepsModel tempProduct = new StepsModel(
                            productJson.getInt("StepId"),
                            productJson.getInt("RecipeId"),
                            productJson.getInt("StepNomen"),
                            productJson.getString("Description"),
                            productJson.getString("Photo"),
                            productJson.getString("PhotoAnd")
                    );
                    listSteps.add(tempProduct);
                    pAdapterStep.notifyDataSetInvalidated();
                }
                loading.setVisibility(View.GONE);
            }
            catch (Exception ignored)
            {

            }
        }
    }


}