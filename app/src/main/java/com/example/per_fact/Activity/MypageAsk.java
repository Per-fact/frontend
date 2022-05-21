package com.example.per_fact.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.per_fact.R;

public class MypageAsk extends AppCompatActivity {
    ImageButton MypageAsk_back;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.mypage_ask);

        MypageAsk_back = (ImageButton) findViewById(R.id.MypageAsk_back_btn);
        MypageAsk_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
