package com.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.bootcamp.dao.TransactionDao;
import com.bootcamp.dao.repository.TransactionRepository;
import com.bootcamp.model.Account;
import com.bootcamp.model.Transaction;
import com.bootcamp.quickstart.CustomException;

public class TransactionDaoImpl extends BaseImpl implements TransactionDao {

	@Autowired
	private TransactionRepository repository;
	
	@Override
	public Transaction getById(String id) throws CustomException {
		return repository.findOne(Integer.valueOf(id));
	}

	@Override
	public Transaction save(Transaction transaction) throws CustomException {
		return repository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) throws CustomException {
		repository.delete(transaction);		
	}

	@Override
	public List<Transaction> getList() throws CustomException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = critB.createQuery(Transaction.class);
		query.from(Transaction.class);
		
		TypedQuery<Transaction> q = em.createQuery(query);
		return q.getResultList();
	}
	
	@Override
	public List<Transaction> getListByAccount(Account account) throws CustomException{
		return repository.findByAccount(account);
	}

}
