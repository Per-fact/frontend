package com.example.per_fact.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.per_fact.Api.CheckListService;
import com.example.per_fact.Data.CheckListData;
import com.example.per_fact.R;
import com.example.per_fact.Repository.CheckListDictionary;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckListCustomAdapter extends RecyclerView.Adapter<CheckListCustomAdapter.CustomViewHolder> {

    private ArrayList<CheckListDictionary> mList;
    private Context mContext;

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected CheckBox mId;
        protected ImageButton mOption;

        public CustomViewHolder(View view) {
            super(view);

            this.mId = (CheckBox) view.findViewById(R.id.textview_recyclerview_id);
            this.mOption = (ImageButton) view.findViewById(R.id.btn_option);
        }
    }

    public CheckListCustomAdapter(Context context, ArrayList<CheckListDictionary> list) {
        mList = list;
        mContext = context;

    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.checklist_item, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, @SuppressLint("RecyclerView") int position) {

        //final??? ????????? ??????????????? ?????????????????? ????????? ?????????
        final CheckListDictionary item = mList.get(position);
        //?????? ??????????????? ???????????? null??? ?????????
        viewholder.mId.setOnCheckedChangeListener(null);
        //getter??? ?????? ???????????? ???????????? setter??? ?????? ??? ?????? ????????? ?????? ??????????????? set??????
        viewholder.mId.setChecked(item.isSelected());
        //??????????????? ???????????? ???????????? ????????? ??????
        viewholder.mId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.setSelected(compoundButton.isChecked());
                viewholder.mId.setSelected(compoundButton.isChecked());
            }
        });
        //
        //Retrofit ?????? ??????
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.220.224:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CheckListService checkListService = retrofit.create(CheckListService.class);

        viewholder.mId.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        viewholder.mId.setGravity(Gravity.CENTER);

        viewholder.mId.setText(mList.get(position).getId());

        viewholder.mOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup= new PopupMenu(mContext.getApplicationContext(), view,Gravity.CENTER);

                popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
                popup.setForceShowIcon(true);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu1:
                                Toast.makeText(view.getContext(), "??????", Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                                // ?????????????????? ???????????? ?????? edit_box.xml ????????? ???????????????.
                                View view_V = LayoutInflater.from(mContext)
                                        .inflate(R.layout.edit_box, null, false);
                                builder.setView(view_V);
                                final Button ButtonSubmit = (Button) view_V.findViewById(R.id.button_dialog_submit);
                                final EditText editTextID = (EditText) view_V.findViewById(R.id.edittext_dialog_id);



                                // 6. ?????? ?????? ???????????? ?????? ???????????? ???????????? ?????????????????? ???????????????.
                                editTextID.setText(mList.get(position).getId());
                                final AlertDialog dialog = builder.create();

                                // 7. ?????? ????????? ???????????? ?????? UI??? ???????????? ?????? ????????????
                                ButtonSubmit.setOnClickListener(new View.OnClickListener() {

                                    public void onClick(View v) {
                                        String strID = editTextID.getText().toString();
                                        Boolean checked = false;
                                        CheckListDictionary dict = new CheckListDictionary(strID, checked);

                                        // 8. ListArray??? ?????? ???????????? ????????????
                                        mList.set(position, dict);


                                        // 9. ??????????????? RecyclerView??? ??????????????? ?????????.
                                        notifyItemChanged(position);
                                        dialog.dismiss();

                                        //?????? API ??????(PUT)
                                        int id = 6; //id ?????? ??????????????? ????????????
                                        checkListService.putData(id).enqueue(new Callback<CheckListData>() {
                                            @Override
                                            public void onResponse(Call<CheckListData> call, Response<CheckListData> response) {
                                                if (response.isSuccessful()) {
                                                    Log.d("TEST PUT", "????????????");
                                                    Log.d("TEST PUT", response.toString());
                                                    Log.d("TEST PUT", response.body().toString());
                                                } else {
                                                    Log.d("TEST PUT", "??????");
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<CheckListData> call, Throwable t) {

                                            }
                                        });
                                    }
                                });
                                dialog.show();

                                break;
                            case R.id.menu2:
                                Toast.makeText(view.getContext(), "??????", Toast.LENGTH_SHORT).show(); //???????????? ??????
                                mList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, mList.size());
                                // API?????? DELETE
                                int id = 6; //id?????? ??????????????? ????????????
                                checkListService.putData(id).enqueue(new Callback<CheckListData>() {
                                    @Override
                                    public void onResponse(Call<CheckListData> call, Response<CheckListData> response) {
                                        if (response.isSuccessful()) {
                                            Log.d("TEST DELETE", "????????????");
                                            Log.d("TEST PUT", response.toString());
                                            Log.d("TEST DELETE", response.body().toString());
                                        } else {
                                            Log.d("TEST DELETE", "??????");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CheckListData> call, Throwable t) {

                                    }
                                });
                                break;
                        }
                        return false;
                    }
                });
                popup.show();

            }

        });

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
