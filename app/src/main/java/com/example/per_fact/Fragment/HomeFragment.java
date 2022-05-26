package com.example.per_fact.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.per_fact.Activity.MainActivity;
import com.example.per_fact.Calendar.EventDecorator;
import com.example.per_fact.Calendar.OneDayDecorator;
import com.example.per_fact.Calendar.SaturdayDecorator;
import com.example.per_fact.Calendar.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import com.example.per_fact.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.DayOfWeek;
import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment {
    MainActivity mainActivity;
    // 메인 액티비티 위에 올린다.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    // 메인 액티비티에서 내려온다.
    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }
    private ArrayList<com.example.per_fact.CheckListDictionary> mArrayList;
    private MaterialCalendarView calendarView;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();

    CheckBox checkBox0, checkBox1, checkBox2, checkBox3, checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9;
    ImageButton btn_checkList_add, btn_week_schedule_open, btn_week_schedule_close, today_schedule_btn_option,
            week_schedule_btn_option,week_schedule_btn_option2,week_schedule_btn_option3, today_schedule_btn_traffic;
    Button schedule_add_button;
    LinearLayout week_schedule_item, week_schedule_item2, week_schedule_item3;
    ConstraintLayout today_schedule;
    static RequestQueue requestQueue;
    ImageView ic_weather;
    TextView current_tmp, txt_weather, txt_clothing;

    //상단 날짜 출력바
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

    int a=0,b=0,c=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        btn_week_schedule_open =(ImageButton)v.findViewById(R.id.btn_week_schedule_open);
        btn_week_schedule_close =(ImageButton)v.findViewById(R.id.btn_week_schedule_close);
        today_schedule_btn_option = (ImageButton) v.findViewById(R.id.today_schedule_btn_option);
        btn_checkList_add = (ImageButton) v.findViewById(R.id.check_add_button);
        today_schedule_btn_traffic = (ImageButton) v.findViewById(R.id.today_schedule_btn_traffic);
        week_schedule_btn_option = (ImageButton) v.findViewById(R.id.week_schedule_btn_option);
        week_schedule_btn_option2 = (ImageButton) v.findViewById(R.id.week_schedule_btn_option2);
        week_schedule_btn_option3 = (ImageButton) v.findViewById(R.id.week_schedule_btn_option3);
        schedule_add_button = (Button) v.findViewById(R.id.schedule_add_button);
        week_schedule_item = (LinearLayout) v.findViewById(R.id.week_schedule_item);
        week_schedule_item2 =(LinearLayout)v.findViewById(R.id.week_schedule_item2);
        week_schedule_item3 =(LinearLayout)v.findViewById(R.id.week_schedule_item3);
        today_schedule =(ConstraintLayout)v.findViewById(R.id.today_schedule);
        ic_weather = (ImageView)v.findViewById(R.id.ic_weather);
        current_tmp = (TextView) v.findViewById(R.id.txt_current_tmp);
        txt_weather = (TextView) v.findViewById(R.id.txt_weather);
        txt_clothing = (TextView) v.findViewById(R.id.txt_clothing);
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
        calendarView = (MaterialCalendarView) v.findViewById(R.id.calendarView);

        //상단 텍스트바 날짜 출력
        TextView mTextView = v.findViewById(R.id.date_View);
        mTextView.setText(getTime());

        //현재 서울 날씨 출력
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }
        CurrentCall();

        //체크리스트 추가 버튼 눌렀을 때 체크리스트액티비티 변환
        mArrayList = new ArrayList<>();
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

                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.per_fact.CheckListActivity.class);
                intent.putParcelableArrayListExtra("checklist", mArrayList);
                startActivityForResult(intent,0);
            }
        });

        //달력//

        // 첫 시작 요일이 월요일이 되도록 설정
        calendarView.state()
                .edit()
                .setFirstDayOfWeek(DayOfWeek.of(Calendar.SATURDAY))
                .commit();

        // 월, 요일을 설정한값으로 보이게 설정 (MonthArrayTitleFormatter의 작동을 확인하려면 밑의 setTitleFormatter()를 지운다)
//        calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        calendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));
        //토요일 파랑, 일요일 빨강으로 보이게 + 현재날짜 표시
        calendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator());
        // 일자 선택 시 내가 정의한 드로어블이 적용되도록 한다
        calendarView.addDecorators(new DayDecorator(getActivity().getApplicationContext()));


