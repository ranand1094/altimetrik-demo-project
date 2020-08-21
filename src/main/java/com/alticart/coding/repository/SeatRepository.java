package com.alticart.coding.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alticart.coding.entity.BusSeatsEntity;

@Repository
public interface SeatRepository extends JpaRepository<BusSeatsEntity, String> {

	@Query(value = "SELECT min(seatid) from BusSeatsEntity where busnumber = ?1 and status = 0")
	String minSeatid(int busid);
 
}
