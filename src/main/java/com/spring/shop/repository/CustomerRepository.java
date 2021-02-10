package com.spring.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.shop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	List<Customer> findByProducts_Id(Long products_id);

}
