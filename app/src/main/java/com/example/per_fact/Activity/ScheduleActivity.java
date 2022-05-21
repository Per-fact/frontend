package com.example.per_fact.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.per_fact.R;

public class ScheduleActivity extends AppCompatActivity {

    ImageButton btnBack, btnCheck;
    ToggleButton toggleBtn1, toggleBtn2, toggleBtn3;
    Button start_time, end_time, btnPrepare;
    EditText title, memo;
    String[] time = {"10분", "20분", "30분", "40분", "50분", "1시간", "1시간 10분", "1시간 20분", "1시간 30분", "1시간 40분", "1시간 50분", "2시간"};
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
        btnPrepare = findViewById(R.id.btnPrepare);
    }
    private void check() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
                    Toast.makeText(ScheduleActivity.this, "알림을 끄면 준비 시작 시간에 대한 알림을 받지 못해요 :(", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(ScheduleActivity.this, "알림을 끄면 나가야 하는 시간에 대한 알림을 받지 못해요 :(", Toast.LENGTH_SHORT).show();
                }
                toggleBtn3.setText("Status: " + isChecked);
            }
        });

        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });

        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(2);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        btnPrepare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("예상 준비 시간을 선택해주세요.");
                builder.setItems(time, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:btnPrepare.setText("10분");
                                break;
                            case 1:
                                btnPrepare.setText("20분");
                                break;
                            case 2:
                                btnPrepare.setText("30분");
                                break;
                            case 3:
                                btnPrepare.setText("40분");
                                break;
                            case 4:
                                btnPrepare.setText("50분");
                                break;
                            case 5:
                                btnPrepare.setText("1시간");
                                break;
                            case 6:
                                btnPrepare.setText("1시간 10분");
                                break;
                            case 7:
                                btnPrepare.setText("1시간 20분");
                                break;
                            case 8:
                                btnPrepare.setText("1시간 30분");
                                break;
                            case 9:
                                btnPrepare.setText("1시간 40분");
                                break;
                            case 10:
                                btnPrepare.setText("1시간 50분");
                                break;
                            case 11:
                                btnPrepare.setText("2시간");
                                break;
                            default:
                                Toast.makeText(ScheduleActivity.this, "시간을 선택해주세요.", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(title.getText().toString()) || TextUtils.isEmpty(start_time.getText().toString()) || TextUtils.isEmpty(end_time.getText().toString()) || TextUtils.isEmpty(btnPrepare.getText().toString())) {
                    Toast.makeText(ScheduleActivity.this, "값을 모두 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(ScheduleActivity.this, CompleteActivity.class);
                    startActivity(intent);
                }
                }
        });

    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
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
            case 2:
                dialog = new TimePickerDialog(ScheduleActivity.this,
                        android.R.style.Theme_Holo_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(getApplicationContext(), hourOfDay + "시 " + minute + "분 을 선택했습니다", Toast.LENGTH_SHORT).show();
                        end_time.setText(hourOfDay + "시 " + minute + "분");
                    }
                }, // 값설정시 호출될 리스너 등록
                        4, 19, false); // 기본값 시분 등록
                dialog.setTitle("시간 설정");
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                return dialog;
            default:
                return super.onCreateDialog(id);
        }
    }
}
