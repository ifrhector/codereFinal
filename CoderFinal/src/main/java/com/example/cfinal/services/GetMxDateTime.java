package com.example.cfinal.services;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.cfinal.interfaces.IMxDate;
import com.example.cfinal.vo.TimeZoneVo;

@Service
public class GetMxDateTime implements IMxDate {

	//This string enable location for get DateTime from server
	private final String URL = "http://api.timezonedb.com/v2.1/get-time-zone?key=KOQS3ULCJUI5&format=json&by=position&lat=19.42847&lng=-99.12766";

	private RestTemplate restTemplate;

	@Override
	public TimeZoneVo getTimeZone() {
		try {
			
			restTemplate = new RestTemplate();
			
			ResponseEntity<TimeZoneVo> response = restTemplate.exchange(URL, HttpMethod.GET, null, TimeZoneVo.class);

			return response.getBody();

		} catch (Exception e) {
			throw new RuntimeException("Error al generar la fecha y hora: " + e.getMessage());
		}
	}

}
