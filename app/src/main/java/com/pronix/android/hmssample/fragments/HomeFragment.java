package com.pronix.android.hmssample.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pronix.android.hmssample.BookDoctorsActivity;
import com.pronix.android.hmssample.R;
import com.pronix.android.hmssample.common.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private CardView book_appointment,seek_medical_advice,order_medicines,medical_queries;
    private TextView patientName;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        patientName = rootView.findViewById(R.id.patientName);
        book_appointment = rootView.findViewById(R.id.book_appointment);
        seek_medical_advice = rootView.findViewById(R.id.seek_medical_advice);
        order_medicines = rootView.findViewById(R.id.order_medicines);
        medical_queries = rootView.findViewById(R.id.medical_queries);
        patientName.setText(Constants.userDetails.name);

        book_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),BookDoctorsActivity.class));
            }
        });
        seek_medical_advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),HospitalDashboardActivity.class));
            }
        });

        return rootView;
    }

}
