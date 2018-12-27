package com.bootcamp.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customernumber;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private Date birthdate;
	@Column
	private String phonenumber;
	@Column
	private String typephone;
	@Column
	private String username;
	@Column
	private String password;
	
public Customer() {}
	
	public Customer(int customernumber, String firstname, String lastname, Date birthdate, String phonenumber, String typephone, String username, String password) {
		this.customernumber = customernumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.phonenumber = phonenumber;
		this.typephone = typephone;
		this.username = username;
		this.password = password;
	}

	public int getCustomernumber() {
		return customernumber;
	}

	public void setCustomernumber(int customernumber) {
		this.customernumber = customernumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getTypephone() {
		return typephone;
	}

	public void setTypephone(String typephone) {
		this.typephone = typephone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
