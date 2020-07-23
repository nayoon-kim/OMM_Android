package com.example.ohmymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnScheduling, btnBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getViewObject();
        this.initListener();
    }

    public void getViewObject() {
        btnScheduling = (Button)findViewById(R.id.scheduling);
        btnBudget = (Button)findViewById(R.id.budgeting);
    }

    public void initListener() {
        btnScheduling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlanActivity1.class);
                startActivity(intent);
            }
        });

        btnBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
