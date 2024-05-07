package com.example.cfinal.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.cfinal.models.ProductModel;

public interface IProduct {
	
	public ResponseEntity<ProductModel> addNew(ProductModel product);

	public ResponseEntity<List<ProductModel>> getAll();
	
	public ResponseEntity<Optional<ProductModel>> getById(Long id);
	
	public ResponseEntity<ProductModel> update(Long id, ProductModel product);
	
	public ResponseEntity<ProductModel> delete(Long id);
	
}
