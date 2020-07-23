package com.example.ohmymoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;


public class BudgetActivity extends Activity {
    private Button button;
    private TableLayout tableLayout;
    private TextView textView_personal_price;
    private TextView textView_total_price;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        find_id();

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), NumberActivity.class);
                startActivity(i);
            }
        });

        HashMap<String, Integer> detail_price = temp_data();
        int num_of_traveling = 3;

        set_price_table(detail_price);
        textView_personal_price.setText(price_personal(detail_price, num_of_traveling));
        textView_total_price.setText(price_total(detail_price));

    }
    public void find_id(){
        button = (Button) findViewById(R.id.budget_button);
        textView_personal_price = (TextView) findViewById(R.id.text_personal_budget_values);
        textView_total_price = (TextView) findViewById(R.id.text_total_budget_values);
    }
    public HashMap<String, Integer> temp_data(){
        HashMap<String, Integer> temp_price = new HashMap<String, Integer>();
        temp_price.put("Pork belly", 24000);
        temp_price.put("Cafe End", 6000);
        temp_price.put("Flower Festival", 3000);

        return temp_price;
    }
    public void set_price_table(HashMap<String, Integer> detail_price){
        tableLayout = (TableLayout) findViewById(R.id.table_budget);

        for(String element : detail_price.keySet()){
            TableRow row = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams((layoutParams));

            TextView text_element = new TextView(this);
            TextView text_price = new TextView(this);
            text_element.setText(element);
            text_element.setText(String.valueOf(detail_price.get(element)));

            row.addView(text_element);
            row.addView(text_price);
        }
    }

    public String price_personal(HashMap<String, Integer> detail_price, int num_of_people_traveling){
        int price_personal = 0;
        for(Integer price: detail_price.values()){
            price_personal += price.intValue();
        }
        price_personal = (int)Math.ceil(price_personal / num_of_people_traveling);
        return String.valueOf(price_personal);
    }

    public String price_total(HashMap<String, Integer> detail_price){
        int price_personal = 0;
        for(Integer price: detail_price.values()){
            price_personal += price.intValue();
        }
        return String.valueOf(price_personal);
    }
}
