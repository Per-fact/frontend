package com.example.per_fact.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.per_fact.Data.PlaceData;
import com.example.per_fact.Data.RoadData;
import com.example.per_fact.R;

import java.util.ArrayList;
import java.util.List;

public class RoadViewAdapter extends RecyclerView.Adapter<RoadViewAdapter.Holder> {
    private Context context;
    private List<RoadData> list = new ArrayList<>();

    public RoadViewAdapter(Context context, List<RoadData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RoadViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview, parent, false);
        RoadViewAdapter.Holder holder = new RoadViewAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoadViewAdapter.Holder holder, int position) {
        int itemposition = position;
        holder.time.setText(list.get(itemposition).totalTime);
        holder.btnBus.setText(list.get(itemposition).busCount);
        holder.btnSubway.setText(list.get(itemposition).subwayCount);
        holder.btnTotal.setText(list.get(itemposition).subwayBusCount);
        holder.startStation.setText(list.get(itemposition).firstStartStation);
        holder.endStation.setText(list.get(itemposition).lastEndStation);

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
        Button btnBus, btnSubway, btnTotal, btnSelect;
        TextView time, startStation, endStation;

        public Holder(@NonNull View itemView) {
            super(itemView);
            btnBus = itemView.findViewById(R.id.btnBus);
            btnSubway = itemView.findViewById(R.id.btnSubway);
            btnTotal = itemView.findViewById(R.id.btnTotal);
            btnSelect = itemView.findViewById(R.id.btnSelect);
            time = itemView.findViewById(R.id.tv_min);
            startStation = itemView.findViewById(R.id.tv_startStation);
            endStation = itemView.findViewById(R.id.tv_EndStation);

            btnSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "경로가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
