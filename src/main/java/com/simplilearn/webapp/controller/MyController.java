package com.simplilearn.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.webapp.entity.Customer;

@RestController
public class MyController {
	List<Customer> customers =  new ArrayList<Customer>();
	
	@RequestMapping(value="/")
	public String homepage() {
		return "Welcome to Customer Management System";
		
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public List<Customer> getProducts() {
		if (customers.isEmpty()) {
			addDefaultData();
		}
		return customers;
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public Customer getProduct(@PathVariable("id") int id) {
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public Customer getProduct(@RequestParam("name") String name) {
		for (Customer customer : customers) {
			if (customer.getName().equals(name)) {
				return customer;
			}
		}
		return null;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Customer searchProduct(@RequestParam("name") String name) {
		for (Customer customer: customers) {
			if (customer.getName().contains(name)) {
				return customer;
			}
		}
		return null;
	}

	// add product
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public List<Customer> addProduct(@RequestBody Customer customer) {
		customers.add(customer);
		return customers;
	}

	// update product
	@RequestMapping(value = "/customers", method = RequestMethod.PUT)
	public Customer updateProduct(@RequestBody Customer customer) {
		for (int index = 0; index < customers.size(); index++) {
			if(customers.get(index).getId()== customer.getId()) {
				customers.set(index, customer);
				return customer;
			}
		}
		return null;
	}
	
	// delete product
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
	public List<Customer> deleteProduct(@PathVariable("id") int id) {
		for (Customer customer1 : customers) {
			if( (customer1.getId()  ==  id )) {
				customers.remove(customer1);
				return customers;
			}
		}
		
		return null;
	}
	
	//add default data when list is empty
	private void addDefaultData() {
		customers.add(new Customer(101,"sneha","sneha@gmail.com","9998978900"));
		customers.add(new Customer(102,"mahesh","mahesh@gmail.com","9998978902"));
		customers.add(new Customer(103,"madhavi","madhavi@gmail.com","9998978903"));
		customers.add(new Customer(104,"gopi","gopi@gmail.com","9998978904"));
	}
}
