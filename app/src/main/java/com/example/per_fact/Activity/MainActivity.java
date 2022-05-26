package com.example.per_fact.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

//import com.example.per_fact.Fragment.HomeFragment;
//import com.example.per_fact.Fragment.MypageFragment;
//import com.example.per_fact.Fragment.PlaceFragment;
import com.example.per_fact.Fragment.RoadFragment;
import com.example.per_fact.HomeFragment;
import com.example.per_fact.MapFragment;
import com.example.per_fact.MypageFragment;
import com.example.per_fact.PlaceFragment;
import com.example.per_fact.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    LinearLayout home_layout;
    BottomNavigationView bottomNavigationView;
    Fragment homeFragment,roadFragment,placeFragment, mypageFragment;

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
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.home_layout, homeFragment)
                                .commit();
                    }
                    if(homeFragment != null) getSupportFragmentManager().beginTransaction().show(homeFragment).commit();
                    if(roadFragment != null) getSupportFragmentManager().beginTransaction().hide(roadFragment).commit();
                    if(placeFragment != null) getSupportFragmentManager().beginTransaction().hide(placeFragment).commit();
                    if(mypageFragment != null) getSupportFragmentManager().beginTransaction().hide(mypageFragment).commit();
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.home_layout, new HomeFragment())
//                            .commit();
                    return true;
                }
                case R.id.tab_map: {
                    if (roadFragment == null) {
                        roadFragment = new RoadFragment();
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.home_layout, roadFragment)
                                .commit();
                    }

                    if(homeFragment != null) getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                    if(roadFragment != null) getSupportFragmentManager().beginTransaction().show(roadFragment).commit();
                    if(placeFragment != null) getSupportFragmentManager().beginTransaction().hide(placeFragment).commit();
                    if(mypageFragment != null) getSupportFragmentManager().beginTransaction().hide(mypageFragment).commit();

//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.home_layout, new RoadFragment())
//                            .commit();
                    return true;
                }
                case R.id.tab_place: {
                    if (placeFragment == null) {
                        placeFragment = new PlaceFragment();
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.home_layout, placeFragment)
                                .commit();
                    }
                    if(placeFragment != null) getSupportFragmentManager().beginTransaction().show(placeFragment).commit();
                    if(homeFragment != null) getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                    if(roadFragment != null) getSupportFragmentManager().beginTransaction().hide(roadFragment).commit();
                    if(mypageFragment != null) getSupportFragmentManager().beginTransaction().hide(mypageFragment).commit();

//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.home_layout, new PlaceFragment())
//                            .commit();
                    return true;
                }
                case R.id.tab_mypage: {

                    if (mypageFragment == null) {
                        mypageFragment = new MypageFragment();
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.home_layout, mypageFragment)
                                .commit();
                    }
                    if(homeFragment != null) getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                    if(roadFragment != null) getSupportFragmentManager().beginTransaction().hide(roadFragment).commit();
                    if(placeFragment != null) getSupportFragmentManager().beginTransaction().hide(placeFragment).commit();
                    if(mypageFragment != null) getSupportFragmentManager().beginTransaction().show(mypageFragment).commit();


//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.home_layout, new MypageFragment())
//                            .commit();
                    return true;
                }
            }
            return false;
        }
    }
    public void change_to_RoadFragment(){
        if (roadFragment == null) {
            roadFragment = new RoadFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_layout, roadFragment)
                    .commit();
        }

        if(homeFragment != null) getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
        if(roadFragment != null) getSupportFragmentManager().beginTransaction().show(roadFragment).commit();
        if(placeFragment != null) getSupportFragmentManager().beginTransaction().hide(placeFragment).commit();
        if(mypageFragment != null) getSupportFragmentManager().beginTransaction().hide(mypageFragment).commit();
    }

}