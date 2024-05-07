package com.example.cfinal.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.cfinal.models.ClientModel;
import com.example.cfinal.vo.ClientsOrdersVo;

public interface IClient {

	public ResponseEntity<ClientModel> addNew(ClientModel client);

	public List<ClientModel> getAll();

	public Optional<ClientModel> getById(Long id);

	public ResponseEntity<ClientModel> update(Long id, ClientModel client);

	public ResponseEntity<Optional<ClientModel>> delete(Long id);

	public ResponseEntity<ClientsOrdersVo> getOrdersById(Long id);

}
