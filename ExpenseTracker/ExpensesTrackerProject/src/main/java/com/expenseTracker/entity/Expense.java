package com.expenseTracker.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
	private int transactionId;
    private String title;
    private BigDecimal amount;
    private String type;
    private String category;
    private LocalDate transactionDate;
    private String description;
    
    // Constructor without transaction ID
    public Expense(String title, BigDecimal amount,
                   String type, String category,
                   LocalDate transactionDate, String description) {

        this.title = title;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.transactionDate = transactionDate;
        this.description = description;
    }

    // Constructor with transaction ID
    public Expense(int transactionId, String title,
                   BigDecimal amount, String type,
                   String category, LocalDate transactionDate,
                   String description) {

        this.transactionId = transactionId;
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.transactionDate = transactionDate;
        this.description = description;
    }
    //Getter and Setter methods

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    

}
