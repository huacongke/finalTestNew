package com.swufe.stu.finaltestnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class dayDetailActivity extends AppCompatActivity {
    ListView list3;
    ArrayList<HashMap<String,String>> listItems;
    TextView showDtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);
        list3=findViewById(R.id.mylist3);
        showDtime=findViewById(R.id.showDay);
        Intent intent=getIntent();
//        listItems=new ArrayList<HashMap<String,String>>();
        listItems=(ArrayList<HashMap<String,String>>)intent.getSerializableExtra("ArrList");
        String show=intent.getStringExtra("Time");
        showDtime.setText(show);

        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter=new SimpleAdapter(this,
                listItems,//数据源
                R.layout.list_item_account,//ListItem的XML布局实现
                new String[]{"accType","accCate","rmbNum","accTime","accNote"},
                new int[]{R.id.showType,R.id.showCate,R.id.showNum,R.id.showTime,R.id.showNote}
        );
        list3.setAdapter(listItemAdapter);





    }
}