package com.demo.coding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.coding.entity.BusSeatsEntity;

@Repository
public interface SeatRepository extends JpaRepository<BusSeatsEntity, String> {

	@Query(value = "SELECT min(seatid) from BusSeatsEntity where busnumber = ?1 and status = 0")
	String minSeatid(int busid);
 
}
