package com.example.healthylifestylemobile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        ListView lvDataBreakfast = view.findViewById(R.id.lvDataBreakfast);
        pAdapterDairy = new AdapterDairyRation(getActivity(), listDairy);
        lvDataBreakfast.setAdapter(pAdapterDairy);

        new callGetDairy().execute();
        return view;
    }

    private class callGetDairy extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url;
                url = new URL("https://iis.ngknn.ru/ngknn/лебедевааф/api/DailyRations?t=" +3+ "&iddd="+4+ "&m=" +0);
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
                int  o =0;
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
                    o++;
                    listDairy.add(tempProduct);
                    pAdapterDairy.notifyDataSetInvalidated();
                }
                o++;
            }
            catch (Exception ignored)
            {
               Toast.makeText(getActivity(),"При выводе данных возникла ошибка", Toast.LENGTH_LONG).show();

            }
        }
    }
}