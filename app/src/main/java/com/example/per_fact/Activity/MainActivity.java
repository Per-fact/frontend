package com.example.per_fact.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.per_fact.Fragment.PlaceFragment;
import com.example.per_fact.Fragment.RoadFragment;
import com.example.per_fact.Fragment.HomeFragment;
import com.example.per_fact.Fragment.MypageFragment;
import com.example.per_fact.Fragment.PlaceFragment;
import com.example.per_fact.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    LinearLayout home_layout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(); //객체 정의
        SettingListener(); //리스너 등록

        //시작할 탭 설정
        bottomNavigationView.setSelectedItemId(R.id.tab_home);
    }

    private void init() {
        home_layout = findViewById(R.id.home_layout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void SettingListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new TabSelectedListener());
    }

    class TabSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tab_home: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_layout, new HomeFragment())
                            .commit();
                    return true;
                }
                case R.id.tab_map: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_layout, new RoadFragment())
                            .commit();
                    return true;
                }
                case R.id.tab_place: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_layout, new PlaceFragment())
                            .commit();
                    return true;
                }
                case R.id.tab_mypage: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_layout, new MypageFragment())
                            .commit();
                    return true;
                }
            }
            return false;
        }
    }
}