package com.triton.johnsonapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;
import com.triton.johnsonapp.R;
import com.triton.johnsonapp.adapter.SpinnerAdapter;
import com.triton.johnsonapp.utils.DatabaseHelper;

import java.util.ArrayList;

public class ViewDatas extends AppCompatActivity {

    private ArrayList<SpinnerModel> SpinnerModelArrayList;
    private DatabaseHelper dbhelper;
    private SpinnerAdapter SpinnerAdapter;
    private RecyclerView datasRV;

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_datas);

        SpinnerModelArrayList = new ArrayList<>();
        dbhelper = new DatabaseHelper(ViewDatas.this);

         SpinnerModelArrayList = dbhelper.readdatas();

        SpinnerAdapter = new SpinnerAdapter(SpinnerModelArrayList, context);
        datasRV = findViewById(R.id.RVDatas);

        datasRV.setAdapter(SpinnerAdapter);
    }
}
