package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Manager;



@Repository
public interface ManagerRepository extends JpaRepository <Manager, Integer>{

	@Query("SELECT m FROM Manager m WHERE LOWER(m.m_username) LIKE LOWER(CONCAT('%', :m_username, '%'))")
     Optional<Manager> findByUsernameContainingIgnoreCase(@Param("m_username") String m_username);
}
