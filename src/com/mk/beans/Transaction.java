package com.mk.beans;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private long transactionId;
	
	private long referenceId;
	
	private Date transactionDate;
	
	private int transactionTypeCode;
	
	private String transactionComments;
	
	private double debitAmount;
	
	private double creditAmount;
	
	private int transactionStatus;
	
	private double balanceAfterTransaction;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(long referenceId) {
		this.referenceId = referenceId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getTransactionTypeCode() {
		return transactionTypeCode;
	}

	public void setTransactionTypeCode(int transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	public String getTransactionComments() {
		return transactionComments;
	}

	public void setTransactionComments(String transactionComments) {
		this.transactionComments = transactionComments;
	}

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public int getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(int transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public double getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}

	public void setBalanceAfterTransaction(double balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

}
