package com.example.customcalendarview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customcalendarview.adapter.BaseRecycerAdapter;
import com.example.customcalendarview.adapter.ViewHolder;

import java.util.ArrayList;

public class TouchActivity extends AppCompatActivity {

    private BaseRecycerAdapter mWeekAdapter;
    private ArrayList<String> mDataTest = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        RecyclerView recyclerView = findViewById(R.id.recycler);

        for (int i = 0; i <100 ; i++) {
            mDataTest.add(String.valueOf(i));
        }

        mWeekAdapter = new BaseRecycerAdapter<String>(this, mDataTest, R.layout.item_week) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, String itemVO, int position) {
                TextView tv_day = viewHolder.findViewById(R.id.tv_week);
                tv_day.setText(itemVO);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mWeekAdapter);
    }
}