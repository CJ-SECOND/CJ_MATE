package com.frontend.cj_app.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.frontend.cj_app.R;
import com.frontend.cj_app.common.adapter.ChildListAdapter;
import com.frontend.cj_app.common.adapter.ParentListAdapter;
import com.frontend.cj_app.common.model.TASK_CHILD_LIST;
import com.frontend.cj_app.common.model.TASK_PARENT_LIST;

import java.util.ArrayList;
import java.util.List;

public class TaskRequestMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_requset_main);

        String date[] = {"05.03 화", "05.04 수", "05.05 목"};
        ListView childListView = (ListView)findViewById(R.id.listView);
        List<TASK_CHILD_LIST> task_child_list = new ArrayList<TASK_CHILD_LIST>();

        ChildListAdapter childAdapter = new ChildListAdapter(getApplicationContext(), (ArrayList<TASK_CHILD_LIST>) task_child_list);

        for(int i=0; i<date.length; i++) {
            childListView.setAdapter(childAdapter);
            task_child_list.add(new TASK_CHILD_LIST("마포구", date[i] +" 07:00 ~ 17:00", "주간"));
            task_child_list.add(new TASK_CHILD_LIST("마포구", date[i] + " 00:00 ~ 07:00", "새벽"));
            task_child_list.add(new TASK_CHILD_LIST("마포구", date[i] + " 07:00 ~ 17:00", "잔여물량"));

            childListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id){

                    Log.i("area : ", childAdapter.getItem(position).getArea());
                    Intent intent = new Intent(getApplicationContext(), TaskRequest.class);
                    intent.putExtra("area", childAdapter.getItem(position).getArea());
                    intent.putExtra("dateTime", childAdapter.getItem(position).getDatetime());
                    intent.putExtra("timeState", childAdapter.getItem(position).getTimeState());
                    startActivity(intent);
                }
            });
        }
    }
}