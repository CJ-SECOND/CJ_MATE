package com.frontend.cj_app.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frontend.cj_app.R;
import com.frontend.cj_app.common.api.RetrofitAPI;
import com.frontend.cj_app.common.model.AssingMentList;
import com.frontend.cj_app.common.payload.Assignment_Request;
import com.frontend.cj_app.common.payload.Confirm_Request;
import com.frontend.cj_app.common.payload.Confirm_Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskConfirm extends AppCompatActivity {

    String packageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_confirm);

        Bundle data = getIntent().getExtras();
        AssingMentList first = data.getParcelable("first");
        AssingMentList mid = data.getParcelable("mid");
        AssingMentList end = data.getParcelable("end");
        Assignment_Request request = data.getParcelable("request");

        TextView area1 = findViewById(R.id.area1);
        area1.setText(request.getArea().toString());
        TextView area2 = findViewById(R.id.area2);
        area2.setText(request.getArea().toString());
        TextView area3 = findViewById(R.id.area3);
        area3.setText(request.getArea().toString());

        TextView datetime1 = findViewById(R.id.datetime1);
        TextView datetime2 = findViewById(R.id.datetime2);
        TextView datetime3 = findViewById(R.id.datetime3);

        TextView timeState1 = findViewById(R.id.timeState1);
        timeState1.setText(request.getWorkTime().toString());
        TextView timeState2 = findViewById(R.id.timeState2);
        timeState2.setText(request.getWorkTime().toString());
        TextView timeState3 = findViewById(R.id.timeState3);
        timeState3.setText(request.getWorkTime().toString());

        TextView classWeight1 = findViewById(R.id.classWeight1);
        classWeight1.setText(first.getClassWeight().toString());
        TextView classWeight2 = findViewById(R.id.classWeight2);
        classWeight2.setText(mid.getClassWeight().toString());
        TextView classWeight3 = findViewById(R.id.classWeight3);
        classWeight3.setText(end.getClassWeight().toString());

        TextView classSize1 = findViewById(R.id.classSize1);
        classSize1.setText(first.getClassSize().toString());
        TextView classSize2 = findViewById(R.id.classSize2);
        classWeight2.setText(mid.getClassSize().toString());
        TextView classSize3 = findViewById(R.id.classSize3);
        classWeight3.setText(end.getClassSize().toString());

        TextView classTrffic1 = findViewById(R.id.classTrffic1);
        classTrffic1.setText(first.getClassTraffic().toString());
        TextView classTrffic2 = findViewById(R.id.classTrffic2);
        classTrffic2.setText(mid.getClassTraffic().toString());
        TextView classTrffic3 = findViewById(R.id.classTrffic3);
        classTrffic3.setText(end.getClassTraffic().toString());

        LinearLayout firstLaylout = findViewById(R.id.first);
        firstLaylout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packageName = first.getPackageName();
            }
        });

        LinearLayout midLaylout = findViewById(R.id.mid);
        midLaylout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packageName = mid.getPackageName();
            }
        });

        LinearLayout endLayout = findViewById(R.id.end);
        endLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packageName = end.getPackageName();
            }
        });

        Button btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // userSeq 는 임시로 1로 지정
                confirmCouryList(new Confirm_Request(packageName, 1));
                Intent intent = new Intent(getApplicationContext(), TaskList.class);
                intent.putExtra("area", request.getArea());
                intent.putExtra("datetime", request.getCount());
                intent.putExtra("workTime", request.getWorkTime());
                intent.putExtra("packageName", packageName);
                startActivity(intent);
            }
        });
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.254.2.21:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private void confirmCouryList(Confirm_Request request) {
        RetrofitAPI serviceApi = retrofit.create(RetrofitAPI.class);
        serviceApi.confirmCouryList(request).enqueue(new Callback<Confirm_Response>() {
            @Override
            public void onResponse(Call<Confirm_Response> call, Response<Confirm_Response> response) {
                Confirm_Response result = response.body();
                Log.i("result : ", String.valueOf(result));
            }
            @Override
            public void onFailure(Call<Confirm_Response> call, Throwable t) {
                Log.e("get user up Error", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.cjmate_ic2);
        builder.setContentTitle("업무를 확정해주세요.");
        builder.setContentText("마포 5월 2일 업무배치되었습니다.\n" +
                "앱에서 자세히 보고 확정해주시길 바랍니다.");

        builder.setAutoCancel(true); //탭 클릭하면 자동 제거
        //알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }
        //각 알림의 고유한 int값
        notificationManager.notify(1, builder.build());
    }
}