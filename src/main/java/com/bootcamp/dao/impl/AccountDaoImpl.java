package com.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.bootcamp.dao.AccountDao;
import com.bootcamp.dao.repository.AccountRepository;
import com.bootcamp.model.Account;
import com.bootcamp.model.Customer;
import com.bootcamp.quickstart.CustomException;

public class AccountDaoImpl extends BaseImpl implements AccountDao {

	@Autowired
	private AccountRepository repository;
	
	@Override
	public Account getById(int id) throws CustomException {
		return repository.findOne(Integer.valueOf(id));
	}

	@Override
	public Account save(Account account) throws CustomException {
		return repository.save(account);
	}

	@Override
	public void delete(Account account) throws CustomException {
		repository.delete(account);
	}

	@Override
	public List<Account> getList() throws CustomException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Account> query = critB.createQuery(Account.class);
		query.from(Account.class);
		
		TypedQuery<Account> q = em.createQuery(query);
		return q.getResultList();
	}
	
	@Override
	public List<Account> getListByCustomer(Customer customer) throws CustomException{
		return repository.findByCustomer(customer);
	}
	
}
