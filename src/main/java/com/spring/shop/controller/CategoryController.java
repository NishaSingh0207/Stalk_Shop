package com.spring.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.model.Category;
import com.spring.shop.model.Vendor;
import com.spring.shop.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	@GetMapping("")
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@PostMapping("")
	public Category addCategory(@RequestBody Category category) {

		return categoryRepository.save(category);
	}

	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable("id") Long id) {
		return categoryRepository.getOne(id);
	}

	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable("id") Long id, @RequestBody Category categoryUpdated) {
		Category categoryDB = categoryRepository.getOne(id);
		categoryDB.setMensFashion(categoryUpdated.getMensFashion());
		categoryDB.setWomenFashion(categoryUpdated.getWomenFashion());
		categoryDB.setKidsFashion(categoryUpdated.getKidsFashion());

		// price cannot be updated so we did'nt set the price
		return categoryRepository.save(categoryDB);
	}

	@DeleteMapping("/{id}")
	public void deleteGroup(@PathVariable("id") Long id) {
		categoryRepository.deleteById(id);

	}

}
