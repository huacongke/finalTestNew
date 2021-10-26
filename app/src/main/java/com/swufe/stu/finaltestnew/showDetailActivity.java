package com.swufe.stu.finaltestnew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class showDetailActivity extends AppCompatActivity{
    private static final String TAG = "TestDatePickerActivity";


//    ListView list3;
    private TextView mDatePicker,mDateShow;
    private TextView showDaySum,showDayGet,showDayPay;
    private  TextView showMonSum,showMonGet,showMonPay;
    private  TextView showYSum,showYGet,showYPay;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    ArrayList<HashMap<String,String>> listItems;
    String time,ex_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        mDatePicker = findViewById(R.id.textSearTime);
        mDateShow=findViewById(R.id.showSearTime);

        showDaySum=findViewById(R.id.textDaySum);
        showDayGet=findViewById(R.id.textDayGet);
        showDayPay=findViewById(R.id.textDayPay);

        showMonSum=findViewById(R.id.textMonSum);
        showMonGet=findViewById(R.id.textMinGet);
        showMonPay=findViewById(R.id.textMonPay);

        showYSum=findViewById(R.id.textYSum);
        showYGet=findViewById(R.id.textYGet);
        showYPay=findViewById(R.id.textYPay);


//        list3=findViewById(R.id.mylist3);

        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        showDetailActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.i(TAG, "onDateSet: date: " + year + "/" + month + "/" + dayOfMonth);
                int rm = month + 1;
                mDateShow.setText(year + "/" + rm + "/" + dayOfMonth);
            }
        };
    }

    public void timeClick(View pic){
        time=mDateShow.getText().toString();
        String month,year;
        ex_time=time.replaceAll("/", "-");
        String []split_time=ex_time.split("-");
        month=split_time[1];
        year=split_time[0];

        DBManager dbManager=new DBManager(showDetailActivity.this);
        listItems=new ArrayList<HashMap<String,String>>();
        String type="",temps="",times="";
        float tempf=0.0f;
        int i=1;
        float r_sumf=0.0f,r_getf=0.0f,r_payf=0.0f;
        float r_sumM=0.0f,r_getM=0.0f,r_payM=0.0f;
        float r_sumY=0.0f,r_getY=0.0f,r_payY=0.0f;
        //每一天
        for(AccItem accItem:dbManager.findByDate(ex_time)){
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
        showDaySum.setText("¥"+String.valueOf(r_sumf));
        showDayGet.setText("¥"+String.valueOf(r_getf));
        showDayPay.setText("¥"+"-"+r_payf);

        //每一月
        for(AccItem accItem:dbManager.listAll()){
            type=accItem.getCurType();
            temps=accItem.getCurRmb();
            times=accItem.getCurTime();
            String []timess=times.split("-");
            if(month.equals(timess[1])){
                tempf=Float.parseFloat(temps);
                if(type.equals("收入")){
                    r_sumM+=tempf;
                    r_getM+=tempf;
                }else {
                    r_sumM-=tempf;
                    r_payM+=tempf;
                }
            }
        }

        showMonSum.setText("¥"+String.valueOf(r_sumM));
        showMonGet.setText("¥"+String.valueOf(r_getM));
        showMonPay.setText("¥"+"-"+r_payM);

        //每一年
        for(AccItem accItem:dbManager.listAll()){
            type=accItem.getCurType();
            temps=accItem.getCurRmb();
            times=accItem.getCurTime();
            String []timess=times.split("-");
            if(year.equals(timess[0])){
                tempf=Float.parseFloat(temps);
                if(type.equals("收入")){
                    r_sumY+=tempf;
                    r_getY+=tempf;
                }else {
                    r_sumY-=tempf;
                    r_payY+=tempf;
                }
            }
        }

        showYSum.setText("¥"+String.valueOf(r_sumY));
        showYGet.setText("¥"+String.valueOf(r_getY));
        showYPay.setText("¥"+"-"+r_payY);






        //生成适配器的Item和动态数组对应的元素
//        SimpleAdapter listItemAdapter=new SimpleAdapter(this,
//                listItems,//数据源
//                R.layout.list_item_account,//ListItem的XML布局实现
//                new String[]{"accType","accCate","rmbNum","accTime","accNote"},
//                new int[]{R.id.showType,R.id.showCate,R.id.showNum,R.id.showTime,R.id.showNote}
//        );
//        list3.setAdapter(listItemAdapter);




    }

    public void clickDetoAdd(View pic){
        Intent config=new Intent(this,addConsum.class);
        startActivity(config);
        startActivityForResult(config,4);//code填任意整数,相当于给不同窗口的编号

    }

    public void clickDetoAna(View pic){
        Intent config=new Intent(this,AnalyActivity.class);
        startActivity(config);
        startActivityForResult(config,4);//code填任意整数,相当于给不同窗口的编号

    }

    public void clickToDe(View btn){
        Intent config=new Intent(this,dayDetailActivity.class);
        config.putExtra("ArrList", listItems);
        config.putExtra("Time", ex_time);

        startActivity(config);
        startActivityForResult(config,5);//code填任意整数,相当于给不同窗口的编号

    }
}