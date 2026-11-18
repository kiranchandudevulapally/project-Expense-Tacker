package com.expenseTracker.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.expenseTracker.db.DbConnection;
import com.expenseTracker.entity.Expense;

public class ExpenseDao {

    // 1. Add Expense
    public boolean addExpense(Expense expense) throws SQLException {

        String sql = "INSERT INTO transactions "
                + "(title, amount, type, category, transaction_date, description) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, expense.getTitle());
            ps.setBigDecimal(2, expense.getAmount());
            ps.setString(3, expense.getType());
            ps.setString(4, expense.getCategory());
            ps.setDate(5, java.sql.Date.valueOf(
                    expense.getTransactionDate()));
            ps.setString(6, expense.getDescription());

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }


    // 2. View All Expenses
    public ArrayList<Expense> viewExpenses() throws SQLException {

        ArrayList<Expense> expenses = new ArrayList<>();

        String sql = "SELECT * FROM transactions "
                + "ORDER BY transaction_date DESC, transaction_id DESC";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Expense expense = new Expense(
                        rs.getInt("transaction_id"),
                        rs.getString("title"),
                        rs.getBigDecimal("amount"),
                        rs.getString("type"),
                        rs.getString("category"),
                        rs.getDate("transaction_date").toLocalDate(),
                        rs.getString("description")
                );

                expenses.add(expense);
            }
        }

        return expenses;
    }


    // 3. Search Expense by ID
    public Expense searchExpense(int transactionId) throws SQLException {

        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, transactionId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Expense expense = new Expense(
                            rs.getInt("transaction_id"),
                            rs.getString("title"),
                            rs.getBigDecimal("amount"),
                            rs.getString("type"),
                            rs.getString("category"),
                            rs.getDate("transaction_date").toLocalDate(),
                            rs.getString("description")
                    );

                    return expense;
                }
            }
        }

        return null;
    }


    // 4. Update Expense
    public boolean updateExpense(Expense expense) throws SQLException {

        String sql = "UPDATE transactions SET "
                + "title = ?, amount = ?, type = ?, category = ?, "
                + "transaction_date = ?, description = ? "
                + "WHERE transaction_id = ?";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, expense.getTitle());
            ps.setBigDecimal(2, expense.getAmount());
            ps.setString(3, expense.getType());
            ps.setString(4, expense.getCategory());
            ps.setDate(5, java.sql.Date.valueOf(
                    expense.getTransactionDate()));
            ps.setString(6, expense.getDescription());
            ps.setInt(7, expense.getTransactionId());

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }


    // 5. Delete Expense
    public boolean deleteExpense(int transactionId) throws SQLException {

        String sql = "DELETE FROM transactions WHERE transaction_id = ?";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, transactionId);

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }


    // 6. Calculate Total Income
    public BigDecimal getTotalIncome() throws SQLException {

        return getTotalByType("INCOME");
    }


    // 7. Calculate Total Expenses
    public BigDecimal getTotalExpense() throws SQLException {

        return getTotalByType("EXPENSE");
    }


    // Common method to calculate totals
    private BigDecimal getTotalByType(String type) throws SQLException {

        String sql = "SELECT COALESCE(SUM(amount), 0) AS total "
                + "FROM transactions WHERE type = ?";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, type);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getBigDecimal("total");
                }
            }
        }

        return BigDecimal.ZERO;
    }


    // 8. Calculate Available Balance
    public BigDecimal getBalance() throws SQLException {

        BigDecimal income = getTotalIncome();
        BigDecimal expense = getTotalExpense();

        return income.subtract(expense);
    }

}


