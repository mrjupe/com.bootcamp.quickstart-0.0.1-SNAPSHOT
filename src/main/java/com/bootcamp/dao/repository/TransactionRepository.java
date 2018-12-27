package com.bootcamp.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bootcamp.model.Account;
import com.bootcamp.model.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {
	Transaction findById(int id);
	List<Transaction> findByAccount(Account account);
}
