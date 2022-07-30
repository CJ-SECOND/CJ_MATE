package com.frontend.cj_app.dsla;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frontend.cj_app.R;
import com.frontend.cj_app.common.api.RetrofitAPI;
import com.frontend.cj_app.common.payload.Coury_Response;
import com.frontend.cj_app.user.login.permissionset;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tracking_date_select extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (ViewGroup) inflater.inflate(R.layout.traking_date_select, container, false);

        // 날짜별 통계 결과 조회를 위한 버튼 선언
        Button sunbutton = view.findViewById(R.id.sunbutton);
        Button monbutton = view.findViewById(R.id.monbutton);
        Button tusbutton = view.findViewById(R.id.tusbutton);
        Button wedbutton = view.findViewById(R.id.wedbutton);
        Button turbutton = view.findViewById(R.id.turbutton);
        Button fributton = view.findViewById(R.id.fributton);
        Button satbutton = view.findViewById(R.id.satbutton);

        // 차트 그리기
        getCouryResult(1);

        sunbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sunbutton.setSelected(true);
                monbutton.setSelected(false);

                Bundle result = new Bundle();
                result.putString("bundleKey", "result");
                getParentFragmentManager().setFragmentResult("requestKey", result);
            }
        });

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

        return view;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.18:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private void getCouryResult(int userSeq) {
        RetrofitAPI serviceApi = retrofit.create(RetrofitAPI.class);
        serviceApi.getCouryResult(userSeq).enqueue(new Callback<Coury_Response>() {
            @Override
            public void onResponse(Call<Coury_Response> call, Response<Coury_Response> response) {
                Coury_Response result = response.body();

                Bundle request = new Bundle();
                request.putSerializable("total", result.getTotal());
                request.putSerializable("complete", result.getComplete());
                request.putSerializable("wrong", result.getWrong());
                request.putSerializable("damage", result.getDamage());
                getParentFragmentManager().setFragmentResult("coury_result", request);

            }

            @Override
            public void onFailure(Call<Coury_Response> call, Throwable t) {

            }
        });
    }

}