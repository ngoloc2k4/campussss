package com.example.campusexpensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.campusexpensemanager.incomeModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler1 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "income.db";
    public static final String TABLE_NAME = "income_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "AMOUNT";
    public static final String COL3 = "TYPE";
    public static final String COL4 = "NOTE";
    public static final String COL5 = "DATE";
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_CATEGORY = "CATEGORY";

    // Define the missing columns
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_NOTE = "note";

    public DatabaseHandler1(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "AMOUNT TEXT," + "TYPE TEXT," + "NOTE TEXT," + "DATE TEXT," + "USER_ID TEXT," + "CATEGORY TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String a = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(a);
        onCreate(db);
    }

    public boolean addData(String amount, String type, String note, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, amount);
        contentValues.put(COL3, type);
        contentValues.put(COL4, note);
        contentValues.put(COL5, date);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void update(String id, String amount, String type, String note, String date) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, amount);
        contentValues.put(COL3, type);
        contentValues.put(COL4, note);
        contentValues.put(COL5, date);

        long result = database.update(TABLE_NAME, contentValues, "id=?", new String[]{id});
        if (result == -1) {
        } else {
        }
    }
    public boolean delete(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        int result = database.delete(TABLE_NAME, "id=?", new String[]{id});

        return result >0;
    }

    public List<incomeModel> getAllIncome() {
        List<incomeModel> incomeModelList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (data.getCount() == 0) {

        } else {
            if (incomeModelList == null) {
                incomeModelList = new ArrayList<>();
            }

            while (data.moveToNext()) {
                incomeModelList.add(new incomeModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4)));
            }
        }

        return incomeModelList;
    }

    public boolean addIncome(incomeModel income, String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_AMOUNT, income.getAmount());
        values.put(COLUMN_TYPE, income.getType());
        values.put(COLUMN_NOTE, income.getNote());
        values.put(COLUMN_CATEGORY, income.getCategory());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public List<incomeModel> getAllIncomeByUserId(String userId) {
        List<incomeModel> incomeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_USER_ID + "=?", new String[]{userId}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                incomeModel income = new incomeModel();
                income.setId(cursor.getInt(cursor.getColumnIndex(COL1)));
                income.setAmount(cursor.getString(cursor.getColumnIndex(COL2)));
                income.setType(cursor.getString(cursor.getColumnIndex(COL3)));
                income.setNote(cursor.getString(cursor.getColumnIndex(COL4)));
                income.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
                incomeList.add(income);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return incomeList;
    }

    public void clearOldData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public boolean addCategory(String category, String type, String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CATEGORY", category);
        values.put("TYPE", type);
        values.put("USER_ID", userId);

        long result = db.insert("categories", null, values);
        db.close();
        return result != -1;
    }

    public List<String> getCategoriesByTypeAndUserId(String type, String userId) {
        List<String> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("categories", new String[]{"CATEGORY"}, "TYPE=? AND USER_ID=?", new String[]{type, userId}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(cursor.getColumnIndex("CATEGORY")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return categories;
    }

    public void addExpense(Expenses expense, String userId) {
        // Implement the method to add an expense
    }
}