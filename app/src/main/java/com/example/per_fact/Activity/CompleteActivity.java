package com.example.per_fact.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.per_fact.Fragment.HomeFragment;
import com.example.per_fact.Fragment.PlaceFragment;
import com.example.per_fact.Fragment.RoadFragment;
import com.example.per_fact.R;

import java.lang.reflect.Array;

public class CompleteActivity extends AppCompatActivity {
    Button gotohome;
    TextView start_prepare, et_place;
    int prepareTime, startHour, startMin;
    String startDate, suggestInfo;
    int transportMin = 47;
    int[] startInfo = {startHour, startMin};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        gotohome = findViewById(R.id.gotoHome);
        et_place = findViewById(R.id.et_place);
        start_prepare = findViewById(R.id.start_prepare);


        Intent completeIntent = getIntent();
        prepareTime = completeIntent.getIntExtra("prepareTime", 0);
        startDate = completeIntent.getStringExtra("startDate");
        startHour = completeIntent.getIntExtra("startHour", 0);
        startMin = completeIntent.getIntExtra("startMin", 0);

        //교통수단 시간 먼저 계산
        startInfo = caculateTransport(prepareTime, startHour, startMin);

        Log.d("startHour", "startInfo[0]");
        Log.d("startMin", "startInfo[1]");

        //예상 준비 시간 계산
        startInfo = caculateReadyTime(prepareTime, startInfo[0], startInfo[1]);

        //출발 준비 시간 제안
        suggestInfo = startDate.concat(" 일 " + startInfo[0] + "시 " + startInfo[1] + "분부터 준비를 시작해주세요!");

        start_prepare.setText(suggestInfo);


        listener();
    }

    //준비 시작 시간 계산
    //약속 시간 - 교통 수단 시간 - 예상 준비 시간

    public int[] caculateTransport(int transportTime, int startHour, int startMin) {

        //분 먼저 계산
        if (startHour < transportTime) {
            startHour--;
            startMin = (startMin + 60) - transportTime;
        } else {
            startMin -= transportTime;
        }

        startInfo[0] = startHour;
        startInfo[1] = startMin;

        return startInfo;
    }

    public int[] caculateReadyTime(int readyTime, int startHour, int startMin) {

        if (readyTime > 60) {
            startHour -= (readyTime / 60);
            readyTime -= ((readyTime / 60) * 60);
            startMin -= readyTime;
        } else {
            if (startMin < readyTime) {
                startHour--;
                startMin = (startMin + 60) - readyTime;
            } else {
                startMin -= readyTime;
            }
        }

        startInfo[0] = startHour;
        startInfo[1] = startMin;

        return startInfo;
    }


    private void listener() {
        gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompleteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        et_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompleteActivity.this, PlaceFragment.class);


            }
        });

    }
}
