package com.spring.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.shop.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{
	List<Vendor> findByCategory_Id(Long category_id);

}
