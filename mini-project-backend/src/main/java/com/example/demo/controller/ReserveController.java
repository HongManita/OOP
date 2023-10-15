package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Court;
import com.example.demo.model.Customer;
import com.example.demo.model.Manager;
import com.example.demo.model.Reserve;
import com.example.demo.repository.CourtRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ReserveRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReserveController {

	@Autowired
	ReserveRepository reserveRepository;

	@Autowired
	CourtRepository courtRepository;

	@Autowired
	CustomerRepository customerRepository;

	private List<Reserve> data = new ArrayList<Reserve>();

	@GetMapping("/reserve")
	public ResponseEntity<Object> getReserve() {

		try {
			List<Reserve> reserves = reserveRepository.findAll();
			return new ResponseEntity<>(reserves, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.OK);
		}
	}

	@PostMapping("/reserve")
	public ResponseEntity<Object> addReserve(@RequestBody Reserve body) {

		try {

			Reserve reserve = reserveRepository.save(body);

			return new ResponseEntity<>(reserve, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/reserve/{reserve_id}")
	public ResponseEntity<Object> updateRserve(@PathVariable Integer reserve_id, @RequestBody Reserve body) {

		try {
			Optional<Reserve> reserve = reserveRepository.findById(reserve_id);

			if (reserve.isPresent()) {
				Reserve reserveEdit = reserve.get();
				reserveEdit.setDate(body.getDate());
				reserveEdit.setCustomer(body.getCustomer());
				reserveEdit.setCourt(body.getCourt());

				reserveRepository.save(reserve.get());
				return new ResponseEntity<>(reserve, HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Reserver not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("reserve/{reserve_id}")
	public ResponseEntity<Object> deleteManager(@PathVariable Integer reserve_id) {

		try {
			Optional<Reserve> reserve = reserveRepository.findById(reserve_id);

			if (reserve.isPresent()) {
				reserveRepository.delete(reserve.get());
				return new ResponseEntity<>("Delete SUCESS", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Reserve not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/reserves/user/{userId}")
	public ResponseEntity<Object> getReservesByUserId(@PathVariable int userId) {
		try {
			List<Object[]> reserves = reserveRepository.findReserveByUserId(userId);
			if (reserves.isEmpty()) {
				return new ResponseEntity<>("No reservations found for the user ID: " + userId, HttpStatus.NOT_FOUND);
			} else {
				List<Reserve> reservesNew = new ArrayList<>();

				for (Object[] row : reserves) {
					Integer reserve_id = (Integer) row[0];
					LocalDate date = (LocalDate) row[1];
					Court court = (Court) row[2];
					Customer customer = (Customer) row[3];

					Reserve reserve = new Reserve(reserve_id, date, court, customer);

					reservesNew.add(reserve);
				}
				return new ResponseEntity<>(reservesNew, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
