package com.example.ohmymoney;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.text.DecimalFormat;

public class NumberTextWatcher implements TextWatcher {
    private DecimalFormat df;
    private DecimalFormat dfnd;
    private boolean hasFractionalPart;

    private EditText et;

    public NumberTextWatcher(EditText et) {
        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);
        dfnd = new DecimalFormat("#,###");
        this.et = et;
        hasFractionalPart = false;
    }

    @SuppressWarnings("unused")
    private static final String TAG = "NumberTextWatcher";

    public void afterTextChanged(Editable s) {
        et.removeTextChangedListener(this);

        try {
            int inilen, endlen;
            int cp, sel;
            inilen = et.getText().length();

            String v = s.toString().replace("￦ ", "");
            v = v.replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n = df.parse(v);
            cp = et.getSelectionStart();

            if(hasFractionalPart)
                et.setText("￦ " + df.format(n));
            else
                et.setText("￦ " + dfnd.format(n));

            endlen = et.getText().length();
            sel = (cp + (endlen - inilen));
            if(sel > 0 && sel <= et.getText().length())
                et.setSelection(sel);
            else
                et.setSelection(et.getText().length() - 1);
        } catch (NumberFormatException nfe) {
            Log.d("Num", nfe.toString());
        } catch (Exception e) {
            Log.d("Num", e.toString());
        }

        et.addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator())))
            hasFractionalPart = true;
        else
            hasFractionalPart = false;
    }
}
