package com.example.campusexpensemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusexpensemanager.R;
import com.example.campusexpensemanager.Model.Budget;

import java.util.List;

public class BudgetCardAdapter extends RecyclerView.Adapter<BudgetCardAdapter.ViewHolder> {

    private Context context;
    private List<Budget> budgetList;

    public BudgetCardAdapter(Context context, List<Budget> budgetList) {
        this.context = context;
        this.budgetList = budgetList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_budget_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Budget budget = budgetList.get(position);
        holder.tv_fullName.setText(budget.getFullName());
        holder.tv_remainingBudget.setText("Remaining Budget: " + budget.getRemainingBudget() + " VND");

        int progress = (int) ((budget.getRemainingBudget() / (float) budget.getTotalBudget()) * 100);
        holder.progressBar.setProgress(progress);

        if (progress < 25) {
            holder.progressBar.setProgressTintList(context.getResources().getColorStateList(R.color.colorRed));
        } else if (progress < 50) {
            holder.progressBar.setProgressTintList(context.getResources().getColorStateList(R.color.colorOrange));
        } else if (progress < 75) {
            holder.progressBar.setProgressTintList(context.getResources().getColorStateList(R.color.colorYellow));
        } else {
            holder.progressBar.setProgressTintList(context.getResources().getColorStateList(R.color.colorGreen));
        }
    }

    @Override
    public int getItemCount() {
        return budgetList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_fullName, tv_remainingBudget;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_fullName = itemView.findViewById(R.id.tv_fullName);
            tv_remainingBudget = itemView.findViewById(R.id.tv_remainingBudget);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
