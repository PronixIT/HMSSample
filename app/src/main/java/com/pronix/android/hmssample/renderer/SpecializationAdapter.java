package com.pronix.android.hmssample.renderer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pronix.android.hmssample.R;
import com.pronix.android.hmssample.common.CustomItemClickListener;
import com.pronix.android.hmssample.model.Specialization;

import java.util.ArrayList;

/**
 * Created by ravi on 3/30/2018.
 */

public class SpecializationAdapter extends RecyclerView.Adapter<SpecializationAdapter.MyViewHolder> {
    ArrayList<Specialization> list;
    CustomItemClickListener listener;

    public SpecializationAdapter(ArrayList<Specialization> list, CustomItemClickListener listener)
    {
        this.list = list;
        this.listener = listener;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView, mtextView1;
        public MyViewHolder(final View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.textView);
            mtextView1 = (TextView) view.findViewById(R.id.textView1);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(view,getAdapterPosition());
                }
            });
        }
    }

    @Override
    public SpecializationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptor_cardview, parent, false);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onItemClick(view, get);
//
//            }
//        });

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SpecializationAdapter.MyViewHolder holder, int position) {
        holder.mTextView.setText(list.get(position).getSpecializationName());
        holder.mtextView1.setText(list.get(position).getDescription());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(ArrayList<Specialization> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
