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

import com.example.per_fact.Data.PlaceData;
import com.example.per_fact.R;

import java.util.ArrayList;
import java.util.List;

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
        public Holder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            category = (TextView) itemView.findViewById(R.id.tv_category);
            location = (TextView) itemView.findViewById(R.id.tv_location);
            tel = (TextView) itemView.findViewById(R.id.tv_tel);
            heart = (ImageView) itemView.findViewById(R.id.iv_heart);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView heart;
                    heart = view.findViewById(R.id.iv_heart);
                    //스크랩 기능
                    int [] ImageId = {R.drawable.heart, R.drawable.full_heart};
                    int j = 1;
                    heart.setImageResource(ImageId[j]);
                    Log.i("sooyeon", "클릭됨");
                }
            });
        }
    }
}
