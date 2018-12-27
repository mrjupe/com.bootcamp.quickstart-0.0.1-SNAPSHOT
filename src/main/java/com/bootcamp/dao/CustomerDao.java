package com.bootcamp.dao;

import java.util.List;

import com.bootcamp.model.Customer;
import com.bootcamp.quickstart.CustomException;

public interface CustomerDao {

	Customer getById(int id) throws CustomException;
	Customer save(Customer customer) throws CustomException;
	void delete(Customer customer) throws CustomException;
	List<Customer> getList() throws CustomException;
}
