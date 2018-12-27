package com.bootcamp.model.dto;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerDto {

	private int customernumber;
	private String firstname;
	private String lastname;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthdate;
	private String phonenumber;
	private String typephone;
	private String username;
	private String password;
	
	public CustomerDto() {}
	
	public CustomerDto(int customernumber, String firstname, String lastname, Date birthdate, String phonenumber, String typephone, String username, String password) {
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
