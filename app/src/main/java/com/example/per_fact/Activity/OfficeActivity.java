package com.example.per_fact.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.per_fact.Api.BookMarkService;
import com.example.per_fact.Data.BookmarkCompany;
import com.example.per_fact.Data.BookmarkHome;
import com.example.per_fact.Data.Location;
import com.example.per_fact.Data.LoginData;
import com.example.per_fact.R;
import com.example.per_fact.Retrofit.RetrofitNet;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfficeActivity extends AppCompatActivity implements MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener{

    private static final String LOG_TAG = "MapsActivity";
    private EditText et_building;
    private ImageButton btnBack2, btnSearch2;
    private Button btnAdmin2;
    private TextView tv_complete2;
    MapView mapView;
    String search, placeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);
        mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        et_building = findViewById(R.id.et_building);
        btnBack2 = findViewById(R.id.btnBack2);
        btnSearch2 = findViewById(R.id.btnSearch2);
        btnAdmin2 = findViewById(R.id.btnAdmin2);
        tv_complete2 = findViewById(R.id.tv_complete2);

        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = et_building.getText().toString();

                if (search != null) {
                    Call<Location> call = RetrofitNet.getRetrofit().getSearchAddrService().searchAddressList(search, "KakaoAK c6ca841b4aef918c0b7663d53e05fc5f");
                    call.enqueue(new Callback<Location>() { //검색 조건
                        @Override
                        public void onResponse(Call<Location> call, Response<Location> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    for (int i = 0; i < response.body().documentsList.size(); i++) {
                                        //마커 찍기
                                        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(response.body().documentsList.get(i).getY(), response.body().documentsList.get(i).getX());
                                        MapPOIItem marker = new MapPOIItem();

                                        placeName = response.body().documentsList.get(0).getPlace_name();
                                        marker.setItemName(response.body().documentsList.get(i).getPlace_name());
                                        marker.setTag(0);
                                        marker.setMapPoint(MARKER_POINT);
                                        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
                                        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                                        mapView.addPOIItem(marker);
                                        // 줌 레벨 변경
                                        mapView.setZoomLevel(7, true);
                                        et_building.setText(response.body().documentsList.get(i).getPlace_name());
                                        tv_complete2.setVisibility(View.VISIBLE);
                                        btnAdmin2.setVisibility(View.VISIBLE);
                                        btnAdmin2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                //retrofit 객체 생성
                                                Retrofit retrofit = new Retrofit.Builder()
                                                        .baseUrl("http://34.64.220.224:8080/")
                                                        .addConverterFactory(GsonConverterFactory.create())
                                                        .build();

                                                BookMarkService bookMarkService = retrofit.create(BookMarkService.class);
                                                double x = response.body().documentsList.get(0).getX();
                                                double y = response.body().documentsList.get(0).getY();


                                                BookmarkCompany bookmarkCompany = new BookmarkCompany(1,y,x,placeName);

                                                bookMarkService.addCompany(bookmarkCompany).enqueue(new Callback<BookmarkCompany>() {
                                                    @Override
                                                    public void onResponse(Call<BookmarkCompany> call, Response<BookmarkCompany> response) {
                                                        if(response.isSuccessful()) {
                                                            if(response.body() != null) {
                                                                Toast.makeText(OfficeActivity.this, "회사 등록이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                                                                onBackPressed();
                                                            }
                                                        }else {
                                                            Log.i("TEST", "error");
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<BookmarkCompany> call, Throwable t) {
                                                        Log.i("TEST", "실패");
                                                    }

                                                });



                                            }
                                        });

                                        mapView.setPOIItemEventListener(new MapView.POIItemEventListener() {
                                            @Override
                                            public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

                                            }

                                            @Override
                                            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
                                            }

                                            @Override
                                            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
                                            }

                                            @Override
                                            public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
                                            }
                                        });
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Location> call, Throwable t) {
                            Toast.makeText(OfficeActivity.this, search, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {

    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {

    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {

    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }
}