//        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//                calendarView.addDecorator(EventDecorator(Collections.singleton(date)));
//            }
//        });

        //일정 추가 버튼 클릭시(오른쪽 하단)
        schedule_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.change_to_RoadFragment();
            }
        });

        //TODAY 스케줄
        //TODAY 스케줄 옵션 클릭시
        today_schedule_btn_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup= new PopupMenu(getActivity().getApplicationContext(), view, Gravity.CENTER);

                popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
                popup.setForceShowIcon(true);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu1:
                                Toast.makeText(view.getContext(), "수정", Toast.LENGTH_SHORT).show(); //토스트로 실험


                                break;
                            case R.id.menu2:
                                today_schedule.setVisibility(View.GONE);
                                Toast.makeText(view.getContext(), "삭제", Toast.LENGTH_SHORT).show(); //토스트로 실험

                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        //교통정보 확인하기 버튼
        today_schedule_btn_traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.change_to_RoadFragment();
            }
        });

        //WEEK 스케줄
        week_schedule_btn_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup= new PopupMenu(getActivity().getApplicationContext(), view, Gravity.CENTER);

                popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
                popup.setForceShowIcon(true);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu1:
                                Toast.makeText(view.getContext(), "수정", Toast.LENGTH_SHORT).show(); //토스트로 실험


                                break;
                            case R.id.menu2:
                                a =1;
                                week_schedule_item.setVisibility(View.GONE);
                                Toast.makeText(view.getContext(), "삭제", Toast.LENGTH_SHORT).show(); //토스트로 실험

                                break;
                        }
                        return false;
                    }
                });
                popup.show();

            }
        });
        week_schedule_btn_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup= new PopupMenu(getActivity().getApplicationContext(), view, Gravity.CENTER);

                popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
                popup.setForceShowIcon(true);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu1:
                                Toast.makeText(view.getContext(), "수정", Toast.LENGTH_SHORT).show(); //토스트로 실험


                                break;
                            case R.id.menu2:
                                b =1;
                                week_schedule_item2.setVisibility(View.GONE);
                                Toast.makeText(view.getContext(), "삭제", Toast.LENGTH_SHORT).show(); //토스트로 실험

                                break;
                        }
                        return false;
                    }
                });
                popup.show();

            }
        });
        week_schedule_btn_option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup= new PopupMenu(getActivity().getApplicationContext(), view, Gravity.CENTER);

                popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
                popup.setForceShowIcon(true);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu1:
                                Toast.makeText(view.getContext(), "수정", Toast.LENGTH_SHORT).show(); //토스트로 실험


                                break;
                            case R.id.menu2:
                                c = 1;
                                week_schedule_item3.setVisibility(View.GONE);
                                Toast.makeText(view.getContext(), "삭제", Toast.LENGTH_SHORT).show(); //토스트로 실험

                                break;
                        }
                        return false;
                    }
                });
                popup.show();

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
                    if (tempDo <= 4) {
                        txt_clothing.setText("⊙ 패딩\n⊙ 기모 옷\n⊙ 히트텍\n");

                    }
                    if (4 < tempDo && tempDo <= 16) {
                        txt_clothing.setText("⊙ 가디건\n⊙ 점퍼\n⊙ 트렌치코트\n");

                    }
                    if (16 < tempDo && tempDo <= 22) {
                        txt_clothing.setText("⊙ 맨투맨\n⊙ 긴팔 티\n⊙ 블라우스\n");

                    }
                    if (22 <= tempDo) {
                        txt_clothing.setText("⊙ 얇은 셔츠\n⊙ 린넨 옷\n⊙ 반팔\n");

                    }

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


            checkout_switch();
        }
    }

    private void checkout_switch() {
        for (int i = 0; i < mArrayList.size(); i++) {
            switch (i) {
                case 0:
                    checkBox0.setVisibility(View.VISIBLE);
                    checkBox0.setText(mArrayList.get(0).getId());
                    checkBox0.setChecked(mArrayList.get(0).isSelected());
                    break;
                case 1:
                    checkBox1.setVisibility(View.VISIBLE);
                    checkBox1.setText(mArrayList.get(1).getId());
                    checkBox1.setChecked(mArrayList.get(1).isSelected());
                    break;
                case 2:
                    checkBox2.setVisibility(View.VISIBLE);
                    checkBox2.setText(mArrayList.get(2).getId());
                    checkBox2.setChecked(mArrayList.get(2).isSelected());
                    break;
                case 3:
                    checkBox3.setVisibility(View.VISIBLE);
                    checkBox3.setText(mArrayList.get(3).getId());
                    checkBox3.setChecked(mArrayList.get(3).isSelected());
                    break;
                case 4:
                    checkBox4.setVisibility(View.VISIBLE);
                    checkBox4.setText(mArrayList.get(4).getId());
                    checkBox4.setChecked(mArrayList.get(4).isSelected());
                    break;
                case 5:
                    checkBox5.setVisibility(View.VISIBLE);
                    checkBox5.setText(mArrayList.get(5).getId());
                    checkBox5.setChecked(mArrayList.get(5).isSelected());
                    break;
                case 6:
                    checkBox6.setVisibility(View.VISIBLE);
                    checkBox6.setText(mArrayList.get(6).getId());
                    checkBox6.setChecked(mArrayList.get(6).isSelected());
                    break;
                case 7:
                    checkBox7.setVisibility(View.VISIBLE);
                    checkBox7.setText(mArrayList.get(7).getId());
                    checkBox7.setChecked(mArrayList.get(7).isSelected());
                    break;
                case 8:
                    checkBox8.setVisibility(View.VISIBLE);
                    checkBox8.setText(mArrayList.get(8).getId());
                    checkBox8.setChecked(mArrayList.get(8).isSelected());
                    break;
                case 9:
                    checkBox9.setVisibility(View.VISIBLE);
                    checkBox9.setText(mArrayList.get(9).getId());
                    checkBox9.setChecked(mArrayList.get(9).isSelected());
                    break;
            }
        }
    }

    /* 선택된 요일의 background를 설정하는 Decorator 클래스 */
    private static class DayDecorator implements DayViewDecorator {

        private final Drawable drawable;

        public DayDecorator(Context context) {
            drawable = ContextCompat.getDrawable(context, R.drawable.calendar_selector);
        }

        // true를 리턴 시 모든 요일에 내가 설정한 드로어블이 적용된다
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return true;
        }

        // 일자 선택 시 내가 정의한 드로어블이 적용되도록 한다
        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(drawable);
