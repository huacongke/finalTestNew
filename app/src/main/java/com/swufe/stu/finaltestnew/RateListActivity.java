package com.swufe.stu.finaltestnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RateListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    Handler handler;
    private static final String TAG="RateList2Activity";
    ListView list1;
    MyAdapter myadapter;
    ArrayList<HashMap<String,String>> rlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);
        list1=findViewById(R.id.mylist1);
        list1.setOnItemClickListener(this);
        //准备数据
        ArrayList<HashMap<String,String>> listItems=new ArrayList<HashMap<String,String>>();
        for (int i=0;i<10;i++){
            HashMap<String,String> map=new HashMap<String,String>();

            map.put("Itemtitle","Rate:"+i);
            map.put("ItemDetaie","detail:"+i);
            listItems.add(map);
        }

        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter=new SimpleAdapter(this,
                listItems,//数据源
                R.layout.list_item_rate,//ListItem的XML布局实现
                new String[]{"Itemtitle","ItemDetaie"},
                new int[]{R.id.itemCname,R.id.itemCname}
        );
        list1.setAdapter(listItemAdapter);
//        //页面数据为空时，显示no_data文本框及其内容
//        list1.setEmptyView(findViewById(R.id.no));

        handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.i(TAG, "handleMessage: ");
                if(msg.what==9){
                    rlist=(ArrayList<HashMap<String,String>>)msg.obj;
//                    rlist=(ArrayList<Item>)msg.obj;
                    myadapter=new MyAdapter(RateListActivity.this,R.layout.list_item_rate,rlist);
                    list1.setAdapter(myadapter);
                }
            }
        };

        RateMapThread dt=new RateMapThread(handler);
//        RateItemThread dt=new RateItemThread(handler);

        Thread t=new Thread(dt);
        t.start();




    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Object itemAtposition=list1.getItemAtPosition(position);
//        Item item=(Item) itemAtposition;
        HashMap<String,String> map=(HashMap<String,String>)itemAtposition;
        String titleStr=map.get("cname");
        String detailStr=map.get("cval");
//        String titleStr=item.getCname();
//        String detailStr=item.getCval();
        Log.i(TAG, "onItemClick: titleStr="+titleStr);
        Log.i(TAG, "onItemClick: detailStr="+detailStr);

        TextView title=(TextView) view.findViewById(R.id.itemCname);
        TextView detail=(TextView) view.findViewById(R.id.itemCrate);
        String title2=String.valueOf(title.getText());
        String detail2=String.valueOf(detail.getText());

        Log.i(TAG, "onItemClick: title2="+title2);
        Log.i(TAG, "onItemClick: detail2="+detail2);
        //跳转到另一个页面
        Intent config=new Intent(this,CalRateActivity.class);
        config.putExtra("moneyType",titleStr);
        config.putExtra("rate",detailStr);

//        //跳转到另一个页面
//        Intent config=new Intent(this,calculateRate.class);
//        config.putExtra("moneyType",titleStr);
//        config.putExtra("rate",detailStr);

        //startActivity(config);
        startActivityForResult(config,1);//code填任意整数,相当于给不同窗口的编号
    }

    //对add图片进行监听
    public void clickAdd(View img){
        Intent config=new Intent(this,addConsum.class);

        //startActivity(config);
        startActivityForResult(config,1);//code填任意整数,相当于给不同窗口的编号


    }






}