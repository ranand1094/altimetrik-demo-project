package com.alticart.coding.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alticart.coding.entity.BusEntity;

@Repository
public interface BusRepository extends JpaRepository<BusEntity, Integer> {
	
  	List<BusEntity> findBySourceAndDestinationAndJouneydate(String source,String destination,LocalDate jouneydate);
}
