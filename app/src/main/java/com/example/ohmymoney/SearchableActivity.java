package com.example.ohmymoney;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.HashSet;
import java.util.Set;

import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;
import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import co.moonmonkeylabs.realmsearchview.RealmSearchViewHolder;
import io.realm.Realm;

import com.example.ohmymoney.SpotResult;

public class SearchableActivity extends Activity {

    private TextView search_text_view;
    private ChipGroup search_chip_group;
    private Set<Favorite> favorites;

    private RealmSearchView realmSearchView;
    private SpotResultRecyclerViewAdapter adapter;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //search_text_view = findViewById(R.id.search_text_view);
        //search_chip_group = findViewById(R.id.chipGroup);


        favorites = new HashSet<Favorite>();

//        search_chip_group.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(ChipGroup chipGroup, int i) {
//                Chip chip = search_chip_group.findViewById(i);
//                if(chip != null){
//                    String s = String.valueOf(chip.getText());
//                    if (getString(R.string.couple).equals(s)) {
//                        favorites.add(Favorite.COUPLE);
//                    } else if (getString(R.string.restaurant).equals(s)) {
//                        favorites.add(Favorite.RESTAURANT);
//                    } else if (getString(R.string.tourist_spot).equals(s)) {
//                        favorites.add(Favorite.TOURIST_SPOT);
//                    } else if (getString(R.string.family).equals(s)) {
//                        favorites.add(Favorite.FAMILY);
//                    } else if (getString(R.string.friend).equals(s)) {
//                        favorites.add(Favorite.FRIEND);
//                    } else if (getString(R.string.hotplace).equals(s)) {
//                        favorites.add(Favorite.HOT_PLACE);
//                    } else if (getString(R.string.leisure).equals(s)) {
//                        favorites.add(Favorite.LEISURE);
//                    } else if (getString(R.string.with_dog).equals(s)) {
//                        favorites.add(Favorite.WITH_DOG);
//                    }
//                }
//            }
//        });


    }

    enum Favorite{
        RESTAURANT,
        TOURIST_SPOT,
        LEISURE,
        FAMILY,
        FRIEND,
        COUPLE,
        HOT_PLACE,
        WITH_DOG
    }
    public class SpotResultRecyclerViewAdapter
            extends RealmSearchAdapter<SpotResult, SpotResultRecyclerViewAdapter.ViewHolder> {

        public SpotResultRecyclerViewAdapter(
                Context context,
                Realm realm,
                String filterColumnName) {
            super(context, realm, filterColumnName);
        }

        public class ViewHolder extends RealmSearchViewHolder {

            private SpotResultItemView spotResultItemView;

            public ViewHolder(FrameLayout container, TextView footerTextView) {
                super(container, footerTextView);
            }

            public ViewHolder(SpotResultItemView spotResultItemView) {
                super(spotResultItemView);
                this.spotResultItemView = spotResultItemView;
            }
        }

        @Override
        public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
            ViewHolder vh = new ViewHolder(new SpotResultItemView(viewGroup.getContext()));
            return vh;
        }

        @Override
        public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
            final SpotResult spotResult = realmResults.get(position);
            viewHolder.spotResultItemView.bind(spotResult);
        }

        @Override
        public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup) {
            View v = inflater.inflate(R.layout.footer_view, viewGroup, false);
            return new ViewHolder(
                    (FrameLayout) v,
                    (TextView) v.findViewById(R.id.footer_text_view));
        }

        @Override
        public void onBindFooterViewHolder(ViewHolder holder, int position) {
            super.onBindFooterViewHolder(holder, position);
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }
            );
        }
    }
}


