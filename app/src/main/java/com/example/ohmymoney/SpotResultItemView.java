package com.example.ohmymoney;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.ohmymoney.SpotResult;

public class SpotResultItemView extends RelativeLayout {

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.description)
    TextView description;

    public SpotResultItemView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.spotresult_item_view, this);
        ButterKnife.bind(this);
    }

    public void bind(SpotResult spotResult) {
        title.setText(spotResult.getTitle());
        description.setText(spotResult.getContent());
    }
}
