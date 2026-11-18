
package com.expenseTracker.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.expenseTracker.dao.ExpenseDao;
import com.expenseTracker.entity.Expense;

public class ExpenseTrackerFrame extends Frame implements ActionListener {

    // Database object
    private ExpenseDao dao = new ExpenseDao();

    // Color Theme
    private Color backgroundColor = new Color(245, 247, 250);
    private Color navyColor = new Color(30, 41, 59);

    private Color incomeColor = new Color(220, 252, 231);
    private Color expenseColor = new Color(254, 226, 226);
    private Color balanceColor = new Color(219, 234, 254);

    private Color incomeText = new Color(22, 101, 52);
    private Color expenseText = new Color(185, 28, 28);
    private Color balanceText = new Color(29, 78, 216);

    // Input Fields
    private TextField idField = new TextField();
    private TextField titleField = new TextField();
    private TextField amountField = new TextField();
    private TextField dateField = new TextField();

    private Choice typeChoice = new Choice();
    private Choice categoryChoice = new Choice();

    private TextArea descriptionArea = new TextArea(3, 20);

    // Transaction List
    private List transactionList = new List(12);

    // Dashboard Labels
    private Label incomeLabel = new Label("Total Income: Rs. 0.00");
    private Label expenseLabel = new Label("Total Expenses: Rs. 0.00");
    private Label balanceLabel = new Label("Balance: Rs. 0.00");

    // Buttons
    private Button addButton = new Button("Add");
    private Button viewButton = new Button("View All");
    private Button searchButton = new Button("Search");
    private Button updateButton = new Button("Update");
    private Button deleteButton = new Button("Delete");
    private Button summaryButton = new Button("Refresh Summary");
    private Button clearButton = new Button("Clear");

    // Status Label
    private Label statusLabel =
            new Label("Welcome to Personal Expense Tracker");


