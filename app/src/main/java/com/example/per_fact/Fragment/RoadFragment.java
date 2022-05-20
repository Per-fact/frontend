package com.example.per_fact.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.per_fact.Activity.MapsActivity;
import com.example.per_fact.Activity.ScheduleActivity;
import com.example.per_fact.R;

public class RoadFragment extends Fragment {

    EditText startStation, endStation;
    Button switchStation, btnRoadFind, btnBus, btnSubway, btnTotal, btnSelect;
    ImageButton btnHome, btnBuilding;
    TextView tvHome, tvBuilding, total, tv_min, tv_busNumber, tv_startStation, tv_midStation, tv_endStation;


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

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                startActivity(intent);
            }
        });

    }
}