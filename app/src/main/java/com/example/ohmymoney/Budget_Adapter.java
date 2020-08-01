package com.example.ohmymoney;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Budget_Adapter extends RecyclerView.Adapter<Budget_Adapter.BudgetViewHolder> {
    private Context context;
    private List<Budget> budget_list;

    public Budget_Adapter(Context context, List<Budget> budget_list){
        this.context = context;
        this.budget_list = budget_list;
    }

    @Override
    public BudgetViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_budget_item, parent, false);
        BudgetViewHolder viewHolder = new BudgetViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(BudgetViewHolder holder, int position) {
        Budget budget = budget_list.get(position);

        holder.textView_Store.setText(budget.getStore_name());
        holder.textView_Price.setText(String.valueOf(budget.getStore_price()));
    }
    @Override
    public int getItemCount() {
        return budget_list.size();
    }

    class BudgetViewHolder extends RecyclerView.ViewHolder{

        TextView textView_Store, textView_Price;

        public BudgetViewHolder(View itemView){
            super(itemView);

            textView_Store = itemView.findViewById(R.id.item_tv_title);
            textView_Price = itemView.findViewById(R.id.item_tv_content);
        }
    }
}
