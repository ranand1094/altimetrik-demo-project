package com.demo.coding.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bus")
@Getter
@Setter
public class BusEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int busnumber;
	private String operatorname;
	private LocalDate jouneydate;
	private String source;
	private String destination;
	private Time departure;
	private Time arraival;
	private Double fare;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="busnumber",referencedColumnName = "busnumber")
	List<BusSeatsEntity> seatStatus;
	
}
