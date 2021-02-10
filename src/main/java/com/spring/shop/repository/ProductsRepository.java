package com.spring.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.shop.model.Customer;
import com.spring.shop.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long>{
	List<Products> findByVendor_Id(Long vendor_id);

}
