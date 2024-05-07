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

import com.example.cfinal.models.ClientOrdersModel;
import com.example.cfinal.services.OrdersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/orders")
@Tag(name = "Gestion de ordenes de venta", description = "Endpoints para gestionar ordenes de venta")
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	
	@Operation(summary = "Obtener lista de ordenes de venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de ordenes obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientOrdersModel.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping(path = "/")
	public ResponseEntity<List<ClientOrdersModel>> getAll(){
		return ordersService.getAll();
	}
	
	@Operation(summary = "Obtener de ordenes de venta por id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Orden obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientOrdersModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Orden inexistente", content = @Content) })
	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<ClientOrdersModel>> getById(@PathVariable Long id){
		return ordersService.getById(id);
	}
	
	@Operation(summary = "Obtener de ordenes de venta por ID de cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ordenes obtenidas correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientOrdersModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Orden inexistente", content = @Content) })
	@GetMapping(path = "/client/{id}")
	public ResponseEntity<List<ClientOrdersModel>> getByClientId(@PathVariable Long id){
		return ordersService.getAllByClientId(id);
	}
	
	@Operation(summary = "Agregar orden de venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Ordene agregada correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientOrdersModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Error con los datos de la orden", content = @Content) })
	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientOrdersModel> addNew(@RequestBody ClientOrdersModel clientOrder){
		return ordersService.addNew(clientOrder);
	}
	
	@Operation(summary = "Modificar orden de venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ordene modificada correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientOrdersModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Error con los datos de la orden", content = @Content),
			@ApiResponse(responseCode = "409", description = "Conflicto con la orden, cancelada, no admite modificaciones", content = @Content)})
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientOrdersModel> update(@PathVariable Long id, @RequestBody ClientOrdersModel clientOrder){
		return ordersService.update(id, clientOrder);
	}
	
	@Operation(summary = "Eliminar orden de venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Ordene eliminada correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientOrdersModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Orden no localizada", content = @Content) })
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Optional<ClientOrdersModel>> delete(@PathVariable Long id){
		return ordersService.delete(id);
	}
}
