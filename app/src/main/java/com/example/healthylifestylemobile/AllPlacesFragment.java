package com.example.healthylifestylemobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
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
    public static DailyRationsModel dailyRationsModel;
    TextView textCaloriesEat, textCalories;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ProgressBar loading;
    ConstraintLayout v;
    int r2=0;
    LinearLayout FF, F;
    TextView One, Two, Three, OneFAQ, TwoFAQ, ThreeFAQ,  Four, FourFAQ , Five,  FiveFAQ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_all_places, container, false);
        textCaloriesEat =  v.findViewById(R.id.textCaloriesEat);
        textCalories =  v.findViewById(R.id.textCalories);

        loading = v.findViewById(R.id.pbLoading);
        FF = v.findViewById(R.id.FF);
        F = v.findViewById(R.id.F);
        loading.setVisibility(View.VISIBLE);


        One = v.findViewById(R.id.One);
        Two = v.findViewById(R.id.Two);
        Three = v.findViewById(R.id.Three);
        OneFAQ = v.findViewById(R.id.OneFAQ);
        TwoFAQ = v.findViewById(R.id.TwoFAQ);
        ThreeFAQ = v.findViewById(R.id.ThreeFAQ);
        Four = v.findViewById(R.id.Four);
        FourFAQ = v.findViewById(R.id.FourFAQ);
        Five = v.findViewById(R.id.Five);
        FiveFAQ = v.findViewById(R.id.FiveFAQ);

        Five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FiveFAQ.getVisibility() == View.VISIBLE)
                {
                    FiveFAQ.setVisibility(View.GONE);
                }
                else {
                    FiveFAQ.setVisibility(View.VISIBLE);
                }
            }
        });
        Four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FourFAQ.getVisibility() == View.VISIBLE)
                {
                    FourFAQ.setVisibility(View.GONE);
                }
                else {
                    FourFAQ.setVisibility(View.VISIBLE);
                }
            }
        });
        One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(OneFAQ.getVisibility() == View.VISIBLE)
                {
                    OneFAQ.setVisibility(View.GONE);
                }
                else {
                    OneFAQ.setVisibility(View.VISIBLE);
                }
            }
        });
        Two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TwoFAQ.getVisibility() == View.VISIBLE)
                {
                    TwoFAQ.setVisibility(View.GONE);
                }
                else {
                    TwoFAQ.setVisibility(View.VISIBLE);
                }
            }
        });
        Three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ThreeFAQ.getVisibility() == View.VISIBLE)
                {
                    ThreeFAQ.setVisibility(View.GONE);
                }
                else {
                    ThreeFAQ.setVisibility(View.VISIBLE);
                }
            }
        });

        callPutDail();

        callGetUser();
        callGetDail();

        ff();
        return v;
    }
    public  void ff()
    {
        if (r2 == 3) {

            loading.setVisibility(View.GONE);
            FF.setVisibility(View.VISIBLE);
            F.setVisibility(View.VISIBLE);
        }
    }
    String Calories;
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
                    Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

                    return;
                }
                userModel = new UserModel(0, response.body().getGenderId(), response.body().getLogin(), response.body().getWeight(), response.body().getHeight(),
                        response.body().getActivityId(),  response.body().getGoalId(),  response.body().getCalories(),
                        response.body().getSquirrels(), response.body().getDateOfBirth(), response.body().getPassword(),
                        response.body().getFats(), response.body().getCarbohydrates());
                Calories = String.valueOf(response.body().getCalories());
                textCalories.setText(Calories);
                r2++;
                ff();
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void callGetDail()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<DailyRationsModel> call = retrofitAPI.getCaloriesUser(HomePageWithCalories.index,HomePageWithCalories.index);
        call.enqueue(new Callback<DailyRationsModel>() {
            @Override
            public void onResponse(Call<DailyRationsModel> call, Response<DailyRationsModel> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(getActivity(),"При выводе съеденных калорий произошла ошибка!" , Toast.LENGTH_LONG).show();

                    return;
                }
                dailyRationsModel = new DailyRationsModel(0,
                        response.body().getUserId(),
                        response.body().getRecepeId(),
                        response.body().getDate(),
                        response.body().getCalories(),
                        response.body().getSquirrels(),
                        response.body().getFats(),
                        response.body().getCarbohydrates(),
                        response.body().getCaloriesUsers(),
                        response.body().getMealId());


                //String answ = "3.14159";
                //String newElement = answ.substring(0, answ.split("[.]")[0].length()+3);
                float ss = Float.parseFloat(String.valueOf(response.body().getCaloriesUsers()));
                String s = String.valueOf(ss);
                String newElement = s.substring(0, s.split("[.]")[0].length()+3);
                textCaloriesEat.setText(newElement);
                r2++;
                ff();
            }
            @Override
            public void onFailure(Call<DailyRationsModel> call, Throwable t) {
                Toast.makeText(getActivity(),"При выводе съеденных калорий произошла ошибка!", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void callPutDail()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<DailyRationsModel> call = retrofitAPI.updateDaily(HomePageWithCalories.index);
        call.enqueue(new Callback<DailyRationsModel>() {
            @Override
            public void onResponse(Call<DailyRationsModel> call, Response<DailyRationsModel> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(getActivity(),"При выводе съеденных калорий произошла ошибка" , Toast.LENGTH_LONG).show();

                    return;
                }
                r2++;
                ff();

            }
            @Override
            public void onFailure(Call<DailyRationsModel> call, Throwable t) {
                Toast.makeText(getActivity(),"При выводе съеденных калорий произошла ошибка", Toast.LENGTH_LONG).show();
            }
        });
    }

}