package com.example.healthylifestylemobile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
 * Use the {@link RecepisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecepisFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecepisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecepisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecepisFragment newInstance(String param1, String param2) {
        RecepisFragment fragment = new RecepisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TextView textVes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private List<RecepesModel> listRecepes = new ArrayList<>();

    GridView listView;
    AdapterRecepes pAdapter;
    ProgressBar loading;
    Button Entry;

    Spinner spDiets, spMinutesOfCooking, spDifficulties,
            spKitchens, spPreparations, spSpecifics,
            spRecipeTypes, spMeals;
    SearchView etextTitle;
    String textSearch;
    ConstraintLayout v;
    int i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0, i6 = 0, i7 = 0, i8 = 0;
    public int r2 = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recepis, container, false);

        loading = view.findViewById(R.id.pbLoading);
        v = view.findViewById(R.id.v);
        loading.setVisibility(View.VISIBLE);
        loading.setMax(100);

        spMinutesOfCooking = view.findViewById(R.id.spMinutesOfCooking);
        spMinutesOfCooking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (i2 > 0) {
                    //new GetR().execute();
                }
                i1++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        spDiets = view.findViewById(R.id.spDiets);
        spDiets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (i1 > 0) {
                    new GetR().execute();
                }
                i1++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spDifficulties = view.findViewById(R.id.spDifficulties);
        spDifficulties.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (i3 > 0) {
                    new GetR().execute();
                }
                i3++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spKitchens = view.findViewById(R.id.spKitchens);
        spKitchens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (i4 > 0) {
                    new GetR().execute();
                }
                i4++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spPreparations = view.findViewById(R.id.spPreparations);
        spPreparations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (i5 > 0) {
                    new GetR().execute();
                }
                i5++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spSpecifics = view.findViewById(R.id.spSpecifics);
        spSpecifics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (i6 > 0) {
                    new GetR().execute();
                }
                i6++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spRecipeTypes = view.findViewById(R.id.spRecipeTypes);
        spRecipeTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (i7 > 0) {
                    new GetR().execute();
                }
                i7++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spMeals = view.findViewById(R.id.spMeals);
        spMeals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (i8 > 0) {
                    new GetR().execute();
                }
                i8++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        etextTitle = view.findViewById(R.id.etextTitle);

        etextTitle.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                textSearch = newText;
                new GetR().execute();
                return false;
            }
        });
        textSearch = "";
        GridView ivProducts = view.findViewById(R.id.lvData);
        pAdapter = new AdapterRecepes(getActivity(), listRecepes);
        ivProducts.setAdapter(pAdapter);
        listView = view.findViewById(R.id.lvData);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = (int) id;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                StepsFragment fragment = new StepsFragment(index);
                ft.replace(R.id.RecepisPerehod, fragment);
                ft.commit();
            }
        });


        new GetR().execute();
        new GetDiets().execute();
        new GetDifficulties().execute();
        new GetKitchens().execute();
        new GetPreparations().execute();
        new GetMeals().execute();
        new GetSpecifics().execute();
        new GetRecipeTypes().execute();
        ff();


        return view;
    }
    public  void ff()
    {
        if (r2 == 4) {

            loading.setVisibility(View.GONE);
            v.setVisibility(View.VISIBLE);
        }
    }


    String[][] spMealsArray;
    String[][] spRecipeTypesArray;
    String[][] spSpecificsArray;
    String[][] spPreparationsArray;
    String[][] spKitchensArray;
    String[][] spDifficultiesArray;
    String[][] spMinutesOfCookingArray;
    String[][] spDietsArray;


    public String fild(String str) {
        if (str.equals("до 10 мин")) {
            return "10";
        } else if (str.equals("до 20 мин")) {
            return "20";
        } else if (str.equals("до 30 мин")) {
            return "30";
        } else if (str.equals("до 40 мин")) {
            return "40";
        } else if (str.equals("до 50 мин")) {
            return "50";
        } else if (str.equals("до 60 мин")) {
            return "60";
        } else if (str.equals("более 60 мин")) {
            return "60";
        } else {
            return null;
        }
    }


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
                String[] str_array = new String[tempArray.length() + 1];
                str_array[0] = "Прием пищи";
                spMealsArray = new String[tempArray.length() + 1][2];
                spMealsArray[0][0] = "0";
                spMealsArray[0][1] = "";
                for (int i = 0; i < tempArray.length(); i++) {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    str_array[i + 1] = productJson.getString("Title");
                    spMealsArray[i + 1][0] = productJson.getString("MealId");
                    spMealsArray[i + 1][1] = productJson.getString("Title");
                    r2 ++;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, str_array);
                spMeals.setAdapter(adapter);
                ff();

            } catch (Exception ignored) {

            }
        }
    }

    private class GetRecipeTypes extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/RecipeTypes");
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
                String[] str_array = new String[tempArray.length() + 1];
                str_array[0] = "Тип рецепта";
                spRecipeTypesArray = new String[tempArray.length() + 1][2];
                spRecipeTypesArray[0][0] = "0";
                spRecipeTypesArray[0][1] = "";
                for (int i = 0; i < tempArray.length(); i++) {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    str_array[i + 1] = productJson.getString("Title");
                    spRecipeTypesArray[i + 1][0] = productJson.getString("RecipeTypeId");
                    spRecipeTypesArray[i + 1][1] = productJson.getString("Title");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, str_array);
                spRecipeTypes.setAdapter(adapter);

            } catch (Exception ignored) {

            }
        }
    }

    private class GetSpecifics extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Specifics");
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
                String[] str_array = new String[tempArray.length() + 1];
                str_array[0] = "Специфика";
                spSpecificsArray = new String[tempArray.length() + 1][2];
                spSpecificsArray[0][0] = "0";
                spSpecificsArray[0][1] = "";
                for (int i = 0; i < tempArray.length(); i++) {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    str_array[i + 1] = productJson.getString("Title");
                    spSpecificsArray[i + 1][0] = productJson.getString("SpecificityId");
                    spSpecificsArray[i + 1][1] = productJson.getString("Title");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, str_array);
                spSpecifics.setAdapter(adapter);

            } catch (Exception ignored) {

            }
        }
    }

    private class GetPreparations extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Preparations");
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
                String[] str_array = new String[tempArray.length() + 1];
                str_array[0] = "Приготовление";
                spPreparationsArray = new String[tempArray.length() + 1][2];
                spPreparationsArray[0][0] = "0";
                spPreparationsArray[0][1] = "";
                for (int i = 0; i < tempArray.length(); i++) {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    str_array[i + 1] = productJson.getString("Title");
                    spPreparationsArray[i + 1][0] = productJson.getString("PreparationId");
                    spPreparationsArray[i + 1][1] = productJson.getString("Title");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, str_array);
                spPreparations.setAdapter(adapter);

            } catch (Exception ignored) {

            }
        }
    }

    private class GetKitchens extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Kitchens");
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
                String[] str_array = new String[tempArray.length() + 1];
                str_array[0] = "Кухня";
                spKitchensArray = new String[tempArray.length() + 1][2];
                spKitchensArray[0][0] = "0";
                spKitchensArray[0][1] = "";
                for (int i = 0; i < tempArray.length(); i++) {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    str_array[i + 1] = productJson.getString("Title");
                    spKitchensArray[i + 1][0] = productJson.getString("KitchenId");
                    spKitchensArray[i + 1][1] = productJson.getString("Title");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, str_array);
                spKitchens.setAdapter(adapter);

            } catch (Exception ignored) {

            }
        }
    }

    private class GetDifficulties extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Difficulties");
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
                String[] str_array = new String[tempArray.length() + 1];
                str_array[0] = "Сложность";
                spDifficultiesArray = new String[tempArray.length() + 1][2];
                spDifficultiesArray[0][0] = "0";
                spDifficultiesArray[0][1] = "";
                for (int i = 0; i < tempArray.length(); i++) {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    str_array[i + 1] = productJson.getString("Title");
                    spDifficultiesArray[i + 1][0] = productJson.getString("DifficultiesId");
                    spDifficultiesArray[i + 1][1] = productJson.getString("Title");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, str_array);
                spDifficulties.setAdapter(adapter);

            } catch (Exception ignored) {

            }
        }
    }

    private class GetDiets extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Diets");
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
                String[] str_array = new String[tempArray.length() + 1];
                str_array[0] = "Диеты";
                spDietsArray = new String[tempArray.length() + 1][2];
                spDietsArray[0][0] = "0";
                spDietsArray[0][1] = "";
                for (int i = 0; i < tempArray.length(); i++) {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    str_array[i + 1] = productJson.getString("Title");
                    spDietsArray[i + 1][0] = productJson.getString("DietId");
                    spDietsArray[i + 1][1] = productJson.getString("Title");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, str_array);
                spDiets.setAdapter(adapter);

            } catch (Exception ignored) {

            }
        }
    }

    private class GetR extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                //loading.setVisibility(View.VISIBLE);
                String Meals;
                String RecipeTypes;
                String Specifics;
                String Preparations;
                String Kitchens;
                String Difficulties;
                //String MinutesOfCooking;
                String Diets;
                URL url;
                try {

                    Meals = spMeals.getSelectedItem().toString();
                    RecipeTypes = spRecipeTypes.getSelectedItem().toString();
                    Specifics = spSpecifics.getSelectedItem().toString();
                    Kitchens = spKitchens.getSelectedItem().toString();
                    //MinutesOfCooking = spMinutesOfCooking.getSelectedItem().toString();
                    Diets = spDiets.getSelectedItem().toString();
                    Difficulties = spDifficulties.getSelectedItem().toString();
                    Preparations = spPreparations.getSelectedItem().toString();
                    url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Recipes?b=" + "&name=" + textSearch + "&indexMeals=" + getIdMeals(Meals) + "&indexDiets=" + getIdDiets(Diets) + "&indexRecipeTypes=" +
                            getIdRecipeTypes(RecipeTypes) + "&indexSpecifics=" + getIdSpecifics(Specifics) +
                            "&indexPreparations=" + getIdPreparations(Preparations) + "&indexKitchens=" + getIdKitchens(Kitchens) +
                            "&indexDifficulties=" + getIdDifficulties(Difficulties));

                } catch (Exception exception) {
                    Meals = "";
                    RecipeTypes = "";
                    Specifics = "";
                    Preparations = "";
                    Kitchens = "";
                    Difficulties = "";
                    //MinutesOfCooking = "";
                    Diets = "";
                    url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Recipes?b=" + "&name=" + "" + "&indexMeals=" + 0 + "&indexDiets=" + 0 + "&indexRecipeTypes=" +
                            0 + "&indexSpecifics=" + 0 +
                            "&indexPreparations=" + 0 + "&indexKitchens=" + 0 +
                            "&indexDifficulties=" + 0 + "&indexMinutesOfCooking=" + 0);
                    //textVes.setText(Meals);
                    //url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/Recipes?b=" +"&name=" + "" +"&id_address=" + 0 +"&id_type_locality=" + 0 +"&fieldSort=" + field +"&valueSort=" + fieldOrder);
                }

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

                listRecepes.clear();
                pAdapter.notifyDataSetInvalidated();
                JSONArray tempArray = new JSONArray(s);
                for (int i = 0; i < tempArray.length(); i++) {
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
                    listRecepes.add(tempProduct);
                    pAdapter.notifyDataSetInvalidated();

                }
            } catch (Exception ignored) {

            }
        }
    }

    private int getIdMeals(String Meals) {
        for (int i = 0; i < spMealsArray.length; i++) {
            if (spMealsArray[i][1].equals(Meals)) {
                return Integer.parseInt(spMealsArray[i][0]);
            }
        }
        return 0;
    }

    private int getIdDiets(String Diets) {
        for (int i = 0; i < spDietsArray.length; i++) {
            if (spDietsArray[i][1].equals(Diets)) {
                return Integer.parseInt(spDietsArray[i][0]);
            }
        }
        return 0;
    }

    private int getIdDifficulties(String Difficulties) {
        for (int i = 0; i < spDifficultiesArray.length; i++) {
            if (spDifficultiesArray[i][1].equals(Difficulties)) {
                return Integer.parseInt(spDifficultiesArray[i][0]);
            }
        }
        return 0;
    }

    private int getIdKitchens(String Kitchens) {
        for (int i = 0; i < spKitchensArray.length; i++) {
            if (spKitchensArray[i][1].equals(Kitchens)) {
                return Integer.parseInt(spKitchensArray[i][0]);
            }
        }
        return 0;
    }

    private int getIdMinutesOfCooking(String MinutesOfCooking) {
        for (int i = 0; i < spMinutesOfCookingArray.length; i++) {
            if (spMinutesOfCookingArray[i][1].equals(MinutesOfCooking)) {
                return Integer.parseInt(spMinutesOfCookingArray[i][0]);
            }
        }
        return 0;
    }

    private int getIdPreparations(String Preparations) {
        for (int i = 0; i < spPreparationsArray.length; i++) {
            if (spPreparationsArray[i][1].equals(Preparations)) {
                return Integer.parseInt(spPreparationsArray[i][0]);
            }
        }
        return 0;
    }

    private int getIdRecipeTypes(String RecipeTypes) {
        for (int i = 0; i < spRecipeTypesArray.length; i++) {
            if (spRecipeTypesArray[i][1].equals(RecipeTypes)) {
                return Integer.parseInt(spRecipeTypesArray[i][0]);
            }
        }
        return 0;
    }

    private int getIdSpecifics(String Specifics) {
        for (int i = 0; i < spSpecificsArray.length; i++) {
            if (spSpecificsArray[i][1].equals(Specifics)) {
                return Integer.parseInt(spSpecificsArray[i][0]);
            }
        }
        return 0;
    }
}