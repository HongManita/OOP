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

	@ManyToOne
	@JoinColumn(name = "court_id")
	private Court court;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customer;

	public Reserve() {
		super();
	}

	public Reserve(Integer reserve_id, LocalDate date, Court court, Customer customer) {
		super();
		this.reserve_id = reserve_id;
		this.date = date;
		this.court = court;
		this.customer = customer;
	}

	public Integer getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(Integer reserve_id) {
		this.reserve_id = reserve_id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
