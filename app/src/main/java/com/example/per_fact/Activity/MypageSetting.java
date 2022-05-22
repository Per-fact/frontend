package com.example.per_fact.Activity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.per_fact.R;

public class MypageSetting extends AppCompatActivity {

    Button MypageSetting_password_btn;
    ImageButton MypageSetting_back_btn;
    LinearLayout MypageSetting_layout4;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.mypage_setting);

        MypageSetting_password_btn = (Button) findViewById(R.id.MypageSetting_password_btn);
        MypageSetting_back_btn = (ImageButton) findViewById(R.id.MypageSetting_back_btn);
        MypageSetting_layout4 =(LinearLayout) findViewById(R.id.MypageSetting_layout4);

        //뒤로가기 버튼 클릭시 종료
        MypageSetting_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //비밀번호변경 버튼 클릭시
        MypageSetting_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MypageSetting_password_btn.setVisibility(View.GONE);
                MypageSetting_layout4.setVisibility(View.VISIBLE);


            }
        });


    }
}
