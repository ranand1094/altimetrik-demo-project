package com.alticart.coding.model;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

 
@Getter
@Setter

public class BusSearchModel {
	
	
	@NotBlank(message="Enter source")
    @NotNull(message = "Source cannot be null")
	private String source;
	
	@NotBlank(message="Enter destination")
    @NotNull(message = "Destination cannot be null")
	private String destination;
	
 	@FutureOrPresent(message="onwardJouneydate can be eithier today or future")
 	private LocalDate onwardJouneydate;
	
	@FutureOrPresent(message="returnJouneydate can be eithier today or future")
 	private LocalDate returnJouneydate;
	
	private boolean roundTrip;

	
}
