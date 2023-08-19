package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer m_id;

	
	private String m_firstName;
	private String m_lastName;
	private String m_username;
	private String m_password;
	
	public Manager() {
		super();
		
	}

	public Manager(String m_firstName, String m_lastName, String m_username, String m_password) {
		super();
		this.m_firstName = m_firstName;
		this.m_lastName = m_lastName;
		this.m_username = m_username;
		this.m_password = m_password;
	}

	public Integer getM_id() {
		return m_id;
	}

	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}

	public String getM_firstName() {
		return m_firstName;
	}

	public void setM_firstName(String m_firstName) {
		this.m_firstName = m_firstName;
	}

	public String getM_lastName() {
		return m_lastName;
	}

	public void setM_lastName(String m_lastName) {
		this.m_lastName = m_lastName;
	}

	public String getM_username() {
		return m_username;
	}

	public void setM_username(String m_username) {
		this.m_username = m_username;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	
	

	
	
	
}