package com.example.healthylifestylemobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Authorization extends AppCompatActivity {

    ImageView image;
    EditText textPassword, textLogin;
    TextView Hint;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);

        image = findViewById(R.id.ivVisiblePassword);
        textLogin = findViewById(R.id.textLogin);
        textPassword = findViewById(R.id.textPassword);
        Hint = findViewById(R.id.Hint);
        progressBar = findViewById(R.id.pbLoading);
        textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


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
                textPassword.setHint("Пароль");
        });

        SharedPreferences prefs = this.getSharedPreferences(
                "Date", Context.MODE_PRIVATE); // Получение данных о пользователе
    }

    public void nextMain(View v)
    {
        if(textLogin.getText().toString().equals("") || textPassword.getText().toString().equals("")) // Проверка заполненности полей
        {
            Hint.setText("Поля не заполены\nДля входа необходимо ввести логин И пароль или пройти Регистрацию");
        }
        else
        {
            callLogin();
        }
    }


    private void callLogin()
    {
        progressBar.setVisibility(View.VISIBLE);
        String login = String.valueOf(textLogin.getText());
        String password = String.valueOf(textPassword.getText());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        //ModelSendUser modelSendUser = new ModelSendUser(login, password);
        //Call<UserModel> call = retrofitAPI.createUser(modelSendUser);
        Call<UserModel> call = retrofitAPI.Login(login, password);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    Hint.setText("При авторизации возникла ошибка");
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                progressBar.setVisibility(View.GONE);
                if(response.body() != null)
                {
                    if(response.body().getLogin() == "6") {
                        Intent intent = new Intent(Authorization.this, HomePageWithCalories.class);
                        startActivity(intent);
                    }
                }
                else
                {
                    Hint.setText( "Пользователь с таким логином и паролем не найден");
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Hint.setText( "При  возникла ошибка: ");
                progressBar.setVisibility(View.GONE);
            }
        });
    }



    public void getVisiblePassword(View v)
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

    public void nextRegistation(View view)
    {
        startActivity(new Intent(this, Registration.class));
    }

}