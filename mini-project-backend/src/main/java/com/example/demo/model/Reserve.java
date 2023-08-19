package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserve")
public class Reserve {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reserve_id;
	
	
	 private LocalDate date; 
	 private LocalTime time;
	 
	 
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private Customer customer;


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@ManyToOne
    @JoinColumn(name = "court_id")
	private Court court;



	public void setCourt(Court court) {
		this.court = court;
	}

	
	

	


	

	
	
	
	
}
