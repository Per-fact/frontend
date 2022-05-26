package com.example.per_fact;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AlertDialog;

import android.widget.Toast;

import com.example.per_fact.Api.CheckListService;
import com.example.per_fact.Data.CheckListData;
import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckListActivity extends AppCompatActivity {
    ImageButton buttonInsert, btn_back;
    private ArrayList<com.example.per_fact.CheckListDictionary> mArrayList, mArrayList_check;
    private com.example.per_fact.CheckListCustomAdapter mAdapter;
    private int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        //리싸이클러 뷰 설정
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mArrayList = new ArrayList<>();

        //mAdapter = new CustomAdapter( mArrayList);
        mAdapter = new com.example.per_fact.CheckListCustomAdapter( this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        //메인에서 체크리스트 받아와 화면에 출력
        mArrayList_check = new ArrayList<>();
        Intent intent = getIntent();
        mArrayList_check = intent.getParcelableArrayListExtra("checklist");
        for (int i = 0; i < mArrayList_check.size(); i++) {

            String item = mArrayList_check.get(i).getId();
            Boolean checked = mArrayList_check.get(i).isSelected();
            com.example.per_fact.CheckListDictionary dict = new com.example.per_fact.CheckListDictionary(item, checked);

            mArrayList.add(dict); //마지막 줄에 삽입
            mAdapter.notifyDataSetChanged(); //변경된 데이터를 화면에 반영
        }

        //Retrofit 객체 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.220.224:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CheckListService checkListService = retrofit.create(CheckListService.class);


        buttonInsert = (ImageButton)findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            //화면 아래쪽에 있는 추가 버튼 클릭시
            @Override
            public void onClick(View v) {

                //edit_box를 불러와서 입력
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckListActivity.this);
                View view = LayoutInflater.from(CheckListActivity.this)
                        .inflate(R.layout.edit_box, null, false);
                builder.setView(view);
                final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);
                ButtonSubmit.setText("확인");


                final AlertDialog dialog = builder.create();
                ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String strID = editTextID.getText().toString();

                        //Api 호출(POST)
                        CheckListData checkListData = new CheckListData(0, strID, "false");
                        checkListService.postCheckList(checkListData).enqueue(new Callback<CheckListData>() {
                            @Override
                            public void onResponse(Call<CheckListData> call, Response<CheckListData> response) {
                                CheckListData data = response.body();
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        Log.d("TEST", data.toString());
                                    }
                                } else {
                                    Log.d("TEST", "error");
                                }
//                                Log.d("TEST", "Post 성공");

                            }

                            @Override
                            public void onFailure(Call<CheckListData> call, Throwable t) {

                            }
                        });

                        Boolean checked = false;
                        com.example.per_fact.CheckListDictionary dict = new com.example.per_fact.CheckListDictionary(strID,checked);
                        mArrayList.add(dict); //마지막 줄에 삽입
                        mAdapter.notifyDataSetChanged(); //변경된 데이터를 화면에 반영
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });


        //뒤로가기 버튼 클릭시
        btn_back = (ImageButton) findViewById(R.id.Check_list_back_btn);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //체크박스 상태를 배열에 담아 전달
                Intent data = new Intent();
                data.putParcelableArrayListExtra("checklist", mArrayList);
                setResult(0,data);

                finish();

            }
        });
    }
}