    // Constructor
    public ExpenseTrackerFrame() {

        setTitle("Personal Expense Tracker");

        setSize(1100, 700);

        setBackground(backgroundColor);

        setLayout(new BorderLayout(15, 15));


        // ================= HEADER =================

        Panel headerPanel = new Panel(new BorderLayout(10, 10));

        headerPanel.setBackground(navyColor);

        Label heading = new Label(
                "PERSONAL EXPENSE TRACKER",
                Label.CENTER
        );

        heading.setBackground(navyColor);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 25));

        headerPanel.add(heading, BorderLayout.NORTH);


        // ================= DASHBOARD =================

        Panel dashboardPanel =
                new Panel(new GridLayout(1, 3, 15, 10));

        dashboardPanel.setBackground(backgroundColor);

        // Income Card
        incomeLabel.setBackground(incomeColor);
        incomeLabel.setForeground(incomeText);
        incomeLabel.setFont(new Font("Arial", Font.BOLD, 17));
        incomeLabel.setAlignment(Label.CENTER);

        // Expense Card
        expenseLabel.setBackground(expenseColor);
        expenseLabel.setForeground(expenseText);
        expenseLabel.setFont(new Font("Arial", Font.BOLD, 17));
        expenseLabel.setAlignment(Label.CENTER);

        // Balance Card
        balanceLabel.setBackground(balanceColor);
        balanceLabel.setForeground(balanceText);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 17));
        balanceLabel.setAlignment(Label.CENTER);

        dashboardPanel.add(incomeLabel);
        dashboardPanel.add(expenseLabel);
        dashboardPanel.add(balanceLabel);

        headerPanel.add(dashboardPanel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);


        // ================= MAIN CONTENT =================

        Panel contentPanel =
                new Panel(new GridLayout(1, 2, 15, 15));

        contentPanel.setBackground(backgroundColor);


        // ================= FORM PANEL =================

        Panel formPanel =
                new Panel(new GridLayout(7, 2, 10, 10));

        formPanel.setBackground(Color.WHITE);

        Label idLabel = createFormLabel("Transaction ID:");
        Label titleLabel = createFormLabel("Title:");
        Label amountLabel = createFormLabel("Amount:");
        Label typeLabel = createFormLabel("Type:");
        Label categoryLabel = createFormLabel("Category:");
        Label dateLabel = createFormLabel("Date (yyyy-MM-dd):");
        Label descriptionLabel = createFormLabel("Description:");

        // Type Options
        typeChoice.add("INCOME");
        typeChoice.add("EXPENSE");

        // Category Options
        categoryChoice.add("Salary");
        categoryChoice.add("Freelance");
        categoryChoice.add("Food");
        categoryChoice.add("Rent");
        categoryChoice.add("Travel");
        categoryChoice.add("Shopping");
        categoryChoice.add("Bills");
        categoryChoice.add("Health");
        categoryChoice.add("Other");

        // Default Date
        dateField.setText(LocalDate.now().toString());

        // Form Components
        formPanel.add(idLabel);
        formPanel.add(idField);

        formPanel.add(titleLabel);
        formPanel.add(titleField);

        formPanel.add(amountLabel);
        formPanel.add(amountField);

        formPanel.add(typeLabel);
        formPanel.add(typeChoice);

        formPanel.add(categoryLabel);
        formPanel.add(categoryChoice);

        formPanel.add(dateLabel);
        formPanel.add(dateField);

        formPanel.add(descriptionLabel);
        formPanel.add(descriptionArea);


        // Style Input Fields
        styleTextField(idField);
        styleTextField(titleField);
        styleTextField(amountField);
        styleTextField(dateField);

        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setForeground(navyColor);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));

        typeChoice.setFont(new Font("Arial", Font.PLAIN, 14));
        categoryChoice.setFont(new Font("Arial", Font.PLAIN, 14));


        contentPanel.add(formPanel);


        // ================= TRANSACTION LIST =================

        Panel listPanel = new Panel(new BorderLayout(5, 5));

        listPanel.setBackground(Color.WHITE);

        Label listHeading =
                new Label("TRANSACTION HISTORY", Label.CENTER);

        listHeading.setBackground(navyColor);
        listHeading.setForeground(Color.WHITE);
        listHeading.setFont(new Font("Arial", Font.BOLD, 17));

        transactionList.setBackground(Color.WHITE);
        transactionList.setForeground(navyColor);
        transactionList.setFont(new Font("Arial", Font.PLAIN, 13));

        listPanel.add(listHeading, BorderLayout.NORTH);

        listPanel.add(transactionList, BorderLayout.CENTER);

        contentPanel.add(listPanel);

        add(contentPanel, BorderLayout.CENTER);


        // ================= BUTTON PANEL =================

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 8, 10));

        buttonPanel.setBackground(backgroundColor);

        // Button Colors
        styleButton(addButton, new Color(22, 163, 74));

        styleButton(viewButton, new Color(37, 99, 235));

        styleButton(searchButton, new Color(14, 116, 144));

        styleButton(updateButton, new Color(217, 119, 6));

        styleButton(deleteButton, new Color(220, 38, 38));

        styleButton(summaryButton, new Color(79, 70, 229));

        styleButton(clearButton, new Color(107, 114, 128));

        // Add Buttons
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(summaryButton);
        buttonPanel.add(clearButton);


        // ================= BOTTOM PANEL =================

        Panel bottomPanel = new Panel(new BorderLayout());

        bottomPanel.setBackground(backgroundColor);

        statusLabel.setForeground(navyColor);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));

        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);


        // ================= ACTION LISTENERS =================

        addButton.addActionListener(this);

        viewButton.addActionListener(this);

        searchButton.addActionListener(this);

        updateButton.addActionListener(this);

        deleteButton.addActionListener(this);

        summaryButton.addActionListener(this);

        clearButton.addActionListener(this);


        // Transaction List Selection
        transactionList.addActionListener(e -> {

            try {

                loadSelectedTransaction();

            } catch (SQLException ex) {

                statusLabel.setText("Error: " + ex.getMessage());

                ex.printStackTrace();
            }

        });


        // ================= WINDOW CLOSING =================

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                dispose();

            }

        });


        setLocationRelativeTo(null);


        // Load Database Records
        try {

            refreshAll();

        } catch (SQLException ex) {

            statusLabel.setText("Database Error: " + ex.getMessage());

            ex.printStackTrace();
        }

        setVisible(true);

    }


    // ================= HELPER METHODS =================

    private Label createFormLabel(String text) {

        Label label = new Label(text);

        label.setForeground(navyColor);

        label.setFont(new Font("Arial", Font.BOLD, 13));

        label.setBackground(Color.WHITE);

        return label;
    }


    private void styleTextField(TextField field) {

        field.setBackground(Color.WHITE);

        field.setForeground(navyColor);

        field.setFont(new Font("Arial", Font.PLAIN, 14));

    }


    private void styleButton(Button button, Color color) {

        button.setBackground(color);

        button.setForeground(Color.WHITE);

        button.setFont(new Font("Arial", Font.BOLD, 13));

    }


    // ================= BUTTON EVENTS =================

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        try {

            if (source == addButton) {

                addExpense();

            } else if (source == viewButton) {

                refreshAll();

            } else if (source == searchButton) {

                searchExpense();

            } else if (source == updateButton) {

                updateExpense();

            } else if (source == deleteButton) {

                deleteExpense();

            } else if (source == summaryButton) {

                refreshSummary();

                statusLabel.setText("Dashboard updated.");

            } else if (source == clearButton) {

                clearForm();

                statusLabel.setText("Form cleared.");

            }

        } catch (Exception ex) {

            statusLabel.setText("Error: " + ex.getMessage());

            ex.printStackTrace();

        }

    }


    // ================= READ FORM =================

    private Expense readExpenseForm(int id) {

        String title = titleField.getText().trim();

        String amountText = amountField.getText().trim();

        if (title.isEmpty() || amountText.isEmpty()) {

            throw new IllegalArgumentException(
                    "Title and amount are required."
            );

        }

        BigDecimal amount = new BigDecimal(amountText);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Amount must be greater than zero."
            );

        }

        String type = typeChoice.getSelectedItem();

        String category = categoryChoice.getSelectedItem();

        LocalDate date =
                LocalDate.parse(dateField.getText().trim());

        String description = descriptionArea.getText().trim();


        if (id == 0) {

            return new Expense(
                    title,
                    amount,
                    type,
                    category,
                    date,
                    description
            );

        } else {

            return new Expense(
                    id,
                    title,
                    amount,
                    type,
                    category,
                    date,
                    description
            );

        }

    }


    // ================= ADD EXPENSE =================

    private void addExpense() throws SQLException {

        Expense expense = readExpenseForm(0);

        boolean result = dao.addExpense(expense);

        if (result) {

            refreshAll();

            clearForm();

            statusLabel.setText("Transaction added successfully!");

        }

    }


    // ================= VIEW EXPENSES =================

    private void viewExpenses() throws SQLException {

        transactionList.removeAll();

        ArrayList<Expense> expenses = dao.viewExpenses();

        for (Expense expense : expenses) {

            String record =
                    "ID: " + expense.getTransactionId()
                    + " | " + expense.getTitle()
                    + " | " + expense.getType()
                    + " | Rs. " + expense.getAmount()
                    + " | " + expense.getCategory()
                    + " | " + expense.getTransactionDate();

            transactionList.add(record);

        }

    }


    // ================= SEARCH EXPENSE =================

    private void searchExpense() throws SQLException {

        int id = Integer.parseInt(idField.getText().trim());

        Expense expense = dao.searchExpense(id);

        if (expense != null) {

            fillForm(expense);

            statusLabel.setText("Transaction found!");

        } else {

            statusLabel.setText("Transaction not found.");

        }

    }


    // ================= UPDATE EXPENSE =================

    private void updateExpense() throws SQLException {

        int id = Integer.parseInt(idField.getText().trim());

        Expense expense = readExpenseForm(id);

        boolean result = dao.updateExpense(expense);

        if (result) {

            refreshAll();

            clearForm();

            statusLabel.setText("Transaction updated successfully!");

        } else {

            statusLabel.setText("Transaction not found.");

        }

    }


    // ================= DELETE EXPENSE =================

    private void deleteExpense() throws SQLException {

        int id = Integer.parseInt(idField.getText().trim());

        boolean result = dao.deleteExpense(id);

        if (result) {

            refreshAll();

            clearForm();

            statusLabel.setText("Transaction deleted successfully!");

        } else {

            statusLabel.setText("Transaction not found.");

        }

    }


    // ================= LOAD SELECTED TRANSACTION =================

    private void loadSelectedTransaction() throws SQLException {

        String selected = transactionList.getSelectedItem();

        if (selected == null) {

            return;

        }

        int start = selected.indexOf("ID: ") + 4;

        int end = selected.indexOf(" |");

        int id = Integer.parseInt(
                selected.substring(start, end).trim()
        );

        Expense expense = dao.searchExpense(id);

        if (expense != null) {

            fillForm(expense);

            statusLabel.setText("Transaction selected.");

        }

    }


    // ================= FILL FORM =================

    private void fillForm(Expense expense) {

        idField.setText(
                String.valueOf(expense.getTransactionId())
        );

        titleField.setText(expense.getTitle());

        amountField.setText(
                expense.getAmount().toPlainString()
        );

        typeChoice.select(expense.getType());

        categoryChoice.select(expense.getCategory());

        dateField.setText(
                expense.getTransactionDate().toString()
        );

        descriptionArea.setText(expense.getDescription());

    }


    // ================= REFRESH ALL =================

    private void refreshAll() throws SQLException {

        viewExpenses();

        refreshSummary();

    }


    // ================= DASHBOARD SUMMARY =================

    private void refreshSummary() throws SQLException {

        BigDecimal income = dao.getTotalIncome();

        BigDecimal expenses = dao.getTotalExpense();

        BigDecimal balance = income.subtract(expenses);

        incomeLabel.setText(
                "TOTAL INCOME: Rs. " + income.toPlainString()
        );

        expenseLabel.setText(
                "TOTAL EXPENSES: Rs. " + expenses.toPlainString()
        );

        balanceLabel.setText(
                "AVAILABLE BALANCE: Rs. " + balance.toPlainString()
        );

    }


    // ================= CLEAR FORM =================

    private void clearForm() {

        idField.setText("");

        titleField.setText("");

        amountField.setText("");

        dateField.setText(LocalDate.now().toString());

        descriptionArea.setText("");

        typeChoice.select(0);

        categoryChoice.select(0);

    }

}
