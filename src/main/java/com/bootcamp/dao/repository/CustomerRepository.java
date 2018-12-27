package com.bootcamp.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bootcamp.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
	Customer findByCustomernumber(int customernumber);
}
