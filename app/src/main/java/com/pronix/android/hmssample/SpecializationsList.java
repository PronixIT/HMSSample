package com.pronix.android.hmssample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pronix.android.hmssample.common.CustomItemClickListener;
import com.pronix.android.hmssample.model.Specialization;
import com.pronix.android.hmssample.renderer.SpecializationAdapter;

import java.util.ArrayList;

/**
 * Created by ravi on 3/29/2018.
 */

public class SpecializationsList extends AppCompatActivity {
    ArrayAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Specialization> arrayList = new ArrayList<>();
    SpecializationAdapter adapter;
    String[] specializationNames = {"General Physician", "Anesthesiologists", "Cardiologists", "Dermatologists", "Endocrinologists",
            "Dentist", "Ophthalmologist", "Radiologists", "General Surgeon", "Gynecologist"};

    String[] specializationDescription = {"Fever, cough and cold", "Anesthesia", "Heart related problems", "Skin related problems", "Thyroid gland problems",
            "Teeth related problems", "Eye related problems", "X-Ray/Scanning", "Surgery", "Pregnancy, childbirth and other women health"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specializations);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView rl_Specliazation = (RecyclerView) findViewById(R.id.rv_SpecializationList);
        rl_Specliazation.setHasFixedSize(true);
        TextView mEmptyView = (TextView) findViewById(R.id.emptyView);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Specializations");

        getSpecilizationList();
        mLayoutManager = new LinearLayoutManager(this);
        rl_Specliazation.setLayoutManager(mLayoutManager);

        adapter = new SpecializationAdapter(arrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent in = new Intent(SpecializationsList.this, DoctorsLookupActivity.class);
                in.putExtra("SPECIALIZATION",arrayList.get(position).getSpecializationName());
                startActivity(in);
            }
        });
        rl_Specliazation.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                mAdapter.getFilter().filter(newText);
                filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void getSpecilizationList() {
        arrayList.clear();
        for (int i = 0; i < 9; i++) {
            Specialization specialization = new Specialization();
            specialization.setSpecializationId(i + "");
            specialization.setSpecializationName(specializationNames[i]);
            specialization.setDescription(specializationDescription[i]);
            arrayList.add(specialization);
        }
    }

    void filter(String text) {
        ArrayList<Specialization> temp = new ArrayList();
        for (Specialization d : arrayList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.getSpecializationName().toUpperCase().contains(text.toUpperCase())) {
                temp.add(d);
            }
        }
        //update recyclerview
        adapter.updateList(temp);
    }
}
