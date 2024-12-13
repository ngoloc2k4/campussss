package com.example.campusexpensemanager;

public class incomeModel {
    private int id;
    private String amount;
    private String type;
    private String note;
    private String category;

    // Constructor with all fields
    public incomeModel(int id, String amount, String type, String note, String category) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.note = note;
        this.category = category;
    }

    // Default constructor
    public incomeModel() {
    }

    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}