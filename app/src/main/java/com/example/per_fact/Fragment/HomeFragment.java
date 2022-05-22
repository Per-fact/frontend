package com.example.per_fact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment {
    private ArrayList<com.example.per_fact.CheckListDictionary> mArrayList;

    CheckBox checkBox0;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    CheckBox checkBox6;
    CheckBox checkBox7;
    CheckBox checkBox8;
    CheckBox checkBox9;

    ImageButton btn_checkList_add;
    ImageButton btn_week_schedule_open;
    ImageButton btn_week_schedule_close;
    LinearLayout week_schedule_item2;

    static RequestQueue requestQueue;
    ImageView ic_weather;
    TextView current_tmp;
    TextView txt_weather;

    //상단 날짜 출력바
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        btn_week_schedule_open =(ImageButton)v.findViewById(R.id.btn_week_schedule_open);
        btn_week_schedule_close =(ImageButton)v.findViewById(R.id.btn_week_schedule_close);
        week_schedule_item2 =(LinearLayout)v.findViewById(R.id.week_schedule_item2);

        ic_weather = (ImageView)v.findViewById(R.id.ic_weather);
        current_tmp = (TextView) v.findViewById(R.id.txt_current_tmp);
        txt_weather = (TextView) v.findViewById(R.id.txt_weather);

        btn_checkList_add = (ImageButton) v.findViewById(R.id.check_add_button);
        checkBox0 = (CheckBox) v.findViewById(R.id.checkBox0);
        checkBox1 = (CheckBox) v.findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) v.findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) v.findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) v.findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) v.findViewById(R.id.checkBox5);
        checkBox6 = (CheckBox) v.findViewById(R.id.checkBox6);
        checkBox7 = (CheckBox) v.findViewById(R.id.checkBox7);
        checkBox8 = (CheckBox) v.findViewById(R.id.checkBox8);
        checkBox9 = (CheckBox) v.findViewById(R.id.checkBox9);


        //상단 텍스트바 날짜 출력
        TextView mTextView = v.findViewById(R.id.date_View);
        mTextView.setText(getTime());

        //현재 서울 날씨 출력
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }
        CurrentCall();


        //주간 스케줄 펼치기 버튼
        btn_week_schedule_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_week_schedule_open.setVisibility(btn_week_schedule_open.GONE);
                btn_week_schedule_close.setVisibility(btn_week_schedule_close.VISIBLE);

                week_schedule_item2.setVisibility(week_schedule_item2.VISIBLE);
            }
        });
        //주간 스케줄 단기 버튼
        btn_week_schedule_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_week_schedule_open.setVisibility(btn_week_schedule_open.VISIBLE);
                btn_week_schedule_close.setVisibility(btn_week_schedule_close.GONE);

                week_schedule_item2.setVisibility(week_schedule_item2.GONE);
            }
        });

        mArrayList = new ArrayList<>();

        //체크리스트 추가 버튼 눌렀을 때 체크리스트액티비티 변환
        btn_checkList_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkBox0.setVisibility(View.INVISIBLE);
                checkBox1.setVisibility(View.INVISIBLE);
                checkBox2.setVisibility(View.INVISIBLE);
                checkBox3.setVisibility(View.INVISIBLE);
                checkBox4.setVisibility(View.INVISIBLE);
                checkBox5.setVisibility(View.INVISIBLE);
                checkBox6.setVisibility(View.INVISIBLE);
                checkBox7.setVisibility(View.INVISIBLE);
                checkBox8.setVisibility(View.INVISIBLE);
                checkBox9.setVisibility(View.INVISIBLE);

                //체크박스상태를 배열에 담아 전달
                boolean [] checkArray;
                checkArray = new boolean[mArrayList.size()];
                for (int i = 0; i < mArrayList.size(); i++) {
                    switch (i) {
                        case 0:
                            checkArray[i] = checkBox0.isChecked();
                            break;
                        case 1:
                            checkArray[i] = checkBox1.isChecked();
                            break;
                        case 2:
                            checkArray[i] = checkBox2.isChecked();
                            break;
                        case 3:
                            checkArray[i] = checkBox3.isChecked();
                            break;
                        case 4:
                            checkArray[i] = checkBox4.isChecked();
                            break;
                        case 5:
                            checkArray[i] = checkBox5.isChecked();
                            break;
                        case 6:
                            checkArray[i] = checkBox6.isChecked();
                            break;
                        case 7:
                            checkArray[i] = checkBox7.isChecked();
                            break;
                        case 8:
                            checkArray[i] = checkBox8.isChecked();
                            break;
                        case 9:
                            checkArray[i] = checkBox9.isChecked();
                            break;
                    }
                }

                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.per_fact.CheckListActivity.class);
                intent.putParcelableArrayListExtra("checklist", mArrayList);
                intent.putExtra("checkbox", checkArray);
                startActivityForResult(intent,0);
            }
        });


        return v;
    }//end of onCreateView

    //DATE를 얻는 함수
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    //현재 서울 날씨 출력
    private void CurrentCall() {


        String url = "http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=31594f3577ef731e2ce17c60a34f04a3";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {
                    //api로 받은 파일 jsonobject로 새로운 객체 선언
                    JSONObject jsonObject = new JSONObject(response);

                    //도시 키값 받기
                    String city = jsonObject.getString("name");

                    //날씨 키값 받기
                    JSONArray weatherJson = jsonObject.getJSONArray("weather");
                    JSONObject weatherObj = weatherJson.getJSONObject(0);
                    String weather = weatherObj.getString("description");
                    txt_weather.setText(weather);

                    //기온 키값 받기
                    JSONObject tempK = new JSONObject(jsonObject.getString("main"));

                    //기온 받고 켈빈 온도를 섭씨 온도로 변경
                    double tempDo = (Math.round((tempK.getDouble("temp")-273.15)*100)/100.0);
                    String tempDo_s = String.format("%.0f", tempDo);
                    current_tmp.setText(tempDo_s +  "°C");

                    //날씨 아이콘 받기
                    JSONArray weather_object = jsonObject.getJSONArray("weather");
                    JSONObject weather_icon= weather_object.getJSONObject(0);
                    String icon = weather_icon.getString("icon");
                    int resID = getResId("icon_"+icon, R.drawable.class);
                    ic_weather.setImageResource(resID);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        request.setShouldCache(false);
        requestQueue.add(request);

    }

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    //End of 현재서울날씨 출력

    //체크리스트엑티비티에서 받아와 화면에 출력
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 0) {
            mArrayList = new ArrayList<>();
            mArrayList = data.getParcelableArrayListExtra("checklist");
            boolean [] checkArray;
            checkArray = new boolean[mArrayList.size()];
            checkArray = data.getBooleanArrayExtra("checkbox");


            for (int i = 0; i < mArrayList.size(); i++) {
                switch (i) {
                    case 0:
                        checkBox0.setVisibility(View.VISIBLE);
                        checkBox0.setText(mArrayList.get(0).getId());
                        checkBox0.setChecked(checkArray[0]);
                        break;
                    case 1:
                        checkBox1.setVisibility(View.VISIBLE);
                        checkBox1.setText(mArrayList.get(1).getId());
                        checkBox1.setChecked(checkArray[1]);
                        break;
                    case 2:
                        checkBox2.setVisibility(View.VISIBLE);
                        checkBox2.setText(mArrayList.get(2).getId());
                        checkBox2.setChecked(checkArray[2]);
                        break;
                    case 3:
                        checkBox3.setVisibility(View.VISIBLE);
                        checkBox3.setText(mArrayList.get(3).getId());
                        checkBox3.setChecked(checkArray[3]);
                        break;
                    case 4:
                        checkBox4.setVisibility(View.VISIBLE);
                        checkBox4.setText(mArrayList.get(4).getId());
                        checkBox4.setChecked(checkArray[4]);
                        break;
                    case 5:
                        checkBox5.setVisibility(View.VISIBLE);
                        checkBox5.setText(mArrayList.get(5).getId());
                        checkBox5.setChecked(checkArray[5]);
                        break;
                    case 6:
                        checkBox6.setVisibility(View.VISIBLE);
                        checkBox6.setText(mArrayList.get(6).getId());
                        checkBox6.setChecked(checkArray[6]);
                        break;
                    case 7:
                        checkBox7.setVisibility(View.VISIBLE);
                        checkBox7.setText(mArrayList.get(7).getId());
                        checkBox7.setChecked(checkArray[7]);
                        break;
                    case 8:
                        checkBox8.setVisibility(View.VISIBLE);
                        checkBox8.setText(mArrayList.get(8).getId());
                        checkBox8.setChecked(checkArray[8]);
                        break;
                    case 9:
                        checkBox9.setVisibility(View.VISIBLE);
                        checkBox9.setText(mArrayList.get(9).getId());
                        checkBox9.setChecked(checkArray[9]);
                        break;
                }
            }





        }
    }

}