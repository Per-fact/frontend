package com.example.per_fact.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.per_fact.Api.ScrapService;
import com.example.per_fact.Data.PlaceData;
import com.example.per_fact.Data.ScrapData;
import com.example.per_fact.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.Holder> {
    private Context context;
    private List<PlaceData> list = new ArrayList<>();

    public MainViewAdapter(Context context, List<PlaceData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MainViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewAdapter.Holder holder, int position) {
        int itemposition = position;
        holder.name.setText(list.get(itemposition).name);
        holder.location.setText(list.get(itemposition).address);
        holder.category.setText(list.get(itemposition).categoryName);
        holder.tel.setText(list.get(itemposition).tel);

        //간격 설정
        ViewGroup.LayoutParams layoutParmas = holder.itemView.getLayoutParams();
        layoutParmas.height = 400;
        holder.itemView.requestLayout();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder{
        TextView location, name, tel, category;
        ImageView heart;

        //retrofit 객체 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.220.224:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ScrapService scrapService = retrofit.create(ScrapService.class);

        public Holder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            category = (TextView) itemView.findViewById(R.id.tv_category);
            location = (TextView) itemView.findViewById(R.id.tv_location);
            tel = (TextView) itemView.findViewById(R.id.tv_tel);
            heart = (ImageView) itemView.findViewById(R.id.iv_heart);

            String stringName = name.getText().toString();
            String stringAddr = location.getText().toString();
            String stringTel = tel.getText().toString();
            heart = (ImageView) itemView.findViewById(R.id.iv_heart);

            heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //스크랩 기능

                    ScrapData scrap = new ScrapData(0, stringName, stringAddr, stringTel);
                    scrapService.postData(scrap).enqueue(new Callback<ScrapData>() {
                        @Override
                        public void onResponse(Call<ScrapData> call, Response<ScrapData> response) {
                            Log.i("sooyeon", response.toString());
                            heart.setImageResource(R.drawable.full_heart);
                            Toast.makeText(view.getContext(), "스크랩 되었습니다.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ScrapData> call, Throwable t) {
                            Log.i("sooyeon", "실패");
                        }
                    });

                }
            });
        }
    }
}
