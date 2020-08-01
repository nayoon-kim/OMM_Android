package com.example.ohmymoney;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class PlanActivity1 extends AppCompatActivity {
    private EditText txtMin, txtMax;
    private ImageButton btnAdultUp, btnAdultDown, btnChildUp, btnChildDown;
    private TextView txtAdultNum, txtChildNum, txtDate;
    private ImageView imgCalendar;
    private ChipGroup chipGroup;

    private NumberPicker child, adult;
    private DateOfTrip dDay;
    private ArrayList<String> themaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan1);
        getSupportActionBar().hide();

        this.getViewObject();
        this.initListener();

        // 여행날짜
        dDay = new DateOfTrip();
        txtDate.setText(dDay.getDate());

        // 테마 추가
        themaList = new ArrayList<>();
        addChips();
    }

    public void onClick(View view) {
        // Next 버튼
        Intent intent = new Intent(this, PlanActivity2.class);
        startActivity(intent);
    }

    private void getViewObject() {
        txtMin = (EditText)findViewById(R.id.txtMin);
        txtMax = (EditText)findViewById(R.id.txtMax);
        txtAdultNum = (TextView)findViewById(R.id.txtAdultNum);
        txtChildNum = (TextView)findViewById(R.id.txtChildNum);
        btnAdultDown = (ImageButton)findViewById(R.id.btnAdultDown);
        btnAdultUp = (ImageButton)findViewById(R.id.btnAdultUp);
        btnChildDown = (ImageButton)findViewById(R.id.btnChildDown);
        btnChildUp = (ImageButton)findViewById(R.id.btnChildUp);
        txtDate = (TextView)findViewById(R.id.txtDate);
        imgCalendar = (ImageView)findViewById(R.id.imgCalendar);
        chipGroup = (ChipGroup)findViewById(R.id.chipGroup);
    }

    private void initListener() {
        // 돈 콤마, 원 자동으로 추가하는 이벤트리스너
        txtMin.addTextChangedListener(new NumberTextWatcher(txtMin));
        txtMax.addTextChangedListener(new NumberTextWatcher(txtMax));

        // 인원 수 조정
        child = new NumberPicker(0, 20, 0);
        adult = new NumberPicker(0, 20, 0);

        btnAdultUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adult.setValue(adult.getValue() + 1, txtAdultNum);
            }
        });
        btnAdultDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adult.setValue(adult.getValue() - 1, txtAdultNum);
            }
        });
        btnChildUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                child.setValue(child.getValue() + 1, txtChildNum);
            }
        });
        btnChildDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                child.setValue(child.getValue() - 1, txtChildNum);
            }
        });

        // 달력 다이얼로그
        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });
    }

    public void showDate() {
        // 현재 날짜 구하기
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dDay.setDate(year, month, dayOfMonth);
                txtDate.setText(dDay.getDate());
            }
        }, dDay.getYear(), dDay.getMonth(), dDay.getDay());

        dialog.setMessage("여행날짜");
        dialog.show();
    }

    public void addChips() {
        String[] arr = { "# 힐링", "# 우정여행", "# 가족여행", "# 커플여행", "# 자연", "# 역사여행", "# 식도락", "# 졸업 여행", "# 대학생", "# 액티비티", "# 가성비", "# 갬성", "# 혼자" };
        ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));

        int cnt = 0;
        for(String itr : list) {
            Chip chip = (Chip)this.getLayoutInflater().inflate(R.layout.item_chip, null, false);
            chip.setId(cnt++);
            chip.setText(itr);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                        themaList.add(buttonView.getText().toString());
                    else
                        themaList.remove(buttonView.getText().toString());
                }
            });
            chipGroup.addView(chip);
        }
    }
}

class DateOfTrip {
    private int year;
    private int month;
    private int day;

    public DateOfTrip() {
        Calendar today = Calendar.getInstance();

        year = today.get(today.YEAR);
        month = today.get(today.MONTH);
        day = today.get(today.DATE);
    }

    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getDate() {
        return year + "년 " + (month + 1) + "월 " + day + "일";
    }

    public int getYear() { return this.year; }
    public int getMonth() { return this.month; }
    public int getDay() { return this.day; }
}

class NumberPicker {
    private int value;
    private int maxValue;
    private int minValue;

    public NumberPicker() {
        this.value = 0;
    }

    public NumberPicker(int value, int maxValue, int minValue) {
        this.value = value;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value, TextView tvNumber) {
        if(value <= minValue)
            this.value = minValue;
        else if(value >= maxValue)
            this.value = maxValue;
        else
            this.value = value;

        tvNumber.setText(String.valueOf(this.value));
    }

    public int getMaxValue() {
        return maxValue;
    }
    public void setMaxValue(int value) {
        this.maxValue = value;
    }
    public int getMinValue() {
        return minValue;
    }
    public void setMinValue(int value) {
        this.minValue = value;
    }
}
