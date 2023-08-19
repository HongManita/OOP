package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ReserveRepository;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository customerRepositoty;
	
	@Autowired
	ReserveRepository reserveRepository;
	
	private List<Customer> data = new ArrayList<Customer>();
	
	
	@GetMapping("/customer")
	public ResponseEntity<Object> getCustomer(){
		
		try {
			List<Customer> ecustomers = customerRepositoty.findAll();
			return new ResponseEntity<>(ecustomers, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.OK);
		}
	}
	
	
	
	@PostMapping("/customer")
	public ResponseEntity<Object> addCustomer(@RequestBody Customer body){
		
	try {
		
		
		Customer customer=  customerRepositoty.save(body);
		
		
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
		
		} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		}
	
	
	
	
}
