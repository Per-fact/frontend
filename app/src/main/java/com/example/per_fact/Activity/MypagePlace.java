package com.example.per_fact.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.per_fact.R;

public class MypagePlace extends AppCompatActivity {

    ImageButton MypagePlace_back_btn;
    ImageView fullHeart, fullHeart2;
    ConstraintLayout MypagePlace_lay1, MypagePlace_lay2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_place);

        MypagePlace_back_btn = (ImageButton) findViewById(R.id.MypagePlace_back_btn);
        fullHeart = findViewById(R.id.full_heart);
        fullHeart2 = findViewById(R.id.full_heart2);
        MypagePlace_lay1 = findViewById(R.id.MypagePlace_lay1);
        MypagePlace_lay2 = findViewById(R.id.MypagePlace_lay2);

        //뒤로가기 버튼 클릭시 종료
        MypagePlace_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        fullHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MypagePlace_lay1.setVisibility(View.GONE);
                Toast.makeText(view.getContext(), "스크랩이 취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        fullHeart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MypagePlace_lay2.setVisibility(View.GONE);
                Toast.makeText(view.getContext(), "스크랩이 취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}