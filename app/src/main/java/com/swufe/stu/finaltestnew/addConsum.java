package com.swufe.stu.finaltestnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class addConsum extends AppCompatActivity {
    private static final String TAG="MySpinner";
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    Spinner spinnerType,spinnerCate;
    TextView numText,noteText;

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

        Log.i(TAG, "onCreate: 类型="+strType);
        Log.i(TAG, "clickAdd: 分类="+strCate);
        Log.i(TAG, "clickAdd: 金额="+rmb);
        Log.i(TAG, "clickAdd: 备注="+note);
        

    }

}