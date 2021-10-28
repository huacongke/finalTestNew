package com.swufe.stu.finaltestnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class AnalyActivity extends AppCompatActivity {
    private static final String TAG="AnaActivity";
    ListView list4,list5;//支出类目排行
    TextView text;
    List<AccItem> listData;
    String type,cate;
    int num;
    float rmb,rmb_temp;
    int numP,numG;
    float rmbP,rmbG;
    HashMap<String,Integer> mapPayNum,mapGetNum;
    HashMap<String,Float> mapPayMoney,mapGetMoney;
    ArrayList<HashMap<String,String>> listItemsP,listItemsG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analy);
        list4=findViewById(R.id.mylist4);
        list5=findViewById(R.id.mylist5);
        text=findViewById(R.id.textAnaMon);
        mapPayNum=new HashMap<String,Integer>();
        mapPayMoney=new HashMap<String,Float>();
        mapGetNum=new HashMap<String,Integer>();
        mapGetMoney=new HashMap<String,Float>();

        listItemsP=new ArrayList<HashMap<String,String>>();
        listItemsG=new ArrayList<HashMap<String,String>>();

        long time = System.currentTimeMillis();

        /*时间戳转换成IOS8601字符串*/
        Date date = new Date(time);
        TimeZone tz = TimeZone.getTimeZone("Asia/Beijing");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(tz);
        String nowAs = df.format(date);
        Log.i(TAG, "onCreate: 月份="+nowAs.split("-")[1]);
        text.setText(nowAs.split("-")[1]+"月的账目统计");

        DBManager dbManager=new DBManager(AnalyActivity.this);
        listData=dbManager.listAll();
        type="";cate="";
        num=0;
        //获得数据库中 记账的所有种类（支出）及每种记录了多少次
        for(AccItem item:listData){

            type=item.getCurType();
            if(type.equals("支出")){
                cate=item.getCurCate();
                rmb_temp=Float.parseFloat(item.getCurRmb()) ;
                if(mapPayNum.get(cate)!=null){
                    num=mapPayNum.get(cate);
                    mapPayNum.put(cate, 1+num);
                    rmb=mapPayMoney.get(cate);
                    mapPayMoney.put(cate, rmb_temp+rmb);
                }else {
                    mapPayNum.put(cate, 1);
                    mapPayMoney.put(cate, rmb_temp);
                }

                Log.i(TAG, "onCreate:向mapPayNum中添加："+type+cate+"==>"+String.valueOf(1+num));
                Log.i(TAG, "onCreate: 向mapPayMoney中添加："+type+cate+"==>"+String.valueOf(rmb_temp+rmb));

            }else {
                cate=item.getCurCate();
                rmb_temp=Float.parseFloat(item.getCurRmb());
                if(mapGetNum.get(cate)!=null){
                    num=mapGetNum.get(cate);
                    mapGetNum.put(cate, 1+num);
                    rmb=mapGetMoney.get(cate);
                    mapGetMoney.put(cate, rmb_temp+rmb);
                }else {
                    mapGetNum.put(cate, 1);
                    mapGetMoney.put(cate, rmb_temp);
                }
                Log.i(TAG, "onCreate:向mapGetNum中添加："+type+cate+"==>"+String.valueOf(1+num));
                Log.i(TAG, "onCreate: 向mapGetMoney中添加："+type+cate+"==>"+String.valueOf(rmb_temp+rmb));

            }

        }

        //遍历上述mapPayNum和mapPayMoney
        for(String key:mapPayNum.keySet()){
            HashMap<String,String> map=new HashMap<String,String>();
            numP=mapPayNum.get(key);
            Log.i(TAG, "onCreate:"+key+"支出"+numP+"笔");
            map.put("cateP",key);
//            Log.i(TAG, "onCreate: 从map中取值="+map.get("cateP"));
            map.put("cateNumP", "支出"+String.valueOf(numP)+"笔");
            rmbP=mapPayMoney.get(key);
            Log.i(TAG, "onCreate:"+key+"支出"+"¥"+rmbP);
            map.put("moneyP","-¥"+String.valueOf(rmbP));
            listItemsP.add(map);
        }

        //遍历上述mapGetNum和mapGetMoney
        for (String key:mapGetNum.keySet()){
            HashMap<String,String> map=new HashMap<String,String>();
            numG=mapGetNum.get(key);
            Log.i(TAG, "onCreate:"+key+"收入"+numG+"笔");
            map.put("cateG", key);
            map.put("cateNumG", "收入"+String.valueOf(numG)+"笔");
            rmbG=mapGetMoney.get(key);
            Log.i(TAG, "onCreate:"+key+"收入"+"¥"+rmbG);
            map.put("moneyG", "¥"+String.valueOf(rmbG));
            listItemsG.add(map);
        }


//        生成适配器的Item和动态数组对应的元素
        //支出
        SimpleAdapter listItemAdapter=new SimpleAdapter(this,
                listItemsP,//数据源
                R.layout.list_ana_item,//ListItem的XML布局实现
                new String[]{"cateP","moneyP","cateNumP"},
                new int[]{R.id.showCateP,R.id.showMoneyP,R.id.showNumP}
        );
        list4.setAdapter(listItemAdapter);

        //收入
        SimpleAdapter listItemAdapter1=new SimpleAdapter(this,
                listItemsG,//数据源
                R.layout.list_ana_item,//ListItem的XML布局实现
                new String[]{"cateG","moneyG","cateNumG"},
                new int[]{R.id.showCateP,R.id.showMoneyP,R.id.showNumP}
        );
        list5.setAdapter(listItemAdapter1);



    }
    public void clickAnaToAdd(View pic){
        Intent config=new Intent(this,addConsum.class);
        startActivity(config);
        startActivityForResult(config,4);//code填任意整数,相当于给不同窗口的编号

    }

    public void clickAnaToDe(View pic){
        Intent config=new Intent(this,showDetailActivity.class);
        startActivity(config);
        startActivityForResult(config,4);//code填任意整数,相当于给不同窗口的编号

    }

}