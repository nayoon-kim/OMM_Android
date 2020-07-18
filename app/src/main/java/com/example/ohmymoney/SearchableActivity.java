package com.example.ohmymoney;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class SearchableActivity extends Activity {

    private TextView search_text_view;
    private ChipGroup search_chip_group;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_text_view = findViewById(R.id.search_text_view);
        search_chip_group = findViewById(R.id.chipGroup);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = findViewById(R.id.search_view);
        //SearchManager를 통해 SearchableInfo를 가져와 SearchView에 적용합니다.
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            //검색어는 SearchManater.QUERY라는 string extra로 보내집니다.
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }
    private void doMySearch(String query){
        String[] companyNames = getResources().getStringArray(R.array.company_names);
        for (String companyName : companyNames) {
            if (companyName.toLowerCase().contains(query.toLowerCase())) {
                search_text_view.setText(companyName);
                return;
            } else {
                search_text_view.setText("Search failed.");
            }
        }
    }

}
