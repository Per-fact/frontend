package com.example.per_fact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheckListCustomAdapter extends RecyclerView.Adapter<CheckListCustomAdapter.CustomViewHolder> {

    private ArrayList<com.example.per_fact.CheckListDictionary> mList;
    private Context mContext;

    public class CustomViewHolder extends RecyclerView.ViewHolder

    {

        protected CheckBox mId;
        protected ImageButton mOption;

        public CustomViewHolder(View view) {
            super(view);

            this.mId = (CheckBox) view.findViewById(R.id.textview_recyclerview_id);
            this.mOption = (ImageButton) view.findViewById(R.id.btn_option);

        }
    }

    public CheckListCustomAdapter(Context context, ArrayList<com.example.per_fact.CheckListDictionary> list) {
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
                                Toast.makeText(view.getContext(), "수정", Toast.LENGTH_SHORT).show(); //토스트로 실험

                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                                // 다이얼로그를 보여주기 위해 edit_box.xml 파일을 사용합니다.

                                View view_V = LayoutInflater.from(mContext)
                                        .inflate(R.layout.edit_box, null, false);
                                builder.setView(view_V);
                                final Button ButtonSubmit = (Button) view_V.findViewById(R.id.button_dialog_submit);
                                final EditText editTextID = (EditText) view_V.findViewById(R.id.edittext_dialog_id);



                                // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여줍니다.
                                editTextID.setText(mList.get(position).getId());



                                final AlertDialog dialog = builder.create();
                                ButtonSubmit.setOnClickListener(new View.OnClickListener() {


                                    // 7. 수정 버튼을 클릭하면 현재 UI에 입력되어 있는 내용으로

                                    public void onClick(View v) {
                                        String strID = editTextID.getText().toString();

                                        com.example.per_fact.CheckListDictionary dict = new com.example.per_fact.CheckListDictionary(strID);
                                        //여기


                                        // 8. ListArray에 있는 데이터를 변경하고
                                        mList.set(position, dict);


                                        // 9. 어댑터에서 RecyclerView에 반영하도록 합니다.

                                        notifyItemChanged(position);

                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();


                                break;
                            case R.id.menu2:
                                Toast.makeText(view.getContext(), "삭제", Toast.LENGTH_SHORT).show(); //토스트로 실험
                                mList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, mList.size());
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
