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

import com.spring.shop.model.Customer;
import com.spring.shop.model.Products;
import com.spring.shop.repository.CustomerRepository;
import com.spring.shop.repository.ProductsRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductsRepository productsRepository;
	
	@GetMapping("")
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}
	
	@PostMapping("")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable("id") Long id) {
		return customerRepository.getOne(id);
	}
	
	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customerUpdated) {
		Customer studentDB = customerRepository.getOne(id);
		studentDB.setName(customerUpdated.getName());
		studentDB.setPhoneno(customerUpdated.getPhoneno());
		studentDB.setAddress(customerUpdated.getAddress());
		studentDB.setEmailid(customerUpdated.getEmailid());
		return customerRepository.save(studentDB);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable("id") Long id) {
		customerRepository.deleteById(id);
	}

	@PutMapping("/products/{products_id}")
	public Customer addingProductsToCustomer(@PathVariable("products_id") Long products_id, @RequestBody Customer customer) {
		if(customerRepository.findById(customer.getId()).isPresent() && productsRepository.findById(products_id).isPresent()) {
			Customer customerDB = customerRepository.getOne(customer.getId());
			List<Products> products = customerDB.getProducts();
			products.add(productsRepository.getOne(products_id));
			customerDB.setProducts(products);
			return customerRepository.save(customerDB);
		} else
			return null;
	}
	@GetMapping("/products/{products_id}")
	public List<Customer> getAllProductsByProductId(@PathVariable("products_id") Long products_id) {
		if (productsRepository.findById(products_id).isPresent()) {
			return customerRepository.findByProducts_Id(products_id);
		} else
			return null;
	}
}
