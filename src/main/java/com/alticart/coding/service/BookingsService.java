package com.alticart.coding.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alticart.coding.entity.BookingsEntity;
import com.alticart.coding.entity.BusEntity;
import com.alticart.coding.entity.BusSeatsEntity;
import com.alticart.coding.model.BookingsModel;
import com.alticart.coding.repository.BookingRepository;
import com.alticart.coding.repository.BusRepository;
import com.alticart.coding.repository.SeatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookingsService {

	@Autowired
	private BookingRepository bookRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private ObjectMapper objectMapper;// = new ObjectMapper();

	public <F, T> T convertObj(F from, T to) {
		to = (T) objectMapper.convertValue(from, to.getClass());
		return to;
	}

	public Map bookBus(BookingsModel bookings) {
		
		Map responseMap = new LinkedHashMap<String, String>();
		boolean autoSeatSelection = false;
		BookingsEntity busBooking = new BookingsEntity();
		BusSeatsEntity busSeatsEntity = new BusSeatsEntity();
		BusEntity bus = new BusEntity();

		Optional<BusEntity> validBus = busRepository.findById(bookings.getBusnumber());

		if (validBus.isPresent()) {
			String seatNumber = bookings.getSeatnumber();
			if (seatNumber.trim().length() == 0) {
				autoSeatSelection = true;
				System.out.println(seatRepository.minSeatid(bookings.getBusnumber()));
				bookings.setSeatnumber(seatRepository.minSeatid(bookings.getBusnumber()));
			}
			Optional<BusSeatsEntity> validSeat = seatRepository.findById(bookings.getSeatnumber());
			if (validSeat.isPresent()) {
				busSeatsEntity = validSeat.get();
				if (busSeatsEntity.getStatus() == 0) {
					busBooking = convertObj(bookings, busBooking);
					bus = validBus.get();
					busBooking.setBus(bus);
					Double fare = bus.getFare();

					if (!autoSeatSelection) {
						fare = fare + ((fare * 10) / 100);
					}
					
					busBooking.setAmount(fare);
					bookRepository.save(busBooking);

					busSeatsEntity.setSeatid(busBooking.getSeatnumber());
					busSeatsEntity.setStatus(1);// 1 -> represents as booked
					seatRepository.save(busSeatsEntity);

					responseMap.put("ResponseCode", "201");
					responseMap.put("ResponseMessage", "Booking Successful");
					responseMap.put("BookingDetails", busBooking);
				} else {
					responseMap.put("ResponseCode", "406");
					responseMap.put("ResponseMessage", "Selected Seat is already booked");
				}
			} else {
				responseMap.put("ResponseCode", "404");
				responseMap.put("ResponseMessage", "No such seat found for the given bus");
			}
		} else {
			responseMap.put("ResponseCode", "404");
			responseMap.put("ResponseMessage", "No such bus found");
		}
		return responseMap;
	}
}
