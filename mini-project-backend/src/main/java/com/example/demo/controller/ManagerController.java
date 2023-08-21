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
import com.example.demo.model.Manager;
import com.example.demo.repository.ManagerRepository;



@RestController
public class ManagerController {

	@Autowired
	ManagerRepository managerRepository;
	
	
	private List<Manager> data = new ArrayList<Manager>();
	
	
	@PostMapping("/loginManager")
	public ResponseEntity<Object> loginManager(@RequestBody Manager loginRequest) {
		try {

			Optional<Manager> managerFound = managerRepository.findByUsernameContainingIgnoreCase(loginRequest.getM_username());
			if (managerFound.isPresent() && managerFound.get().getM_password().equals(loginRequest.getM_password())) {
				managerFound.get().setM_password(null);
				return new ResponseEntity<>(managerFound, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid credentials.", HttpStatus.UNAUTHORIZED);
			}
		}catch(Exception e) {
					System.out.println(e.getMessage());
					return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	@GetMapping("/manager")
	public ResponseEntity<Object> getManager(){
		
		try {
			List<Manager> managers = managerRepository.findAll();
			return new ResponseEntity<>(managers, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.OK);
		}
	}
	
	
	
	
	@PostMapping("/manager")
	public ResponseEntity<Object> addManager(@RequestBody Manager body){
		
	try {
		
		
		Manager manager=  managerRepository.save(body);
		
		
		return new ResponseEntity<>(manager, HttpStatus.CREATED);
		
		} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		}
	
	
	@GetMapping("/manager/{m_id}")
	public  ResponseEntity<Object> ManagerDetail(@PathVariable Integer m_id) {
		
		
		try {
			Optional<Manager> manager = managerRepository.findById(m_id);
					if(manager.isPresent()) {
						return new ResponseEntity<>(manager, HttpStatus.OK);
					}else {
						return new ResponseEntity<>("Manager not found",HttpStatus.BAD_REQUEST);
					}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/manager/{m_id}")
	public  ResponseEntity<Object> updateManager(@PathVariable Integer m_id,@RequestBody Manager body) {
		
		try {
		Optional<Manager> manager = managerRepository.findById(m_id);
		
		if(manager.isPresent()) {
			Manager managerEdit = manager.get();
			managerEdit.setM_firstName(body.getM_firstName());
			managerEdit.setM_lastName(body.getM_lastName());
			managerEdit.setM_username(body.getM_username());
			managerEdit.setM_password(body.getM_password());
			
			
			
			managerRepository.save(manager.get());
			return new ResponseEntity<>(manager, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>("Manager not found",HttpStatus.BAD_REQUEST);
		}}
		catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("manager/{m_id}")
	public ResponseEntity<Object> deleteManager(@PathVariable Integer m_id) {
		
		try {
		Optional<Manager> manager = managerRepository.findById(m_id);
		
		if(manager.isPresent()) {
			managerRepository.delete(manager.get());
			return new ResponseEntity<> ("Delete SUCESS",HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Manager not found", HttpStatus.BAD_REQUEST);
		}
	}catch (Exception e) {
		return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	
	
}
