package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.shop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
