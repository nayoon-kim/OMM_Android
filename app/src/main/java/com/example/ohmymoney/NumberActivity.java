package com.example.ohmymoney;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberActivity extends Activity {
    private Button button;
    private ImageButton adult_plus_button;
    private ImageButton adult_minus_button;
    private ImageButton child_plus_button;
    private ImageButton child_minus_button;

    private TextView adult_number_text_view;
    private TextView child_number_text_view;

    private int adult_number;
    private int child_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        button = (Button) findViewById(R.id.number_button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(i);
            }
        });

        adult_plus_button = findViewById(R.id.adults_plus);
        adult_minus_button = findViewById(R.id.adults_minus);
        child_plus_button = findViewById(R.id.child_plus);
        child_minus_button = findViewById(R.id.child_minus);
        adult_number_text_view = findViewById(R.id.adults_number);
        child_number_text_view = findViewById(R.id.child_number);

        adult_number = 0;
        child_number = 0;


        adult_plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adult_number+=1;
                adult_number_text_view.setText(String.valueOf(adult_number));
            }
        });
        adult_minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adult_number == 0)
                    adult_number = 0;
                else
                    adult_number-=1;

                adult_number_text_view.setText(String.valueOf(adult_number));

            }
        });

        child_plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child_number+=1;
                child_number_text_view.setText(String.valueOf(child_number));
            }
        });
        child_minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (child_number == 0)
                    child_number = 0;
                else
                    child_number-=1;
                child_number_text_view.setText(String.valueOf(child_number));
            }
        });

    }

}
