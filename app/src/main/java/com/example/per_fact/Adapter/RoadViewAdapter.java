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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        RoadViewAdapter.Holder holder = new RoadViewAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoadViewAdapter.Holder holder, int position) {
        int itemposition = position;
        String bc = String.valueOf(list.get(itemposition).busCount);
        String sc = String.valueOf(list.get(itemposition).subwayCount);
        String sbc = String.valueOf(list.get(itemposition).subwayBusCount);
        String tt = String.valueOf(list.get(itemposition).totalTime);
        holder.btnBus.setText(bc);
        holder.btnSubway.setText(sc);
        holder.btnTotal.setText(sbc);
        holder.time.setText(tt);
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


        }
    }
}
