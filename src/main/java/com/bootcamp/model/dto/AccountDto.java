package com.bootcamp.model.dto;

import java.sql.Date;

import com.bootcamp.model.Customer;

public class AccountDto {

	private int accountnumber;
	private Date opendate;
	private float balance;
	private Customer customer;
	
	public AccountDto() {}
	
	public AccountDto(int accountnumber, Date opendate, float balance, Customer customer) {
		this.accountnumber = accountnumber;
		this.opendate = opendate;
		this.balance = balance;
		this.customer = customer;
	}

	public int getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	public Date getOpendate() {
		return opendate;
	}

	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
