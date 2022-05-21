package com.example.per_fact.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.per_fact.R;

public class MypageLogout extends Activity {

    Button btn_cancel;
    Button btn_logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mypage_logout);

        btn_cancel = (Button) findViewById(R.id.button);
        btn_logout = (Button) findViewById(R.id.button2);


    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
