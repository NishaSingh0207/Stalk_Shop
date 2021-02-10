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
import com.spring.shop.repository.VendorRepository;

@RestController
@RequestMapping("/vendor")
public class VendorController {
	@Autowired
	private VendorRepository vendorRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("")
	public List<Vendor> getAllGroups() {
		return vendorRepository.findAll();
	}

	@PostMapping("")
	public Vendor addVendor(@RequestBody Vendor vendor) {

		return vendorRepository.save(vendor);
	}

	@GetMapping("/{id}")
	public Vendor getVendorStudentById(@PathVariable("id") Long id) {
		return vendorRepository.getOne(id);
	}

	@PutMapping("/{id}")
	public Vendor updateVendor(@PathVariable("id") Long id, @RequestBody Vendor vendorUpdated) {
		Vendor vendorDB = vendorRepository.getOne(id);
		vendorDB.setName(vendorUpdated.getName());

		// price cannot be updated so we did'nt set the price
		return vendorRepository.save(vendorDB);
	}

	@DeleteMapping("/{id}")
	public void deleteGroup(@PathVariable("id") Long id) {
		vendorRepository.deleteById(id);

	}
	@PutMapping("/category/{category_id}")
	public Vendor addCategoryToVendor(@PathVariable("category_id") Long category_id, @RequestBody Vendor vendor) {
		if (vendorRepository.findById(vendor.getId()).isPresent() && categoryRepository.findById(category_id).isPresent()) {
			Vendor vendorDB = vendorRepository.getOne(vendor.getId());
			List<Category> category=vendorDB.getCategory();
			category.add(categoryRepository.getOne(category_id));
			vendorDB.setCategory(category);
			return vendorRepository.save(vendorDB);
		} else
			return null;

	}

	@GetMapping("/category/{category_id}")
	public List<Vendor> getAllStudentsByCategoryId(@PathVariable("category_id") Long category_id) {
		if (categoryRepository.findById(category_id).isPresent()) {
			return vendorRepository.findByCategory_Id(category_id);
			
		} else
			return null;
	}
}
