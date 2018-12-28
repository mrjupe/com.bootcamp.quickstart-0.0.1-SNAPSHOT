package com.bootcamp.dao;

import java.util.List;

import com.bootcamp.model.Account;
import com.bootcamp.model.Customer;
import com.bootcamp.quickstart.CustomException;

public interface AccountDao {

	Account getById(int id) throws CustomException;
	Account save(Account account) throws CustomException;
	void delete(Account account) throws CustomException;
	
	List<Account> getList() throws CustomException;
	List<Account> getListByCustomer(Customer customer) throws CustomException;
}
