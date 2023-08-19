package com.example.demo.controller;

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

import com.example.demo.model.Court_status;
import com.example.demo.model.Manager;
import com.example.demo.repository.Court_statusRepository;

@RestController
public class Court_statusController {

	@Autowired
	Court_statusRepository court_statusRepository;
	
	@GetMapping("/court_status")
	public ResponseEntity<Object> getCourt_status(){
		
		try {
			List<Court_status> court_statuss = court_statusRepository.findAll();
			return new ResponseEntity<>(court_statuss, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/court_status")
	public ResponseEntity<Object> addCourt_status(@RequestBody Court_status body){
		
	try {
		
		
		Court_status court_status=  court_statusRepository.save(body);
		
		
		return new ResponseEntity<>(court_status, HttpStatus.CREATED);
		
		} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		}
	
	@PutMapping("/court_status/{status_id}")
	public  ResponseEntity<Object> updateCourt_status(@PathVariable Integer status_id,@RequestBody Court_status body) {
		
		try {
		Optional<Court_status> court_status = court_statusRepository.findById(status_id);
		
		if(court_status.isPresent()) {
			Court_status court_statusEdit = court_status.get();
			
			court_statusEdit.setStatus_name(body.getStatus_name());
			
			
			
			
			court_statusRepository.save(court_status.get());
			return new ResponseEntity<>(court_status, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>("Manager not found",HttpStatus.BAD_REQUEST);
		}}
		catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/court_status/{status_id}")
	public ResponseEntity<Object> Court_status(@PathVariable Integer status_id) {
		
		try {
		Optional<Court_status> court_status = court_statusRepository.findById(status_id);
		
		if(court_status.isPresent()) {
			court_statusRepository.delete(court_status.get());
			return new ResponseEntity<> ("Delete SUCESS",HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Court_status not found", HttpStatus.BAD_REQUEST);
		}
	}catch (Exception e) {
		return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	
	
}
