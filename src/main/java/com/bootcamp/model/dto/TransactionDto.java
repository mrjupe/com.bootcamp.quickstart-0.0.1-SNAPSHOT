package com.bootcamp.model.dto;

import com.bootcamp.model.Account;

public class TransactionDto {

	private int id;
	private String type;
	private float amount;
	private String amountsign;
	private Account account;
	
	public TransactionDto() {}
	
	public TransactionDto(int id, String type, float amount, String amountsign, Account account) {
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.amountsign = amountsign;
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getAmountsign() {
		return amountsign;
	}

	public void setAmountsign(String amountsign) {
		this.amountsign = amountsign;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
