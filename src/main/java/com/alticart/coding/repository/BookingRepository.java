package com.alticart.coding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alticart.coding.entity.BookingsEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingsEntity, Integer> {

}
