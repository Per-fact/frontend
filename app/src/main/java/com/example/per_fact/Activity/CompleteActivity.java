package com.example.per_fact.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.per_fact.Fragment.PlaceFragment;
import com.example.per_fact.Fragment.RoadFragment;
import com.example.per_fact.R;

public class CompleteActivity extends AppCompatActivity {
    Button gotohome;
    TextView start_prepare, et_place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        gotohome = findViewById(R.id.gotoHome);
        et_place = findViewById(R.id.et_place);
        start_prepare = findViewById(R.id.start_prepare);

        listener();
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
                Intent intent = new Intent(CompleteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
