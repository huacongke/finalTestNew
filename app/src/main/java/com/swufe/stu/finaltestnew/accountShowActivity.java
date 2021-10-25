package com.swufe.stu.finaltestnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class accountShowActivity extends AppCompatActivity {
    private static final String TAG="MyAccShow";
    ListView list2;
    String type,cate,rmbb,time;
    String r_sum,r_get,r_pay;
    float r_sumf=0.0f,r_getf=0.0f,r_payf=0.0f;
    TextView allGet,allPay,allSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_show);
        list2=findViewById(R.id.mylist2);
        allGet=findViewById(R.id.showGet);
        allPay=findViewById(R.id.showPay);
        allSum=findViewById(R.id.showSum);



        Intent intent=getIntent();
//        type=intent.getStringExtra("accType");
//        cate=intent.getStringExtra("accRate");
//        rmbb=intent.getStringExtra("rmbNum");
//        time=intent.getStringExtra("accTime");


        //准备数据
        ArrayList<HashMap<String,String>> listItems=new ArrayList<HashMap<String,String>>();
        //从数据库中获得数据
        int i=1;
        String type="",temps="";
        float tempf=0.0f;
        DBManager dbManager=new DBManager(accountShowActivity.this);
        for(AccItem accItem:dbManager.listAll()){
            Log.i(TAG, "db: 从数据库中获得第"+i+"条数据");
            i++;
            HashMap<String,String> map=new HashMap<String,String>();
            map.put("accType",accItem.getCurType());
            map.put("accCate",accItem.getCurCate());
            map.put("rmbNum", accItem.getCurRmb());
            map.put("accTime", accItem.getCurTime());
            map.put("accNote", accItem.getCurNote());
            listItems.add(map);

            type=accItem.getCurType();
            temps=accItem.getCurRmb();
            tempf=Float.parseFloat(temps);
            Log.i(TAG, "onCreate: type="+type.getClass().toString());
            Log.i(TAG, "onCreate: "+temps);
            if(type.equals("收入")){
                r_getf+=tempf;
                r_sumf+=tempf;
            }else {
                r_payf+=tempf;
                r_sumf-=tempf;
            }
        }
        Log.i(TAG, "onCreate: 总共收入="+r_getf);
        Log.i(TAG, "onCreate: 总共支出="+r_payf);
        Log.i(TAG, "onCreate: 总共结余="+r_sumf);
//        HashMap<String,String> map=new HashMap<String,String>();
//        map.put("accType",type);
//        map.put("accRate",cate);
//        map.put("rmbNum", rmbb);
//        map.put("accTime", time);




        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter=new SimpleAdapter(this,
                listItems,//数据源
                R.layout.list_item_account,//ListItem的XML布局实现
                new String[]{"accType","accCate","rmbNum","accTime","accNote"},
                new int[]{R.id.showType,R.id.showCate,R.id.showNum,R.id.showTime,R.id.showNote}
        );
        list2.setAdapter(listItemAdapter);

        allSum.setText(String.valueOf(r_sumf));
        allPay.setText(String.valueOf(r_payf));
        allGet.setText(String.valueOf(r_getf));





    }

    public void clickToAdd(View pic){
        Intent config=new Intent(this,addConsum.class);
        startActivity(config);
        startActivityForResult(config,2);//code填任意整数,相当于给不同窗口的编号
    }
}