package com.demo.coding.model;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookingsModel {


	@Positive(message="Value should be positive")
	private int busnumber;
	

	@FutureOrPresent(message="jouneydate can be eithier today or future")
	private LocalDate journeyDate;
	

    @NotNull(message = "seatnumber cannot be null")
	private String seatnumber;
	

	@NotBlank(message="Enter travelerFirstName")
    @NotNull(message = "travelerFirstName cannot be null")
	private String travelerFirstName;
	

    @NotNull(message = "travelerMiddleName cannot be null")
	private String travelerMiddleName;

	@NotBlank(message="Enter travelerLastName")
    @NotNull(message = "travelerLastName cannot be null")
	private String travelerLastName;
	

	@NotBlank(message="Enter travelerGender")
    @NotNull(message = "travelerGender cannot be null")
	private String travelerGender;
	
}
