package com.alticart.coding.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alticart.coding.entity.BusEntity;
import com.alticart.coding.entity.BusSeatsEntity;
import com.alticart.coding.model.BusModel;
import com.alticart.coding.model.BusSearchModel;
import com.alticart.coding.repository.BusRepository;
import com.alticart.coding.repository.SeatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BusSearchService {

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private ObjectMapper objectMapper;
	
	public <F, T> T convertObj(F from, T to) {
		to = (T) objectMapper.convertValue(from, to.getClass());
		return to;
	}

	public Map searchBus(BusSearchModel busSearchModel) {
		Map responseMap = new LinkedHashMap<String, String>();
		List<BusEntity> returnBuses = new ArrayList<BusEntity>();
		List<BusEntity> onwardBuses = busRepository.findBySourceAndDestinationAndJouneydate(busSearchModel.getSource(),
				busSearchModel.getDestination(), busSearchModel.getOnwardJouneydate());
		if (busSearchModel.isRoundTrip()) {
			returnBuses = busRepository.findBySourceAndDestinationAndJouneydate(busSearchModel.getDestination(),
					busSearchModel.getSource(), busSearchModel.getReturnJouneydate());
		}

		responseMap.put("ResponseCode", "200");
		responseMap.put("ResponseMessage", "Success");
		responseMap.put("onwardBusesCount", onwardBuses.size());
		responseMap.put("returnBusesCount", returnBuses.size());
		responseMap.put("onwardBuses", onwardBuses);
		responseMap.put("returnBuses", returnBuses);

		return responseMap;
	}

	public Map addNewRoute(BusModel busModel) {
		Map responseMap = new LinkedHashMap<String, String>();
		BusEntity busEntity = new BusEntity();
		BusSeatsEntity busSeatEntity = new BusSeatsEntity();
		busEntity = convertObj(busModel, busEntity);
		busEntity = busRepository.save(busEntity);
		for (int i = 1; i <= busModel.getCapacity(); i++) {
			busSeatEntity.setBus(busEntity);
			busSeatEntity.setSeatid(busEntity.getBusnumber() + "-" + i);
			seatRepository.save(busSeatEntity);
		}
		responseMap.put("ResponseCode", "201");
		responseMap.put("ResponseMessage", "Successfully created a new route");
		responseMap.put("BusDetails", busEntity);
		return responseMap;
	}
}