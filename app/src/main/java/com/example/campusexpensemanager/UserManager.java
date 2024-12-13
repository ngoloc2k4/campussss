// ...existing code...

public void addNewUser(String userId) {
    DatabaseHandler1 dbHandler = new DatabaseHandler1(context);
    dbHandler.clearOldData(); // Clear old data

    // Add default categories for the new user
    String[] defaultIncomeCategories = context.getResources().getStringArray(R.array.default_income_categories);
    String[] defaultExpenseCategories = context.getResources().getStringArray(R.array.default_expense_categories);

    for (String category : defaultIncomeCategories) {
        dbHandler.addCategory(category, "Income", userId);
    }

    for (String category : defaultExpenseCategories) {
        dbHandler.addCategory(category, "Expense", userId);
    }

    // Add new user logic here
}

// ...existing code...
