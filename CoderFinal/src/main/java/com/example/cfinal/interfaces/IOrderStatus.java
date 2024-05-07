package com.example.cfinal.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.cfinal.models.CatOrderStatus;
import com.example.cfinal.models.ProductModel;

public interface IOrderStatus {

	public ResponseEntity<CatOrderStatus> addNew(ProductModel product);

	public ResponseEntity<List<CatOrderStatus>> getAll();
	
	public ResponseEntity<Optional<CatOrderStatus>> getById();
	
}
