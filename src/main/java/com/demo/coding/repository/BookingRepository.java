package com.demo.coding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.coding.entity.BookingsEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingsEntity, Integer> {

}
