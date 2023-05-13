package com.example.healthylifestylemobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllPlacesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllPlacesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllPlacesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllPlacesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllPlacesFragment newInstance(String param1, String param2) {
        AllPlacesFragment fragment = new AllPlacesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static UserModel userModel;
    TextView textCaloriesEat, textCalories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //callGetUser();
    }

    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_all_places, container, false);
        textCaloriesEat =  v.findViewById(R.id.textCaloriesEat);
        textCalories =  v.findViewById(R.id.textCalories);
        progressBar = v.findViewById(R.id.pbLoading);

        callGetUser();
        return v;
    }
    String Calories;
    public void callGetUser()
    {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       // Toast.makeText(getActivity(), HomePageWithCalories.index, Toast.LENGTH_LONG).show();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<UserModel> call = retrofitAPI.getDATAUser(HomePageWithCalories.index);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                userModel = new UserModel(0, response.body().getGenderId(), response.body().getLogin(), response.body().getWeight(), response.body().getHeight(),
                        response.body().getActivityId(),  response.body().getGoalId(),  response.body().getCalories(),
                        response.body().getSquirrels(), response.body().getDateOfBirth(), response.body().getPassword(),
                        response.body().getFats(), response.body().getCarbohydrates());
                Calories = String.valueOf(response.body().getCalories());
                textCalories.setText(Calories);

                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}