package com.example.cfinal.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.cfinal.models.ClientOrdersModel;

public interface IOrders {
	
	public ResponseEntity<ClientOrdersModel> addNew(ClientOrdersModel order);
	
	public  ResponseEntity<Optional<ClientOrdersModel>> getById(Long id);
	
	public  ResponseEntity<List<ClientOrdersModel>> getAll();
	
	public  ResponseEntity<List<ClientOrdersModel>> getAllByClientId(Long id);
	
	public  ResponseEntity<List<ClientOrdersModel>> getAllByIdentifier(String identifier);
	
	public  ResponseEntity<ClientOrdersModel> update(Long id, ClientOrdersModel order);
	
	public  ResponseEntity<Optional<ClientOrdersModel>> delete(Long id);

}
