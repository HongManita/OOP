package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ReserveRepository;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ReserveRepository reserveRepository;
	
	private List<Customer> data = new ArrayList<Customer>();
	
	
	
	
	@PostMapping("/loginCustomer")
	public ResponseEntity<Object> loginCustomer(@RequestBody Customer loginRequest) {
		try {

			Optional<Customer> customerFound = customerRepository.findByUsername(loginRequest.getUsername());
			if (customerFound.isPresent() && customerFound.get().getPassword().equals(loginRequest.getPassword())) {
				customerFound.get().setPassword(null);
				return new ResponseEntity<>(customerFound, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid credentials.", HttpStatus.UNAUTHORIZED);
			}
		}catch(Exception e) {
					System.out.println(e.getMessage());
					return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	@GetMapping("/customer")
	public ResponseEntity<Object> getCustomer(){
		
		try {
			List<Customer> customers = customerRepository.findAll();
			return new ResponseEntity<>(customers, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.OK);
		}
	}
	
	
	
	@PostMapping("/customer")
	public ResponseEntity<Object> addCustomer(@RequestBody Customer body){
		
	try {
		
		
		Customer customer=  customerRepository.save(body);
		
		
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
		
		} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		}
	
	
	@GetMapping("/customer/{user_id}")
	public  ResponseEntity<Object> CustomerDetail(@PathVariable Integer user_id) {
		
		
		try {
			Optional<Customer> customer = customerRepository.findById(user_id);
					if(customer.isPresent()) {
						return new ResponseEntity<>(customer, HttpStatus.OK);
					}else {
						return new ResponseEntity<>("Customer not found",HttpStatus.BAD_REQUEST);
					}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("customer/{user_id}")
	public  ResponseEntity<Object> updateCustomer(@PathVariable Integer user_id,@RequestBody Customer body) {
		
		try {
		Optional<Customer> customer = customerRepository.findById(user_id);
		
		if(customer.isPresent()) {
			Customer customerEdit = customer.get();
			customerEdit.setFirstName(body.getFirstName());
			customerEdit.setLastName(body.getLastName());
			customerEdit.setTel(body.getTel());
			customerEdit.setUsername(body.getUsername());
			customerEdit.setPassword(body.getPassword());
			
			
			
			customerRepository.save(customer.get());
			return new ResponseEntity<>(customer, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>("Customer not found",HttpStatus.BAD_REQUEST);
		}}
		catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("customer/{user_id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable Integer user_id) {
		
		try {
		Optional<Customer> customer = customerRepository.findById(user_id);
		
		if(customer.isPresent()) {
			customerRepository.delete(customer.get());
			return new ResponseEntity<> ("Delete SUCESS",HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
		}
	}catch (Exception e) {
		return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	
}
