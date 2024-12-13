package com.example.campusexpensemanager.Model;

public class Budget {
    private String fullName;
    private int totalBudget;
    private int remainingBudget;

    public Budget(String fullName, int totalBudget, int remainingBudget) {
        this.fullName = fullName;
        this.totalBudget = totalBudget;
        this.remainingBudget = remainingBudget;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(int totalBudget) {
        this.totalBudget = totalBudget;
    }

    public int getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(int remainingBudget) {
        this.remainingBudget = remainingBudget;
    }
}
