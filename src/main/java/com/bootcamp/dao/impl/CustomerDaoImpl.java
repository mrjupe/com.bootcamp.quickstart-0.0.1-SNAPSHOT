package com.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.bootcamp.dao.CustomerDao;
import com.bootcamp.dao.repository.CustomerRepository;
import com.bootcamp.model.Customer;
import com.bootcamp.quickstart.CustomException;

public class CustomerDaoImpl extends BaseImpl implements CustomerDao {

	@Autowired
	private CustomerRepository repository;
	@Override
	public Customer getById(int id) throws CustomException {
		return repository.findOne(id);
	}

	@Override
	public Customer save(Customer customer) throws CustomException {
		return repository.save(customer);
	}

	@Override
	public void delete(Customer customer) throws CustomException {
		repository.delete(customer);
	}

	@Override
	public List<Customer> getList() throws CustomException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = critB.createQuery(Customer.class);
		query.from(Customer.class);
		
		TypedQuery<Customer> q = em.createQuery(query);
		return q.getResultList();
	}

}
