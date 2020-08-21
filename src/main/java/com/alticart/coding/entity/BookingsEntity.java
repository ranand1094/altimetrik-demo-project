package com.alticart.coding.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class BookingsEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingid;
	private LocalDate journeyDate;
	private Double amount;

	private String travelerFirstName;
	private String travelerMiddleName;
	private String travelerLastName;
	private String travelerGender;
	private String seatnumber;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "busnumber",referencedColumnName = "busnumber")
	@JsonIgnore
	private BusEntity bus;
	
	 
	
	
}
