package com.example.per_fact.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.per_fact.Activity.MapsActivity;
import com.example.per_fact.Activity.OfficeActivity;
import com.example.per_fact.Activity.ScheduleActivity;
import com.example.per_fact.Data.OdsayData;
import com.example.per_fact.R;
import com.example.per_fact.Retrofit.RetrofitOdsay;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoadFragment extends Fragment {

    EditText startStation, endStation;
    Button switchStation, btnRoadFind, btnBus, btnSubway, btnTotal, btnSelect;
    ImageButton btnHome, btnBuilding;
    TextView tvHome, tvBuilding, total, tv_min, tv_busNumber, tv_startStation, tv_midStation, tv_endStation;
    String APIKEY_ID = "ar7zysdloh";
    String APIKEY = "Zd5Pm31DyEAhVNTgL7bUWIMzhFoiBoOB4tEL9CrU";
    String start, end;

    public RoadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_road, container, false);

        startStation = v.findViewById(R.id.et_startStation);
        endStation = v.findViewById(R.id.et_endStation);
        switchStation = v.findViewById(R.id.switchStation);
        btnRoadFind = v.findViewById(R.id.btnRoadFind);
        btnBus = v.findViewById(R.id.btnBus);
        btnSubway = v.findViewById(R.id.btnSubway);
        btnTotal = v.findViewById(R.id.btnTotal);
        btnHome = v.findViewById(R.id.btnHome);
        btnBuilding = v.findViewById(R.id.btnBuilding);
        btnSelect = v.findViewById(R.id.btnSelect);
        tvHome = v.findViewById(R.id.tvHome);
        tvBuilding = v.findViewById(R.id.tvBuilding);
        total = v.findViewById(R.id.total);
        tv_min = v.findViewById(R.id.tv_min);
        tv_busNumber = v.findViewById(R.id.tv_busNumber);
        tv_startStation = v.findViewById(R.id.tv_startStation);
        tv_midStation = v.findViewById(R.id.tv_midStation);
        tv_endStation = v.findViewById(R.id.tv_EndStation);

        clickListener();

        return v;
    }

    private void clickListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        btnBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OfficeActivity.class);
                startActivity(intent);
            }
        });

        btnRoadFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = startStation.getText().toString();
                end = endStation.getText().toString();
                if (start != null && end != null) {
                    Call<OdsayData> call = RetrofitOdsay.getRetrofit().getSearch().getPath("Fm8lbwL5ydRCXpTuXJaCnx6h2rPo1%2Bs%2B5wo%2BKvJjf6g", "0", "126.926493082645", "37.6134436427887", "127.126936754911", "37.5004198786564");
                    call.enqueue(new Callback<OdsayData>() {
                        @Override
                        public void onResponse(Call<OdsayData> call, Response<OdsayData> response) {
                            if (response.isSuccessful()) {
                                Log.i("sooyeon", "리스폰스는 옴");
                                if (response.body() != null) {
//                                                  for(int i = 0; i < response.body().resultList.get(i).pathList.size(); i++) {
                                    Log.i("sooyeon", response.body().resultList.toString());
//                                                 }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<OdsayData> call, Throwable t) {

                        }
                    });
                }

                btnSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}