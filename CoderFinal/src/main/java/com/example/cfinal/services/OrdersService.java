package com.example.cfinal.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cfinal.interfaces.IOrders;
import com.example.cfinal.models.CatOrderStatus;
import com.example.cfinal.models.ClientOrdersModel;
import com.example.cfinal.repositories.IClientOrderRepository;
import com.example.cfinal.repositories.IOrderStatus;
import com.example.cfinal.utils.StringUtils;
import com.example.cfinal.vo.TimeZoneVo;

@Service
public class OrdersService implements IOrders {

	@Autowired
	IClientOrderRepository ordersRepository;
	
	@Autowired
	IOrderStatus orderStatusRepo;

	@Autowired
	GetMxDateTime dateTime;

	private HttpStatus hs = null;

	@Override
	public ResponseEntity<ClientOrdersModel> addNew(ClientOrdersModel order) {

		StringUtils su = new StringUtils();

		ClientOrdersModel response = null;

		// This is an external API to get data from TimeZone of any place of world
		// "api.timezonedb.com"
		TimeZoneVo timeZone = dateTime.getTimeZone();

		if (order != null) {

			String identifier = su.getKey();

			order.setOrderIdentifier(identifier);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(timeZone.getFormatted(), formatter);

			order.setMakeDateOrder(dateTime);
			order.setUpdateDateOrder(dateTime);
			order.setCanceledDateOrder(null);
			order.setTotal(0);

			response = ordersRepository.save(order);
			hs = HttpStatus.ACCEPTED;
		} else {
			hs = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<Optional<ClientOrdersModel>> getById(Long id) {

		Optional<ClientOrdersModel> response = null;

		if (ordersRepository.existsById(id)) {
			response = ordersRepository.findById(id);
			hs = HttpStatus.OK;
		} else {
			hs = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<List<ClientOrdersModel>> getAll() {

		List<ClientOrdersModel> response = ordersRepository.findAll();

		if (response.isEmpty()) {
			hs = HttpStatus.NO_CONTENT;
		} else {
			hs = HttpStatus.OK;
		}

		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<List<ClientOrdersModel>> getAllByClientId(Long id) {

		List<ClientOrdersModel> response = ordersRepository.getOrdersByClientId(id);

		if (response.isEmpty()) {
			hs = HttpStatus.NO_CONTENT;
		} else {
			hs = HttpStatus.OK;
		}

		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<ClientOrdersModel> update(Long id, ClientOrdersModel order) {

		ClientOrdersModel response = null;

		// This is an external API to get data from TimeZone of any place of world
		// "api.timezonedb.com"
		TimeZoneVo timeZone = dateTime.getTimeZone();

		if (ordersRepository.existsById(id)) {

			Optional<ClientOrdersModel> orderValidation = ordersRepository.findById(id);

			// Return conflict if status of order is canceled because this status not admit
			// more modifications
			if (orderValidation.get().getStatusId().getId() == 2) {
				response = orderValidation.get();
				hs = HttpStatus.CONFLICT;
			} else {
				order.setId(id);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(timeZone.getFormatted(), formatter);

				order.setUpdateDateOrder(dateTime);

				// If order is canceled then save date
				// Require final notes
				if (order.getStatusId().getId() == 2) {
					order.setCanceledDateOrder(dateTime);
				}

				response = ordersRepository.save(order);
				hs = HttpStatus.OK;
			}
		} else {
			hs = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(response, hs);
	}

	//Can´t delete order, only change to canceled.
	@Override
	public ResponseEntity<Optional<ClientOrdersModel>> delete(Long id) {

		// This is an external API to get data from TimeZone of any place of world
		// "api.timezonedb.com"
		TimeZoneVo timeZone = dateTime.getTimeZone();

		if (ordersRepository.existsById(id)) {

			Optional<ClientOrdersModel> order = ordersRepository.findById(id);
			
			//Get status canceled
			Optional<CatOrderStatus> oss = orderStatusRepo.findById((long) 2);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(timeZone.getFormatted(), formatter);
			
			order.get().setCanceledDateOrder(dateTime);
			order.get().setStatusId(oss.get());
			
		}

		return null;
	}

	@Override
	public ResponseEntity<List<ClientOrdersModel>> getAllByIdentifier(String identifier) {

		List<ClientOrdersModel> response = ordersRepository.getByOrderIdentifier(identifier);

		if (response.isEmpty()) {
			hs = HttpStatus.NO_CONTENT;
		} else {
			hs = HttpStatus.OK;
		}

		return new ResponseEntity<>(response, hs);
	}

}
