package com.example.healthylifestylemobile;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

    public void getVisiblePassword2(View v)
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

    public void getVisiblePassword3(View v)
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
    ImageView image, image2, image3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_login, container, false);


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

        return view;
    }
}