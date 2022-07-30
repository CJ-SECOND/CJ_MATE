package com.frontend.cj_app.dsla;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.frontend.cj_app.R;
import com.frontend.cj_app.common.api.RetrofitAPI;
import com.frontend.cj_app.common.payload.Coury_Response;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tracking extends AppCompatActivity {
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracking);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.254.0.140:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Button sunbutton = findViewById(R.id.sunbutton);
        Button monbutton = findViewById(R.id.monbutton);
        Button tusbutton = findViewById(R.id.tusbutton);
        Button wedbutton = findViewById(R.id.wedbutton);
        Button turbutton = findViewById(R.id.turbutton);
        Button fributton = findViewById(R.id.fributton);
        Button satbutton = findViewById(R.id.satbutton);

        sunbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sunbutton.setSelected(true);
                monbutton.setSelected(false);

                RetrofitAPI serviceApi = retrofit.create(RetrofitAPI.class);
                serviceApi.selectst(1).enqueue(new Callback<Coury_Response>() {
                    @Override
                    public void onResponse(Call<Coury_Response> call, Response<Coury_Response> response) {
                        Coury_Response result = response.body();

                        int total = result.getTotal_cnt();
                        // 1. 정시 배송률
                        float complete = result.getComplete_cnt();
                        float wrong = result.getWrong_cnt();
                        float damage = result.getDamage_cnt();
                    }

                    @Override
                    public void onFailure(Call<Coury_Response> call, Throwable t) {

                    }
                });
            }
        });
        pieChart = findViewById(R.id.piechart);
        setupPieChart();
        loadPieChartData(0.45f, 0.34f, 0.21f);

        monbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monbutton.setSelected(true);
                tusbutton.setSelected(false);

            }
        });

        tusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tusbutton.setSelected(true);
                wedbutton.setSelected(false);


            }
        });

        wedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedbutton.setSelected(true);
                turbutton.setSelected(false);


            }
        });

        turbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turbutton.setSelected(true);
                fributton.setSelected(false);


            }
        });


        fributton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fributton.setSelected(true);
                satbutton.setSelected(false);


            }
        });

        satbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                satbutton.setSelected(true);
                sunbutton.setSelected(false);
            }
        });
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData(float a, float b, float d) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(a, "정시배송"));
        entries.add(new PieEntry(b, "오배송"));
        entries.add(new PieEntry(d, "분실/파손"));

        final int[] MY_COLORS = {Color.rgb(51,153,255), Color.rgb(255,153,51), Color.rgb(255,51,51)};

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c: MY_COLORS) colors.add(c);

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}