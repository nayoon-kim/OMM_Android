package com.example.ohmymoney;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
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

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class PlanActivity2 extends AppCompatActivity {
    private LinearLayout layoutTmap;
    private TMapView tMapView;
    private ListView listView;

    private ListViewAdapter adapter;
    private List<Address> places;
    private String[] strList = { "감천문화마을", "BIFF광장", "이바구길" };

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
        setMarksOnMap();

        // 리스트뷰&어탭터
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        addListViewItems();
    }

    private void setMarksOnMap()
    {
        Thread thread = new Thread();

        // 지도 위에 장소 추가
        places = new ArrayList<>();
        final Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String errMesg = "";
        TMapData tMapData = new TMapData();

        for(String item : strList) {
            try {
               List<Address> list = geocoder.getFromLocationName(item, 1);

                if(list != null && list.size() != 0) {
                    places.add(list.get(0));
                }
            } catch (IOException e) {
                errMesg = e.getMessage();
                Log.e("map", "입출력 오류 - 서버에서 주소 변환시 에러발생");
            } catch (IllegalArgumentException illegalArgumentException) {
                // 잘못된 GPS 가져왔을때
                errMesg = illegalArgumentException.getMessage();
                Log.e("map", "잘못된 GPS 가져옴");
            } catch (Exception ex) {
                errMesg = ex.getMessage();
            }

            if(!errMesg.isEmpty() || !errMesg.equals("")) {
                return;
            }
        }

        // 마커 생성
        ArrayList<TMapPoint> listTMapPoint = new ArrayList<>();
        for(int i = 0; i < places.size(); i++) {
            TMapMarkerItem markerItem = new TMapMarkerItem();
            listTMapPoint.add(new TMapPoint(places.get(i).getLatitude(), places.get(i).getLongitude()));         // 위도, 경도

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
            markerItem.setIcon(bitmap);                 // 마커 아이콘 지정
            markerItem.setPosition(0.5f, 1.0f);
            markerItem.setTMapPoint(listTMapPoint.get(i));         // 좌표 지정
            markerItem.setName(strList[i]);             // 마커의 타이틀 지정
            tMapView.addMarkerItem("marker" + Integer.toString(i), markerItem);
        }
        tMapView.setCenterPoint(listTMapPoint.get(0).getLongitude() + 0.001f, listTMapPoint.get(0).getLatitude() - 0.005f); // 경도, 위도

        // 선 그리기
        TMapPolyLine tMapPolyLIne = new TMapPolyLine();
        tMapPolyLIne.setLineColor(Color.YELLOW);
        tMapPolyLIne.setOutLineColor(Color.YELLOW);
        tMapPolyLIne.setLineWidth(2);
        for(int i = 0; i < listTMapPoint.size(); i++) {
            tMapPolyLIne.addLinePoint(listTMapPoint.get(i));
        }
        tMapView.addTMapPolyLine("Line1", tMapPolyLIne);
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
        // tmap 맵 초기화
        tMapView.setSKTMapApiKey("l7xx4d7fa19c1d544d25beb0e537d32cca36");
        tMapView.setCompassMode(false);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(15);              // zoom level : 1(멀리)~19(확대)
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
        for(int i = 0; i < places.size(); i++) {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.icon_1), strList[i], places.get(i).getAddressLine(0));
        }
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

