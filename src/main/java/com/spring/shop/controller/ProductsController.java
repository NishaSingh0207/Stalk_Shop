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

import com.spring.shop.model.Products;
import com.spring.shop.model.Vendor;
import com.spring.shop.repository.CustomerRepository;
import com.spring.shop.repository.ProductsRepository;
import com.spring.shop.repository.VendorRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private VendorRepository vendorRepository;
	@GetMapping("")
	public List<Products> getAllProducts() {
		return productsRepository.findAll();
	}

	@PostMapping("")
	public Products addProducts(@RequestBody Products products) {

		return productsRepository.save(products);
	}

	@GetMapping("/{id}")
	public Products getProductsById(@PathVariable("id") Long id) {
		return productsRepository.getOne(id);
	}

	@PutMapping("/{id}")
	public Products updateProducts(@PathVariable("id") Long id, @RequestBody Products productsUpdated) {
		Products productstDB = productsRepository.getOne(id);
		productstDB.setPrice(productsUpdated.getPrice());

		// price cannot be updated so we did'nt set the price
		return productsRepository.save(productstDB);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {
		productsRepository.deleteById(id);

	}
	@PutMapping("/vendor/{vendor_id}")
	public Products addVendorToProducts(@PathVariable("vendor_id") Long vendor_id, @RequestBody Products products) {
		if (productsRepository.findById(products.getId()).isPresent() && vendorRepository.findById(vendor_id).isPresent()) {
			Products productsDB = productsRepository.getOne(products.getId());
			List<Vendor> vendor=productsDB.getVendor();
			vendor.add(vendorRepository.getOne(vendor_id));
			productsDB.setVendor(vendor);
			return productsRepository.save(productsDB);
		} else
			return null;

	}

	@GetMapping("/vendor/{vendor_id}")
	public List<Products> getAllProductsByVendorId(@PathVariable("vendor_id") Long vendor_id) {
		if (vendorRepository.findById(vendor_id).isPresent()) {
			return productsRepository.findByVendor_Id(vendor_id);
			
		} else
			return null;
	}
}
