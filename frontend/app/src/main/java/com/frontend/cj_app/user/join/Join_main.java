package com.frontend.cj_app.user.join;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.frontend.cj_app.R;

import java.util.regex.Pattern;

public class Join_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_main);
        EditText et_join_name = findViewById(R.id.et_join_name);
        EditText et_join_id = findViewById(R.id.et_join_id);
        EditText et_join_pw = findViewById(R.id.et_join_pw);
        EditText et_join_phonenum = findViewById(R.id.et_join_phonenum);
        EditText et_join_confirmpw = findViewById(R.id.et_join_confirmpw);

        Button button = findViewById(R.id.btn_next1);

        CheckBox checkBox1 = findViewById(R.id.checkbox1);
        CheckBox checkBox2 = findViewById(R.id.checkbox2);
        CheckBox checkBox3 = findViewById(R.id.checkbox3);
        CheckBox checkBox4 = findViewById(R.id.checkbox4);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Join_account.class);
                String user_name = et_join_name.getText().toString();
                String user_id = et_join_id.getText().toString();
                String user_pw = et_join_pw.getText().toString();
                String user_phonenum = et_join_phonenum.getText().toString();
                //이름 유효성 검사
                if (user_name.length() == 0) {
                    Toast.makeText(Join_main.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                //아이디 유효성 검사
                if (user_id.length() == 0) {
                    Toast.makeText(Join_main.this, "ID를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                //비밀번호 유효성 검사
                if (user_pw.length() == 0) {
                    Toast.makeText(Join_main.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!user_pw.equals(et_join_confirmpw.getText().toString())) {
                    Toast.makeText(Join_main.this, "비밀번호가 일치하지않습니다.", Toast.LENGTH_SHORT).show();
                    et_join_pw.setText("");
                    et_join_confirmpw.setText("");
                    et_join_pw.requestFocus();
                    return;
                }
                //전화번호 유효성 검사
                if (!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{4})\\d{4}$", user_phonenum)) {
                    Toast.makeText(Join_main.this, "올바른 핸드폰 번호가 아닙니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("USER_NAME", user_name);
                intent.putExtra("USER_ID", user_id);
                intent.putExtra("USER_PW", user_pw);
                intent.putExtra("USER_PHONENUM", user_phonenum);

                // 체크박스가 체크된경우
                if (checkBox1.isChecked()) {
                    startActivity(intent);
                } else Toast.makeText(Join_main.this, "약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_sendcode1 = findViewById(R.id.btn_sendcode1);
        btn_sendcode1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });
    }

    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.cjmate_ic2);
        builder.setContentTitle("CJ MATE 본인인증");
        builder.setContentText("본인인증 [12345] 를 입력해주세요.");

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
