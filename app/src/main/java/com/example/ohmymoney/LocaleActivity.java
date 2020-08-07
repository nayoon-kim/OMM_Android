package com.example.ohmymoney;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.ChipGroup;

public class LocaleActivity extends AppCompatActivity {
    private Button btn_north, btn_south, btn_busan, btn_daegu, btn_ulsan;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localchoice);

        this.getViewObject();
        this.initListener();
    }

    private void getViewObject() {
       btn_north = (Button) findViewById(R.id.gyeongsang_north);
       btn_south = (Button) findViewById(R.id.gyeongsang_south);
       btn_busan = (Button) findViewById(R.id.gyeongsang_busan);
       btn_daegu = (Button) findViewById(R.id.gyeongsang_daegu);
       btn_ulsan = (Button) findViewById(R.id.gyeongsang_ulsan);
    }

    private void initListener() {

    }
}
