package com.swufe.stu.finaltestnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class CalRateActivity extends AppCompatActivity {
    private EditText input;
    private TextView show;
    private TextView title;
    private double rate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_rate);
        title=findViewById(R.id.textCname);
        input=findViewById(R.id.editTextRMB);
        show=findViewById(R.id.textRateRe);

        Intent intent=getIntent();
        String cname=intent.getStringExtra("moneyType");
        title.setText(cname);
        String cval=intent.getStringExtra("rate");
//        show.setText(cval);
        rate=Double.parseDouble(cval);
        //对输入框进行监听
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str=s.toString();
                if(str.length()>0){
                    String result=String.valueOf(Double.parseDouble(str)*rate);
                    show.setText(result);
                }else {
                    show.setText("");
                }

            }
        });


    }
}