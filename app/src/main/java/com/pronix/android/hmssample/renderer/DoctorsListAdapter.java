package com.pronix.android.hmssample.renderer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pronix.android.hmssample.R;
import com.pronix.android.hmssample.common.CustomItemClickListener;
import com.pronix.android.hmssample.model.Doctors;

import java.util.ArrayList;

/**
 * Created by ravi on 4/4/2018.
 */

public class DoctorsListAdapter extends RecyclerView.Adapter<DoctorsListAdapter.MyViewHolder> {

    ArrayList<Doctors> arrayList = new ArrayList<>();
    CustomItemClickListener listener;

    public DoctorsListAdapter(ArrayList<Doctors> list, CustomItemClickListener listener)
    {
        this.arrayList = list;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_Name, tv_Specialization, tv_Hospital;
        public MyViewHolder(final View view) {
            super(view);
            tv_Name = (TextView) view.findViewById(R.id.tv_ViewName);
            tv_Hospital = (TextView) view.findViewById(R.id.tv_Hospital);
            tv_Specialization = (TextView) view.findViewById(R.id.tv_ViewSpecialization);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(view,getAdapterPosition());
                }
            });
        }
    }
    @Override
    public DoctorsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctorslookupview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DoctorsListAdapter.MyViewHolder holder, int position) {
        holder.tv_Name.setText(arrayList.get(position).getDoctorName());
        holder.tv_Specialization.setText(arrayList.get(position).getDoctorProfile().getSpecialization());
        holder.tv_Hospital.setText(arrayList.get(position).getDoctorProfile().getHospitalName());

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
