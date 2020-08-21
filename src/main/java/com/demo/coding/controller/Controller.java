package com.demo.coding.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.coding.model.BookingsModel;
import com.demo.coding.model.BusModel;
import com.demo.coding.model.BusSearchModel;
import com.demo.coding.service.BookingsService;
import com.demo.coding.service.BusSearchService;

@RestController
@RequestMapping("/buses/")
public class Controller {
	
	@Autowired
	private BusSearchService searchBus;
	
	@Autowired
	private BookingsService bookBus;
	
	
	@PostMapping("search")
	public Map getBusDetails(@Valid @RequestBody BusSearchModel busSearchModel)
	{
		
		return searchBus.searchBus(busSearchModel);
	}
	
	@PostMapping("routes/add")
	public Map addBusDetails(@Valid @RequestBody BusModel busModel)
	{
 		return searchBus.addNewRoute(busModel);
	}
	
	@PostMapping("book")
	public Map busBooking(@Valid @RequestBody BookingsModel bookings)
	{
		
		return bookBus.bookBus(bookings);
	}
	

}
