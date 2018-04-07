package com.pronix.android.hmssample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class BookDoctorsActivity extends AppCompatActivity {

    private LinearLayout doctor_lookup,hospital_lookup,dentists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doctors);
        doctor_lookup = findViewById(R.id.doctor_lookup);
        hospital_lookup = findViewById(R.id.hospital_lookup);
        dentists = findViewById(R.id.dentists);
        doctor_lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookDoctorsActivity.this,SpecializationsList.class));
            }
        });
        hospital_lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BookDoctorsActivity.this,HospitalsLookupActivity.class));
            }
        });
    }
}
