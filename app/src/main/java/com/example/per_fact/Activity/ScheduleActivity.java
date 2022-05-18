package com.example.per_fact.Activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.per_fact.R;

public class ScheduleActivity extends AppCompatActivity {

    ImageButton btnBack, btnCheck;
    ToggleButton toggleBtn1, toggleBtn2, toggleBtn3;
    Button start_time, end_time;
    TextView prepare;
    EditText title, memo;

    final int DIALOG_TIME = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        init();
        check();
    }

    private void init() {
        btnBack = findViewById(R.id.btnBack);
        btnCheck = findViewById(R.id.btnCheck);
        toggleBtn1 = findViewById(R.id.toggleBtn1);
        toggleBtn2 = findViewById(R.id.toggleBtn2);
        toggleBtn3 = findViewById(R.id.toggleBtn3);
        start_time = findViewById(R.id.startTime);
        end_time = findViewById(R.id.endTime);
        title = findViewById(R.id.et_title);
        memo = findViewById(R.id.et_memo);

    }
    private void check() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        if (title.getText().toString().equals("")) {
//            Toast.makeText(this, "값을 모두 입력해주세요.", Toast.LENGTH_LONG).show();
//        }

        //종일 togglebtn
        toggleBtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(ScheduleActivity.this, "알림 설정이 ON 되었습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ScheduleActivity.this, "알림을 끄면 준비 시작 시간에 대한 알림을 받지 못해요 :(\n그래도 괜찮으시다면 알림 OFF를 해주세요.", Toast.LENGTH_SHORT).show();
                }
                toggleBtn3.setText("Status: " + isChecked);
            }
        });

        //준비 시작 시간 알림 togglebtn
        toggleBtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(ScheduleActivity.this, "알림 설정이 ON 되었습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ScheduleActivity.this, "알림을 끄면 준비 시작 시간에 대한 알림을 받지 못해요 :(\n그래도 괜찮으시다면 알림 OFF를 해주세요.", Toast.LENGTH_SHORT).show();
                }
                toggleBtn3.setText("Status: " + isChecked);
            }
        });

        //외출 시간 알림 togglebtn
        toggleBtn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(ScheduleActivity.this, "알림 설정이 ON 되었습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ScheduleActivity.this, "알림을 끄면 나가야 하 시간에 대한 알림을 받지 못해요 :(\n그래도 괜찮으시다면 알림 OFF를 해주세요.", Toast.LENGTH_SHORT).show();
                }
                toggleBtn3.setText("Status: " + isChecked);
            }
        });

        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_TIME);
            }
        });

    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        if ( id == DIALOG_TIME) {
            TimePickerDialog dialog = new TimePickerDialog(ScheduleActivity.this,
                    android.R.style.Theme_Holo_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Toast.makeText(getApplicationContext(), hourOfDay + "시 " + minute + "분 을 선택했습니다", Toast.LENGTH_SHORT).show();
                    start_time.setText(hourOfDay + "시 " + minute + "분");
                }
            }, // 값설정시 호출될 리스너 등록
                    4, 19, false); // 기본값 시분 등록
            dialog.setTitle("시간 설정");
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            return dialog;
        }


        return super.onCreateDialog(id);
    }


}
