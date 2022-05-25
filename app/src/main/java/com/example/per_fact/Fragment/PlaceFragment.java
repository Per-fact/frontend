package com.example.per_fact.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.per_fact.Data.PlaceData;
import com.example.per_fact.Adapter.MainViewAdapter;
import com.example.per_fact.Data.Location;
import com.example.per_fact.R;
import com.example.per_fact.Retrofit.RetrofitNet;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceFragment extends Fragment {

    Button search_location;
    EditText et_keyword;
    String search;
    ArrayList<PlaceData> list = new ArrayList<>();
    RecyclerView recyclerView;
    ImageView heart;


    private MainViewAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_place, container, false);


        search_location = v.findViewById(R.id.search_location);
        et_keyword = v.findViewById(R.id.et_keyword);
        heart = v.findViewById(R.id.iv_heart);

        //리싸이클러뷰 정의
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new MainViewAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        listener();
        return v;
    }
    private void listener() {
        search_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = et_keyword.getText().toString();

                if(search != null) {
                    Call<Location> call = RetrofitNet.getRetrofit().getSearchAddrService().searchAddressList(search, "KakaoAK b7da65cd26d1be7fe973d194db579efd");
                    call.enqueue(new Callback<Location>() {
                        @Override
                        public void onResponse(Call<Location> call, Response<Location> response) {
                            if(response.isSuccessful()) {
                                if(response.body() != null) {
                                    for(int i = 0; i < response.body().documentsList.size(); i++) {
                                        recyclerView.setVisibility(View.VISIBLE);
                                        list.add(new PlaceData(response.body().documentsList.get(i).getPlace_name(), response.body().documentsList.get(i).getCategory_name(), response.body().documentsList.get(i).getRoad_address_name(), response.body().documentsList.get(i).getPhone()));
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Location> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}