package com.example.ohmymoney;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amar.library.ui.StickyScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BudgetActivity extends Activity {
    private Button button;
    private LinearLayout linearLayout_budget;
    private TextView textView_personal_price;
    private TextView textView_total_price;

    private StickyScrollView stickyScrollView;
    private RecyclerView recyclerView;
    private List<Budget> budgetList;

    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        find_id();
        settings();

        // 임시적으로 초기화한 HashMap을 scrollview에 보여준다.
        HashMap<String, Integer> detail_price = temp_data();

        set_price_table(detail_price);

        textView_total_price.setText(price_total(detail_price));

    }

    private void find_id(){
        button = (Button) findViewById(R.id.budget_button);

        textView_personal_price = (TextView) findViewById(R.id.text_personal_budget_values);
        textView_total_price = (TextView) findViewById(R.id.text_total_budget_values);

        recyclerView = (RecyclerView)findViewById(R.id.budget_recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        stickyScrollView = (StickyScrollView) findViewById(R.id.sticky_scrollview);

        budgetList = new ArrayList<Budget>();

        cardView = (CardView) findViewById(R.id.budget_cardview);
    }
    private void settings(){
        // button 클릭에 대한 설정
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), NumberActivity.class);
                startActivity(i);
            }
        });

        // stickyScrollView의 정확한 구현을 위한 설정
        recyclerView.setNestedScrollingEnabled(false);
        stickyScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!stickyScrollView.canScrollVertically(1))
                {//최하단일때
                    recyclerView.setNestedScrollingEnabled(true);
                }
                else if(!stickyScrollView.canScrollVertically(-1))
                {//최상단일때
                    recyclerView.setNestedScrollingEnabled(false);

                }
                else if(!recyclerView.canScrollVertically(-1)) {
                    recyclerView.setNestedScrollingEnabled(false);
                }
                else if(!recyclerView.canScrollVertically(1)) {
                    recyclerView.setNestedScrollingEnabled(true);
                }
                else {
                    //idle?
                    recyclerView.setNestedScrollingEnabled(false);

                }

                return false;
            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!stickyScrollView.canScrollVertically(1))
                {//최하단일때
                    recyclerView.setNestedScrollingEnabled(true);
                }
                else if(!stickyScrollView.canScrollVertically(-1))
                {//최상단일때
                    recyclerView.setNestedScrollingEnabled(false);

                }
                else if(!recyclerView.canScrollVertically(-1)) {
                    recyclerView.setNestedScrollingEnabled(false);
                }
                else if(!recyclerView.canScrollVertically(1)) {
                    recyclerView.setNestedScrollingEnabled(true);
                }
                else {
                    //idle?
                    recyclerView.setNestedScrollingEnabled(false);

                }

                return false;
            }
        });

    }
    private void set_price_table(HashMap<String, Integer> detail_price){

        for(String element : detail_price.keySet()){
              budgetList.add(new Budget(1, element, detail_price.get(element)));
        }
        Budget_Adapter adapter = new Budget_Adapter(this, budgetList);
        recyclerView.setAdapter(adapter);
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


    public HashMap<String, Integer> temp_data(){
        HashMap<String, Integer> temp_price = new HashMap<String, Integer>();
        temp_price.put("Pork belly", 24000);
        temp_price.put("Cafe End", 6000);
        temp_price.put("Flower Festival", 3000);
        temp_price.put("헤이! 춘천", 200000);
        temp_price.put("장미맨션", 80000);
        temp_price.put("남도 순대국", 50000);
        temp_price.put("Pork belly_belly", 24000);
        temp_price.put("Cafe End_End", 6000);
        temp_price.put("_Pork belly", 24000);
        temp_price.put("_Cafe End", 6000);
        temp_price.put("_Flower Festival", 3000);
        temp_price.put("_헤이! 춘천", 200000);
        temp_price.put("_장미맨션", 80000);
        temp_price.put("_남도 순대국", 50000);
        temp_price.put("_Pork belly_belly", 24000);
        temp_price.put("_Cafe End_End", 6000);
        return temp_price;
    }
}
