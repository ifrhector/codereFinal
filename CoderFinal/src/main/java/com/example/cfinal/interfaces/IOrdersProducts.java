package com.example.cfinal.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.cfinal.models.OrdersProductsModel;
import com.example.cfinal.vo.ClientsOrdersVo;
import com.example.cfinal.vo.GenericResponseVo;

public interface IOrdersProducts {
	
	public ResponseEntity<GenericResponseVo> addNew(OrdersProductsModel orderProduct);
	
	public ResponseEntity<List<OrdersProductsModel>> getAll();
	
	public ResponseEntity<Optional<OrdersProductsModel>> getById(Long Id);
	
	public ResponseEntity<ClientsOrdersVo> getByClientId(Long id);
	
	public ResponseEntity<GenericResponseVo> update(Long id, OrdersProductsModel orderProduct);
	
	public ResponseEntity<GenericResponseVo> delete(Long id);

}
