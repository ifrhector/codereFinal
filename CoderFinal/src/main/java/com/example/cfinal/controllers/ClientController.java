package com.example.cfinal.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cfinal.models.ClientModel;
import com.example.cfinal.services.ClientService;
import com.example.cfinal.vo.ClientsOrdersVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/client")
@Tag(name = "Gestion de clientes", description = "Endpoints para gestionar clientes")
public class ClientController {

	@Autowired
	ClientService clientService;

	@Operation(summary = "Obtener lista de clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientModel.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping(path = "/")
	public List<ClientModel> getAll() {
		return clientService.getAll();
	}

	@Operation(summary = "Obtener cliente por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientModel.class)) }),
			@ApiResponse(responseCode = "400", description = "ID de cliente no localizado", content = @Content) })
	@GetMapping(path = "/{id}")
	Optional<ClientModel> getById(@PathVariable Long id) {
		return clientService.getById(id);
	}

	@Operation(summary = "Obtener lista de ordenes por id de cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de ordenes obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientsOrdersVo.class)) }),
			@ApiResponse(responseCode = "400", description = "ID de cliente no localizado", content = @Content) })
	@GetMapping(path = "/orders/{id}")
	ResponseEntity<ClientsOrdersVo> getOrders(@PathVariable Long id) {
		return clientService.getOrdersById(id);
	}

	@Operation(summary = "Obtener lista de clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientModel.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ClientModel> addNew(@RequestBody ClientModel client) {
		return clientService.addNew(client);
	}

	@Operation(summary = "Actualizar datos de clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientModel.class)) }),
			@ApiResponse(responseCode = "400", description = "ID de cliente no localizado", content = @Content) })
	@PutMapping(value = "/{id}")
	ResponseEntity<ClientModel> update(@PathVariable Long id, @RequestBody ClientModel client) {
		return clientService.update(id, client);
	}

	@Operation(summary = "Baja de un clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente dado de baja correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientModel.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
			@ApiResponse(responseCode = "400", description = "No localizado", content = @Content)})
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Optional<ClientModel>> delete(@PathVariable Long id) {
		return clientService.delete(id);
	}

}
