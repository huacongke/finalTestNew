package com.swufe.stu.finaltestnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class addConsum extends AppCompatActivity {
    private static final String TAG="MySpinner";
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    Spinner spinnerType,spinnerCate;
    TextView numText,noteText;

    List<AccItem> retList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consum);

        spinner = (Spinner) findViewById(R.id.sp_type);
        //数据
        data_list = new ArrayList<String>();
        data_list.add("收入");
        data_list.add("支出");

        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);

//        spinnerItems=(Spinner) findViewById(R.id.sp_type);
//        String str= (String) spinnerItems.getItemAtPosition(spinnerItems.getSelectedItemPosition());
//        Log.i(TAG, "onCreate: str="+str);
    }

    public void clickAdd(View pic){

//        Intent config=new Intent(this,addConsum.class);
//
//        //startActivity(config);
//        startActivityForResult(config,1);//code填任意整数,相当于给不同窗口的编号
        //获取记账增加的条目
        spinnerType=(Spinner) findViewById(R.id.sp_type);
        spinnerCate=(Spinner)findViewById(R.id.sp_cate);
        numText=findViewById(R.id.editTextMon);
        noteText=findViewById(R.id.editTextBei);
        String strType= (String) spinnerType.getItemAtPosition(spinnerType.getSelectedItemPosition());
        String strCate=(String)spinnerCate.getItemAtPosition(spinnerCate.getSelectedItemPosition());
        String rmb=numText.getText().toString();
        String note=noteText.getText().toString();

        /*获取当前系统时间*/
        long time = System.currentTimeMillis();

        /*时间戳转换成IOS8601字符串*/
        Date date = new Date(time);
        TimeZone tz = TimeZone.getTimeZone("Asia/Beijing");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(tz);
        String nowAsIOS = df.format(date);
        Toast.makeText(this, "于"+nowAsIOS+",您添加了类型="+strType+"，分类="+strCate+"，金额="+rmb+"的一笔记账", Toast.LENGTH_SHORT).show();


        Log.i(TAG, "clickAdd: 类型="+strType);
        Log.i(TAG, "clickAdd: 分类="+strCate);
        Log.i(TAG, "clickAdd: 金额="+rmb);
        Log.i(TAG, "clickAdd: 备注="+note);
        Log.i(TAG, "clickAdd: 本次记账的时间="+nowAsIOS);

        retList=new ArrayList<AccItem>();
        AccItem accItem=new AccItem(strType,strCate,nowAsIOS,rmb,note);
        retList.add(accItem);

        DBManager dbManager=new DBManager(addConsum.this);
//        dbManager.deleteAll();
        dbManager.add(accItem);
        Log.i(TAG, "db: 添加新的记录");

        Intent config=new Intent(this,accountShowActivity.class);
        config.putExtra("accType",strType);
        config.putExtra("accRate",strCate);
        config.putExtra("rmbNum", "¥"+rmb);
        config.putExtra("accTime", nowAsIOS);

        startActivity(config);
        startActivityForResult(config,1);//code填任意整数,相当于给不同窗口的编号





        

    }

}