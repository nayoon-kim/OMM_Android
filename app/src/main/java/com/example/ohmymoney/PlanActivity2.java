package com.example.ohmymoney;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class PlanActivity2 extends AppCompatActivity {
    private LinearLayout layoutTmap;
    private TMapView tMapView;
    private ListView listView;

    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan2);
        setCustomActionBar();

        this.getViewObject();
        this.initListener();

        // Tmap 설정
        tMapView = new TMapView(this);
        setTMapView();

        // 리스트뷰&어탭터
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        addListViewItems();
    }

    private void getViewObject() {
        layoutTmap = (LinearLayout)findViewById(R.id.layoutTmap);
        listView = (ListView)findViewById(R.id.listView);
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem item = (ListViewItem)parent.getItemAtPosition(position);

            }
        });
    }

    public void setTMapView() {
        // 맵 초기화
        tMapView.setSKTMapApiKey("l7xx4d7fa19c1d544d25beb0e537d32cca36");
        tMapView.setCompassMode(false);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setTrackingMode(false);
        tMapView.setSightVisible(false);
        layoutTmap.addView(tMapView);
    }

    public void onClick(View v) {
        int id = v.getId();

        switch (id)
        {
            case R.id.btnExit:
            {
                // 종료
                Intent home = new Intent(this, MainActivity.class);
                home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);          // 스택 중간의 액태비티들 삭제
                home.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);         // 재사용
                startActivity(home);
                finish();
                break;
            }
            case R.id.btnAgain:
            {
                // 일정 다시!
                break;
            }
        }
    }

    void setCustomActionBar() {
        // 액션바 초기화
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);        // custom enabled = true
        actionBar.setDisplayHomeAsUpEnabled(true);         // 뒤로가기 버튼
        actionBar.setDisplayUseLogoEnabled(true);           // home logo
        actionBar.setDisplayShowHomeEnabled(true);          // show home logo

        actionBar.setTitle("  ");
        actionBar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 커스텁 액션바
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 액션바 버튼 리스너
        switch ((item.getItemId())) {
            case R.id.action_map:  {
                return true;
            }
            case R.id.action_save: {
                // 마이페이지에 저장
                return true;
            }
            default:
                return false;
        }
    }

    public void addListViewItems() {
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.icon_1), "광화문", "대한민국 서울특별시 종로구 사직로 161");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.icon_2), "홍대입구역", "서울특별시 서교동");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.icon_3), "롯데월드", "서울특별시 송파구 잠실동 올림픽로 240");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.icon_4), "롯데월드타워",  "서울특별시 송파구 잠실6동 올림픽로 300");
    }
}

class ListViewItem {
    private Drawable iconDrawable;
    private String titleStr;
    private String descStr;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
}

class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> itemList = new ArrayList<>();

    public ListViewAdapter() {
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        ImageView imgNum = (ImageView)convertView.findViewById(R.id.imgNum);
        TextView txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
        TextView txtDesc = (TextView)convertView.findViewById(R.id.txtDesc);

        ListViewItem listViewItem = itemList.get(position);

        imgNum.setImageDrawable(listViewItem.getIcon());
        txtTitle.setText(listViewItem.getTitle());
        txtDesc.setText(listViewItem.getDesc());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    public void addItem(Drawable icon, String title, String desc) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        itemList.add(item);
    }
}
