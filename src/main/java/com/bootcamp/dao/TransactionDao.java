package com.bootcamp.dao;

import java.util.List;

import com.bootcamp.model.Account;
import com.bootcamp.model.Transaction;
import com.bootcamp.quickstart.CustomException;

public interface TransactionDao {

	Transaction getById(int id) throws CustomException;
	Transaction save(Transaction transaction) throws CustomException;
	void delete(Transaction transaction) throws CustomException;
	List<Transaction> getList() throws CustomException;
	List<Transaction> getListByAccount(Account account) throws CustomException;
}
