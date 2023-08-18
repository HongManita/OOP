package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer employeeId;
	private String firstName;
	private String lastName;
	private Integer sarary;
	}