//            view.addSpan(new StyleSpan(Typeface.BOLD));   // 달력 안의 모든 숫자들이 볼드 처리됨
        }
    }





// https://59595959.tistory.com/4
//    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
//
//        String[] Time_Result;
//
//        ApiSimulator(String[] Time_Result) {
//            this.Time_Result = Time_Result;
//        }
//
//        @Override
//        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            Calendar calendar = Calendar.getInstance();
//            ArrayList<CalendarDay> dates = new ArrayList<>();
//
//
//            /*특정날짜 달력에 점표시해주는곳*/
//            /*월은 0이 1월 년,일은 그대로*/
//            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로 짜르고 string을 int 로 변환
//            for (int i = 0; i < Time_Result.length; i++) {
//
//
//                //이부분에서 day를 선언하면 초기 값에 오늘 날짜 데이터 들어간다.
//                //오늘 날짜 데이터를 첫 번째 인자로 넣기 때문에 데이터가 하나씩 밀려 마지막 데이터는 표시되지 않고, 오늘 날짜 데이터가 표시 됨.
//                // day선언 주석처리
//
//                //                CalendarDay day = CalendarDay.from(calendar);
//                //                Log.e("데이터 확인","day"+day);
//                String[] time = Time_Result[i].split(",");
//
//                int year = Integer.parseInt(time[0]);
//                int month = Integer.parseInt(time[1]);
//                int dayy = Integer.parseInt(time[2]);
//
//                //선언문을 아래와 같은 위치에 선언
//                //먼저 .set 으로 데이터를 설정한 다음 CalendarDay day = CalendarDay.from(calendar); 선언해주면 첫 번째 인자로 새로 정렬한 데이터를 넣어 줌.
//                calendar.set(year, month - 1, dayy);
//                CalendarDay day = CalendarDay.from(calendar);
//                dates.add(day);
//
//            }
//
//
//            return dates;
//        }
//
//        @Override
//        protected void onPostExecute(@NonNull List<Calendarday> calendarDays) {
//            super.onPostExecute(CalendarDays);
//
//            if (isFinishing()) {
//                return;
//            }
//
//            materialCalendarView.addDecorator(new EventDecorator(Color.RED, calendarDays));        }
//    }


}