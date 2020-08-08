package com.example.ohmymoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import android.widget.SearchView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class SearchActivity extends Activity {
    private RelativeLayout relativeLayout;
    private SearchView searchView;
    private ListView listView;
    private ArrayList<String> list;
    private ArrayAdapter adapter;
    private Button button;

    private ArrayList<String> genres;
    private ChipGroup chipGroup;

    private ArrayList<String> checked_genres;
    private int chipNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        relativeLayout = findViewById(R.id.search_relativeLayout);
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);
        chipGroup = findViewById(R.id.search_chipGroup);
        button = findViewById(R.id.search_button);

        genres = new ArrayList<String>();
        list = new ArrayList<String>();
        checked_genres = new ArrayList<String>();
        chipNumber = 0;

        genres.add("힐링용");
        genres.add("가족여행");
        genres.add("우정여행");
        genres.add("커플여행");
        genres.add("혼술");
        genres.add("알뜰형");


        for(String genre: genres){
            Chip chip = getChip(chipGroup, genre);
            chipGroup.addView(chip);
        }

        list.add("Apple");
        list.add("Banana");
        list.add("Pineapple");
        list.add("Orange");
        list.add("Mango");
        list.add("Grapes");
        list.add("Lemon");
        list.add("Melon");
        list.add("Watermelon");
        list.add("Papaya");


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(SearchActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BudgetActivity.class);
                startActivity(i);
            }
        });
    }

    private Chip getChip(final ChipGroup chipGroup, String text){

        final Chip chip = (Chip)this.getLayoutInflater().inflate(R.layout.item_chip, null, false);
        int paddingDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics());
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
        chip.setText(text);
        chip.setId(chipNumber);
        chipNumber += 1;
        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    checked_genres.add(buttonView.getText().toString());
                else
                    checked_genres.remove(buttonView.getText().toString());
            }
        });
        return chip;
    }
}
