package com.example.healthylifestylemobile;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link AdPhotoFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class AdPhotoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

int id;
    public AdPhotoFragment(int id) {
        // Required empty public constructor
this.id = 45;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdPhotoFragment.
     */
//    // TODO: Rename and change types and number of parameters
//    public static AdPhotoFragment newInstance(String param1, String param2) {
//        AdPhotoFragment fragment = new AdPhotoFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
    public static StepsModel stepsModel;
    String s;
    String varcharPicture;
    ImageView imageProfile;
    EditText Titile;
    Button Save;

    public void UpdatePicture()
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        someActivityResultLauncher.launch(photoPickerIntent);
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Bitmap bitmap = null;
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Uri selectedImage = result.getData().getData();
                        try
                        {
                            Context context = (Context)getActivity();
                            ContentResolver result1 = (ContentResolver)context.getContentResolver();
                            bitmap = MediaStore.Images.Media.getBitmap(result1, selectedImage);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        imageProfile.setImageBitmap(null);
                        imageProfile.setImageBitmap(bitmap);
                        varcharPicture = BitMapToString(bitmap);
                        //Toast.makeText(getActivity(), varcharPicture, Toast.LENGTH_LONG).show();
                        stepsModel.setPhotoAnd(varcharPicture);
                        callPUTDataMethod();
                    }
                }
            });

    public String BitMapToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);
        byte [] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ad_photo, container, false);
        imageProfile = view.findViewById(R.id.imageProfile);
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePicture();
            }
        });


        Titile =  view.findViewById(R.id.Titile);



        callGetUser();
        return view;
    }

    private void callPUTDataMethod() {
        String t = String.valueOf(Titile.getText());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        
       // s = String.valueOf(varcharPicture);
//        RecepesModel modal = new RecepesModel(0,t,
//                0, null, null, null,
//                0,
//                0, 0, 0,0,
//                0, 0, 0, 0,
//                0,0, null);
//        String p = String.valueOf(varcharPicture);
//        recepesModel.setPhotoAnd(p);
        Call<StepsModel> call = retrofitAPI.updateSteps(id, stepsModel);
        call.enqueue(new Callback<StepsModel>() {
            @Override
            public void onResponse(Call<StepsModel> call, Response<StepsModel> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(getActivity(), "При изменение данных возникла ошибка", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getActivity(),"Данные изменены", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<StepsModel> call, Throwable t) {
                Toast.makeText(getActivity(), "При изменение записи возникла ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


    public void callGetUser()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);


        Call<StepsModel> call = retrofitAPI.getDATASteps(id);
        call.enqueue(new Callback<StepsModel>() {
            @Override
            public void onResponse(Call<StepsModel> call, Response<StepsModel> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

                    return;
                }

                stepsModel = new StepsModel(0,
                        response.body().getRecipeId(),
                        response.body().getStepNomen(),
                        response.body().getDescription(),
                        response.body().getPhoto(),
                        response.body().getPhotoAnd());

                Titile.setText(response.body().getDescription());

            }
            @Override
            public void onFailure(Call<StepsModel> call, Throwable t) {
                Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        });
    }

}