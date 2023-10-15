package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reserve;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
    @Query("SELECT r.reserve_id, r.date, r.court, r.customer FROM Reserve r WHERE r.customer.user_id = :userID")
    List<Object[]> findReserveByUserId(@Param("userID") Integer userID);
}
