package com.example.cfinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.cfinal.interfaces.IOrderStatus;
import com.example.cfinal.models.CatOrderStatus;
import com.example.cfinal.models.ProductModel;

public class OrderStatusService implements IOrderStatus {
	
	@Autowired
	OrderStatusService orderStatusService;

	@Override
	public ResponseEntity<CatOrderStatus> addNew(ProductModel product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<CatOrderStatus>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Optional<CatOrderStatus>> getById() {
		// TODO Auto-generated method stub
		return null;
	}

}
