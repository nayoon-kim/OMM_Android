package com.example.ohmymoney;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
