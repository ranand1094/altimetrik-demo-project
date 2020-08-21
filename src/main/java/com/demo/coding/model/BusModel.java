package com.demo.coding.model;

import java.sql.Time;
import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusModel {
	
	@NotBlank(message="Enter operatorname")
    @NotNull(message = "operatorname cannot be null")
	private String operatorname;
	
	@FutureOrPresent(message="jouneydate can be eithier today or future")
	private LocalDate jouneydate;
	
	@NotBlank(message="Enter source")
    @NotNull(message = "source cannot be null")
	private String source;
	
	@NotBlank(message="Enter destination")
    @NotNull(message = "destination cannot be null")
	private String destination;
	
	private Time departure;
	
	private Time arraival;
	
	@Positive(message="Value should be positive")
	private Double fare;
	
	@Positive(message="Value should be positive")
	private int capacity;

}
