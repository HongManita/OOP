package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "court_status")
public class Court_status {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Status_id;
	
	private Integer Status_name;

	public Court_status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Court_status(Integer status_id, Integer status_name) {
		super();
		Status_id = status_id;
		Status_name = status_name;
	}

	public Integer getStatus_id() {
		return Status_id;
	}

	public void setStatus_id(Integer status_id) {
		Status_id = status_id;
	}

	public Integer getStatus_name() {
		return Status_name;
	}

	public void setStatus_name(Integer status_name) {
		Status_name = status_name;
	}

	
	
	
	
	
}
