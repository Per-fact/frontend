package com.example.per_fact.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.per_fact.Activity.MapsActivity;
import com.example.per_fact.Activity.ODsayBackend;
import com.example.per_fact.Activity.OfficeActivity;
import com.example.per_fact.Activity.ScheduleActivity;
import com.example.per_fact.Adapter.MainViewAdapter;
import com.example.per_fact.Adapter.RoadViewAdapter;
import com.example.per_fact.Data.Location;
import com.example.per_fact.Data.PlaceData;
import com.example.per_fact.Data.RoadData;
import com.example.per_fact.R;

import com.example.per_fact.Retrofit.RetrofitNet;
import com.odsay.odsayandroidsdk.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RoadFragment extends Fragment {
    ODsayBackend oDsayBackend;
    EditText startStation, endStation;
    Button switchStation, btnRoadFind, btnBus, btnSubway, btnTotal, btnSelect;
    ImageButton btnHome, btnBuilding;
    TextView tvHome, tvBuilding, total, tv_min, tv_busNumber, tv_startStation, tv_midStation, tv_endStation;
    //String APIKEY_ID = "ar7zysdloh";
    //String APIKEY = "MHEi%2FORnStzohsN6KcJhPoE4GgAiFnUu6gXHIQzb7F";
    RecyclerView recyclerView;
    ArrayList<RoadData> list2 = new ArrayList<>();
    private RoadViewAdapter adapter;

    private LocationManager locationManager;

    //출발지 경도, 위도
    public double longitude;
    public double latitude;

    //도착지 경도, 위도
    public double dst_longitude;
    public double dst_latitude;

    JSONArray path = new JSONArray();

    //path 변수
    int time;
    public RoadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_road, container, false);

        startStation = v.findViewById(R.id.et_startStation);
        endStation = v.findViewById(R.id.et_endStation);
        switchStation = v.findViewById(R.id.switchStation);
        btnRoadFind = v.findViewById(R.id.btnRoadFind);
        btnBus = v.findViewById(R.id.btnBus);
        btnSubway = v.findViewById(R.id.btnSubway);
        btnTotal = v.findViewById(R.id.btnTotal);
        btnHome = v.findViewById(R.id.btnHome);
        btnBuilding = v.findViewById(R.id.btnBuilding);
        btnSelect = v.findViewById(R.id.btnSelect);
        tvHome = v.findViewById(R.id.tvHome);
        tvBuilding = v.findViewById(R.id.tvBuilding);
        total = v.findViewById(R.id.total);
        tv_min = v.findViewById(R.id.tv_min);
        tv_busNumber = v.findViewById(R.id.tv_busNumber);
        tv_startStation = v.findViewById(R.id.tv_startStation);
        tv_midStation = v.findViewById(R.id.tv_midStation);
        tv_endStation = v.findViewById(R.id.tv_EndStation);

        //리싸이클러뷰 정의
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new RoadViewAdapter(getActivity(), list2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        clickListener();

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        }

        return v;
    }

    private void clickListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        btnBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OfficeActivity.class);
                startActivity(intent);
            }
        });


        btnRoadFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                oDsayBackend = ODsayBackend.getInstance(getActivity().getApplicationContext());

                List<String> list = new ArrayList<>();

                //ODsayAPI 호출
                path = oDsayBackend.requestRoute(longitude, latitude, dst_longitude, dst_latitude);

                String stringStartStation = startStation.getText().toString();
                String stringEndStation = endStation.getText().toString();

                //Log.d("ROUTE_PATH", path.toString());

                for (int i = 0; i < path.length(); i++) {
                    StringBuilder strpath = new StringBuilder();
                    strpath.append("\n");
                    //Log.d("SUBPATH", String.valueOf(i));
                    try {
                        //JSON 응답에서 subPath 로 array 추출, info 로 바디 추출
                        JSONArray subPath = path.getJSONObject(i).getJSONArray("subPath");
                        JSONObject info = path.getJSONObject(i).getJSONObject("info");

                        //확장 노드의 갯수만큼 for문 돌리면서 상세 정보 추출
                        for (int j = 0; j < subPath.length(); j++) {

                            JSONObject item = subPath.getJSONObject(j);
                            Log.d("SUBPATH", item.toString());

                            //만약 traffic type ==3 이면 지하철+버스
                            if (item.getInt("trafficType") == 3) {
                                strpath.append("걷기 : " + String.valueOf(item.getInt("distance")) + "m\n");
                            } else {
                                //버스와 지하철 모두 lane 배열에 담겨서 넘어오기 때문에 일단 lane array에 담아서 추출
                                JSONArray lanes = item.getJSONArray("lane");
                                if (item.getInt("trafficType") == 1) {
                                    strpath.append("지하철 : ");
                                    //lane Array에서 지하철 역 추출
                                    JSONArray stations = item.getJSONObject("passStopList").getJSONArray("stations");
                                    for (int k = 0; k < lanes.length(); k++) {
                                        //몇호선인지랑, 승하차역 추출하여 route 에 추가
                                        strpath.append(lanes.getJSONObject(k).getString("name") + " \n" + String.valueOf(item.getString("startName")) + " 탑승 \n" + String.valueOf(item.getString("endName")) + " 하차");
                                    }
                                } else {
                                    //trafficType이 2이면 버스로만 이동가능
                                    strpath.append("버스 : ");
                                    for (int l = 0; l < lanes.length(); l++) {
                                        //busNo 로 추출하여 몇번 버스 탑승하는지, 승차 및 하차 정류장 표시
                                        strpath.append(lanes.getJSONObject(l).getString("busNo") + " \n" + String.valueOf(item.getString("startName")) + " 탑승 \n" + String.valueOf(item.getString("endName")) + " 하차");
                                    }
                                }
                                strpath.append("\n");

                            }
                        }
                        time = path.getJSONObject(i).getJSONObject("info").getInt("totalTime");
                        int pay = path.getJSONObject(i).getJSONObject("info").getInt("payment");
                        strpath.append("소요시간 : " + String.valueOf(time) + "분\n");
                        strpath.append("금액 : " + String.valueOf(pay) + "원\n");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    list.add(strpath.toString());

                }

                for (int j = 0; j < list.size(); j++) {
                    Log.i("data", list.get(j));
                }


                if(stringStartStation != null && stringEndStation != null) {
                    Call<Location> call = RetrofitNet.getRetrofit().getSearchAddrService().searchAddressList(stringStartStation, "KakaoAK c6ca841b4aef918c0b7663d53e05fc5f");
                    call.enqueue(new Callback<Location>() {
                        @Override
                        public void onResponse(Call<Location> call, Response<Location> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    Log.i("출발지 longitude", response.body().documentsList.get(0).getX().toString());
                                    Log.i("출발지 latitude", response.body().documentsList.get(0).getY().toString());
                                    longitude = response.body().documentsList.get(0).getX();
                                    latitude = response.body().documentsList.get(0).getY();
                                    recyclerView.setVisibility(View.VISIBLE);
//                                    list2.add(new RoadData());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Location> call, Throwable t) {
                            Log.i("출발지", "실패");
                        }
                    });

                    Call<Location> call2 = RetrofitNet.getRetrofit().getSearchAddrService().searchAddressList(stringEndStation, "KakaoAK c6ca841b4aef918c0b7663d53e05fc5f");
                    call2.enqueue(new Callback<Location>() {
                        @Override
                        public void onResponse(Call<Location> call2, Response<Location> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    Log.i("목적지 x", response.body().documentsList.get(0).getX().toString());
                                    Log.i("목적지 y", response.body().documentsList.get(0).getY().toString());
                                    dst_longitude = response.body().documentsList.get(0).getX();
                                    dst_latitude = response.body().documentsList.get(0).getY();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Location> call, Throwable t) {
                                Log.i("목적지", "실패");
                        }
                    });
                }

                btnSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}