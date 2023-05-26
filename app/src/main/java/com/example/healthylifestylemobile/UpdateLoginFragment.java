package com.example.healthylifestylemobile;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateLoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateLoginFragment newInstance(String param1, String param2) {
        UpdateLoginFragment fragment = new UpdateLoginFragment();
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
    public void getVisiblePassword()
    {
        if(textPassword.getInputType() == 129)
        {
            image.setImageResource(R.drawable.icon_password_not_visible1);
            textPassword.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        else
        {
            image.setImageResource(R.drawable.icon_password_visible1);
            textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void getVisiblePassword2()
    {
        if(textPassword2.getInputType() == 129)
        {
            image2.setImageResource(R.drawable.icon_password_not_visible1);
            textPassword2.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        else
        {
            image2.setImageResource(R.drawable.icon_password_visible1);
            textPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void getVisiblePassword3()
    {
        if(textPassword3.getInputType() == 129)
        {
            image3.setImageResource(R.drawable.icon_password_not_visible1);
            textPassword3.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        else
        {
            image3.setImageResource(R.drawable.icon_password_visible1);
            textPassword3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }
    EditText textPassword,textPassword2, textPassword3,textLogin;
    TextView Hint;
    ProgressBar progressBar;
    Button Back, Entry;
    ImageView image, image2, image3;

    UserModel user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_login, container, false);
        user = ProfileFragment.userModel;




        Back = (Button) view.findViewById(R.id.Back);
        Entry = (Button) view.findViewById(R.id.Entry);
        Entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = String.valueOf(textLogin.getText());
                String password = String.valueOf(textPassword.getText());
                if(login.replaceAll("\\s+", " ").equals(""))
                {
                    Hint.setText("Логин не может быть пустым");
                    return;
                }


                    callProverkaPassword();

                //callProverkaPassword();
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ProfileFragment fragment = new ProfileFragment();
                ft.replace(R.id.UserProfilePerehod, fragment);
                ft.commit();
            }
        });

        Hint = view.findViewById(R.id.Hint);
        image = view.findViewById(R.id.ivVisiblePassword);
        image2 = view.findViewById(R.id.ivVisiblePassword2);
        image3 = view.findViewById(R.id.ivVisiblePassword3);
        textLogin = view.findViewById(R.id.textLogin);
        textPassword = view.findViewById(R.id.textPassword);
        textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        textPassword2 = view.findViewById(R.id.textPassword2);
        textPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        textPassword3 = view.findViewById(R.id.textPassword3);
        textPassword3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        progressBar = view.findViewById(R.id.pbLoading);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVisiblePassword();
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVisiblePassword2();
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVisiblePassword3();
            }
        });

        textLogin.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                textLogin.setHint("");
            else
                textLogin.setHint("Логин");
        });
        textPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                textPassword.setHint("");
            else
                textPassword.setHint("Старый пароль");
        });
        textPassword2.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                textPassword2.setHint("");
            else
                textPassword2.setHint("Новый пароль");
        });
        textPassword3.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                textPassword3.setHint("");
            else
                textPassword3.setHint("Повторите пароль");
        });

        callGetUser();
        return view;
    }

    public void callGetUser()
    {
        progressBar.setVisibility(View.VISIBLE);
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
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                user = new UserModel(0, response.body().getGenderId(), response.body().getLogin(), response.body().getWeight(), response.body().getHeight(),
                        response.body().getActivityId(),  response.body().getGoalId(),  response.body().getCalories(),
                        response.body().getSquirrels(), response.body().getDateOfBirth(), response.body().getPassword(),
                        response.body().getFats(), response.body().getCarbohydrates());

                textLogin.setText(response.body().getLogin());
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Hint.setText("При выводе данных возникла ошибка!");
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void callProverkaPassword()
    {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        String confirmPassword = String.valueOf(textPassword.getText());
        Call<UserModel> call = retrofitAPI.Login(user.getLogin(), confirmPassword);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    Hint.setText("При проверке пароля возникла ошибка");
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(response.body() != null)
                {
                    if(!ProfileFragment.userModel.getLogin().equals(String.valueOf(textLogin.getText())))
                    {
                        callRegistration();
                        Hint.setText("");
                    }
                    else
                    {
                        callPUTDataMethod(HomePageWithCalories.index);
                        Hint.setText("");
                    }
                }
                else
                {
                    Hint.setText("Старый пароль введён не верно");
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Hint.setText("При проверке пароля возникла ошибка: ");
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void callRegistration()
    {
        progressBar.setVisibility(View.VISIBLE);
        String login = String.valueOf(textLogin.getText());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<Boolean> call = retrofitAPI.examinationRegistration(login);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (!response.isSuccessful()) {
                    Hint.setText( "При проверке логина возникла ошибка");
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(response.body().equals(false))
                {
                    callPUTDataMethod(HomePageWithCalories.index);
                    Hint.setText("");
                }
                else
                {
                    Hint.setText("Пользователь с таким логиным уже зарегистрирован, выберите другой логин");
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Hint.setText( "При проверке логина возникла ошибка: ");
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void callPUTDataMethod(int index) {

        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        user.setLogin(String.valueOf(textLogin.getText()));
        String password = String.valueOf(textPassword2.getText());
        String confirmPassword = String.valueOf(textPassword3.getText());
        if(!password.equals(confirmPassword))
        {
            Hint.setText("Повторный пароль введён не верно");
            progressBar.setVisibility(View.GONE);
            return;
        }
        if(!password.replaceAll("\\s+", " ").equals(""))
        {
            user.setPassword(password);
        }
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<UserModel> call = retrofitAPI.updateLogin(index, user, index);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                progressBar.setVisibility(View.GONE);
                if(!response.isSuccessful())
                {
                    Hint.setText("При измнение данных пользователя возникла ошибка");
                    return;
                }
                Toast.makeText(getActivity(),"Данные изменены", Toast.LENGTH_LONG).show();
                Hint.setText("");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ProfileFragment fragment = new ProfileFragment();
                ft.replace(R.id.UserProfilePerehod, fragment);
                ft.commit();
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Hint.setText( "При измнение данных пользователя возникла ошибка: ");
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}