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

import com.example.demo.model.Court;
import com.example.demo.model.Court_status;
import com.example.demo.model.Customer;
import com.example.demo.repository.CourtRepository;



@RestController
public class CourtController {

	@Autowired
	CourtRepository courtRepository;
	
	private List<Court> data = new ArrayList<Court>();
	
	@GetMapping("/court")
	public ResponseEntity<Object> getCourt(){
		
		try {
			List<Court> courts = courtRepository.findAll();
			return new ResponseEntity<>(courts, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.OK);
		}
	}
	
	
	
	@PostMapping("/court")
	public ResponseEntity<Object> addCourt(@RequestBody Court body){
		
	try {
		
		
		Court court =  courtRepository.save(body);
		
		
		return new ResponseEntity<>(court, HttpStatus.CREATED);
		
		} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		}
	
	
	@GetMapping("/court/{court_id}")
	public  ResponseEntity<Object> CourtDetail(@PathVariable Integer court_id) {
		
		
		try {
			Optional<Court> court = courtRepository.findById(court_id);
					if(court.isPresent()) {
						return new ResponseEntity<>(court, HttpStatus.OK);
					}else {
						return new ResponseEntity<>("Court not found",HttpStatus.BAD_REQUEST);
					}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	@PutMapping("/court/{court_id}")
	public  ResponseEntity<Object> updateCourt(@PathVariable Integer court_id,@RequestBody Court body) {
		
		try {
		Optional<Court> court = courtRepository.findById(court_id);
		
		if(court.isPresent()) {
			Court courtEdit = court.get();
			courtEdit.setCourt_name(body.getCourt_name());
			courtEdit.setCourt_status(body.getCourt_status());
			
			
			
			
			courtRepository.save(court.get());
			return new ResponseEntity<>(court, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>("Court not found",HttpStatus.BAD_REQUEST);
		}}
		catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/court/{court_id}")
	public ResponseEntity<Object> Court(@PathVariable Integer court_id) {
		
		try {
		Optional<Court> court = courtRepository.findById(court_id);
		
		if(court.isPresent()) {
			courtRepository.delete(court.get());
			return new ResponseEntity<> ("Delete SUCESS",HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Court not found", HttpStatus.BAD_REQUEST);
		}
	}catch (Exception e) {
		return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	
	
}
