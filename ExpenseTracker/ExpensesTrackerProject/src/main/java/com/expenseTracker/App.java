package com.expenseTracker;
import java.awt.EventQueue;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import com.expenseTracker.dao.ExpenseDao;
import com.expenseTracker.db.DbConnection;
import com.expenseTracker.entity.Expense;
import com.expenseTracker.ui.ExpenseTrackerFrame;

public class App 
{
    public static void main(String[] args) {
    	EventQueue.invokeLater(() -> {
            new ExpenseTrackerFrame();
        });


      /*  try {
            Connection con = DbConnection.getConnection();

            System.out.println("Database Connected Successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



       Expense expense = new Expense(
                "Monthly Salary",
                new BigDecimal("25000.00"),
                "INCOME",
                "Salary",
                LocalDate.now(),
                "November salary"
        );

        ExpenseDao dao = new ExpenseDao();

        try {

            boolean result = dao.addExpense(expense);

            if (result) {
                System.out.println("Expense added successfully!");
            } else {
                System.out.println("Expense insertion failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        


     /*   ExpenseDao dao = new ExpenseDao();

        try {

            // 1. ADD EXPENSE
            System.out.println("----- ADD EXPENSE -----");

            Expense expense = new Expense(
                    "JDBC Test Transaction",
                    new BigDecimal("500.00"),
                    "EXPENSE",
                    "Testing",
                    LocalDate.now(),
                    "Testing JDBC operations"
            );

            boolean added = dao.addExpense(expense);

            if (added) {
                System.out.println("Expense added successfully!");
            }

            // 2. VIEW ALL EXPENSES
            System.out.println("\n----- VIEW EXPENSES -----");

            ArrayList<Expense> expenses = dao.viewExpenses();

            for (Expense e : expenses) {

                System.out.println("ID: " + e.getTransactionId());
                System.out.println("Title: " + e.getTitle());
                System.out.println("Amount: " + e.getAmount());
                System.out.println("Type: " + e.getType());
                System.out.println("Category: " + e.getCategory());
                System.out.println("Date: " + e.getTransactionDate());
                System.out.println("----------------------");
            }

            // Find the transaction we just inserted
            Expense inserted = null;

            for (Expense e : expenses) {
                if (e.getTitle().equals("JDBC Test Transaction")
                        && e.getTransactionDate().equals(LocalDate.now())) {
                    inserted = e;
                    break;
                }
            }

            if (inserted == null) {
                System.out.println("Test transaction not found.");
                return;
            }

            int id = inserted.getTransactionId();

            // 3. SEARCH EXPENSE
            System.out.println("\n----- SEARCH EXPENSE -----");

            Expense searched = dao.searchExpense(id);

            if (searched != null) {
                System.out.println("Found: " + searched.getTitle());
                System.out.println("Amount: " + searched.getAmount());
            }

            // 4. UPDATE EXPENSE
            System.out.println("\n----- UPDATE EXPENSE -----");

            Expense updatedExpense = new Expense(
                    id,
                    "Updated JDBC Test",
                    new BigDecimal("750.00"),
                    "EXPENSE",
                    "Testing",
                    LocalDate.now(),
                    "Updated transaction"
            );

            boolean updated = dao.updateExpense(updatedExpense);

            System.out.println("Update successful: " + updated);

            // 5. DELETE EXPENSE
            System.out.println("\n----- DELETE EXPENSE -----");

            boolean deleted = dao.deleteExpense(id);

            System.out.println("Delete successful: " + deleted);

            // 6. FINANCIAL SUMMARY
            System.out.println("\n----- FINANCIAL SUMMARY -----");

            System.out.println("Total Income: " + dao.getTotalIncome());

            System.out.println("Total Expenses: " + dao.getTotalExpense());

            System.out.println("Available Balance: " + dao.getBalance());

        } catch (Exception e) {

            e.printStackTrace();
        }*/
        
       
    }


    }



