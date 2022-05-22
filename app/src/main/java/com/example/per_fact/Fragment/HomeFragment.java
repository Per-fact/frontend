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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private ArrayList<CheckListDictionary> mArrayList;

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
        checkBox1 = (CheckBox) v.findViewById(R.id.checkBox0);
        checkBox2 = (CheckBox) v.findViewById(R.id.checkBox1);
        checkBox3 = (CheckBox) v.findViewById(R.id.checkBox2);
        checkBox4 = (CheckBox) v.findViewById(R.id.checkBox3);
        checkBox5 = (CheckBox) v.findViewById(R.id.checkBox4);
        checkBox6 = (CheckBox) v.findViewById(R.id.checkBox5);
        checkBox7 = (CheckBox) v.findViewById(R.id.checkBox6);
        checkBox8 = (CheckBox) v.findViewById(R.id.checkBox7);
        checkBox9 = (CheckBox) v.findViewById(R.id.checkBox8);


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

                Intent intent = new Intent(getActivity().getApplicationContext(), CheckListActivity.class);
                intent.putParcelableArrayListExtra("checklist", mArrayList);
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

//                    cityView.setText(city);


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

            for (int i = 0; i < mArrayList.size(); i++) {
                switch (i) {
                    case 0:
                        checkBox0.setVisibility(View.VISIBLE);
                        checkBox0.setText(mArrayList.get(0).getId());
                        break;
                    case 1:
                        checkBox1.setVisibility(View.VISIBLE);
                        checkBox1.setText(mArrayList.get(1).getId());
                        break;
                    case 2:
                        checkBox2.setVisibility(View.VISIBLE);
                        checkBox2.setText(mArrayList.get(2).getId());
                        break;
                    case 3:
                        checkBox3.setVisibility(View.VISIBLE);
                        checkBox3.setText(mArrayList.get(3).getId());
                        break;
                    case 4:
                        checkBox4.setVisibility(View.VISIBLE);
                        checkBox4.setText(mArrayList.get(4).getId());
                        break;
                    case 5:
                        checkBox5.setVisibility(View.VISIBLE);
                        checkBox5.setText(mArrayList.get(5).getId());
                        break;
                    case 6:
                        checkBox6.setVisibility(View.VISIBLE);
                        checkBox6.setText(mArrayList.get(6).getId());
                        break;
                    case 7:
                        checkBox7.setVisibility(View.VISIBLE);
                        checkBox7.setText(mArrayList.get(7).getId());
                        break;
                    case 8:
                        checkBox8.setVisibility(View.VISIBLE);
                        checkBox8.setText(mArrayList.get(8).getId());
                        break;
                    case 9:
                        checkBox9.setVisibility(View.VISIBLE);
                        checkBox9.setText(mArrayList.get(9).getId());
                        break;

                }


            }






        }
    }

}