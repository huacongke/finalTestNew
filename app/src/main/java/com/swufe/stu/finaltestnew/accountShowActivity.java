package com.swufe.stu.finaltestnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class accountShowActivity extends AppCompatActivity {
    ListView list2;
    String type,cate,rmbb,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_show);
        list2=findViewById(R.id.mylist2);

        Intent intent=getIntent();
        type=intent.getStringExtra("accType");
        cate=intent.getStringExtra("accRate");
        rmbb=intent.getStringExtra("rmbNum");
        time=intent.getStringExtra("accTime");


        //准备数据
        ArrayList<HashMap<String,String>> listItems=new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("accType",type);
        map.put("accRate",cate);
        map.put("rmbNum", rmbb);
        map.put("accTime", time);

        listItems.add(map);


        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter=new SimpleAdapter(this,
                listItems,//数据源
                R.layout.list_item_account,//ListItem的XML布局实现
                new String[]{"accType","accRate","rmbNum","accTime"},
                new int[]{R.id.showType,R.id.showCate,R.id.showNum,R.id.showTime}
        );
        list2.setAdapter(listItemAdapter);


    }
}