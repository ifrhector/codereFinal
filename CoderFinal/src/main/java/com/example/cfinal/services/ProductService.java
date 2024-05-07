package com.example.cfinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cfinal.interfaces.IProduct;
import com.example.cfinal.models.ProductModel;
import com.example.cfinal.repositories.IProductRepository;

@Service
public class ProductService implements IProduct {
	
	@Autowired
	IProductRepository productRepository;
	
	private HttpStatus hs = null;

	@Override
	public ResponseEntity<ProductModel> addNew(ProductModel product) {
		
		ProductModel response = productRepository.save(product);
		
		if(response != null) {
			hs = HttpStatus.ACCEPTED;
		}else {
			hs = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<List<ProductModel>> getAll() {
		
		List<ProductModel> response = productRepository.findAll();
		
		if(response.isEmpty()) {
			hs = HttpStatus.BAD_REQUEST;
		}else {
			hs = HttpStatus.OK;
		}
		
		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<Optional<ProductModel>> getById(Long id) {
		
		Optional<ProductModel> response = null;
		
		if(productRepository.existsById(id)) {
			response = productRepository.findById(id);
			hs = HttpStatus.OK;
		}else {
			hs = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<ProductModel> update(Long id, ProductModel product) {
		
		if(productRepository.existsById(id)) {
			product.setId(id);
			productRepository.save(product);
			hs = HttpStatus.OK;
		}else {
			hs = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<>(product, hs);
	}

	//Product don`t delete from DB, then product set stock 0, in this mode product will can't use and the record is permanent.
	@Override
	public ResponseEntity<ProductModel> delete(Long id) {
		
		Optional<ProductModel> response = null;
		
		if(productRepository.existsById(id)) {
			response = productRepository.findById(id);
			response.get().setStock(0);
			productRepository.save(response.get());
			hs = HttpStatus.OK;
		}else {
			hs = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<>(response != null ? response.get() : null, hs);
	}

}
