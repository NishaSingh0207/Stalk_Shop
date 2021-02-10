package com.spring.shop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String mensFashion;
	private String womenFashion;
	private String kidsFashion;
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<Products> products;
	@ManyToMany(mappedBy = "category")
	@JsonIgnore
	private List<Vendor> vendor;
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}
	
	public List<Vendor> getVendor() {
		return vendor;
	}
	public void setVendor(List<Vendor> vendor) {
		this.vendor = vendor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMensFashion() {
		return mensFashion;
	}
	public void setMensFashion(String mensFashion) {
		this.mensFashion = mensFashion;
	}
	public String getWomenFashion() {
		return womenFashion;
	}
	public void setWomenFashion(String womenFashion) {
		this.womenFashion = womenFashion;
	}
	public String getKidsFashion() {
		return kidsFashion;
	}
	public void setKidsFashion(String kidsFashion) {
		this.kidsFashion = kidsFashion;
	}
	
}