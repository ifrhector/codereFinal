package com.example.cfinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cfinal.models.OrdersProductsModel;
import com.example.cfinal.services.OrdersProductsService;
import com.example.cfinal.vo.ClientsOrdersVo;
import com.example.cfinal.vo.GenericResponseVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/op")
@Tag(name = "Gestion de ordenes y productos", description = "Endpoints para gestionar productos en una orden")
public class OrderProductsController {
	
	@Autowired
	OrdersProductsService opService;
	
	@Operation(summary = "Obtener lista de ordenes vs productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = OrdersProductsModel.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping(path = "/")
	public ResponseEntity<List<OrdersProductsModel>> getAll(){
		return opService.getAll();
	}
	
	@Operation(summary = "Obtener lista de ordenes vs productos por ID de cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientsOrdersVo.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping(path = "/cli/{id}")
	public ResponseEntity<ClientsOrdersVo> getOrdersByClient(@PathVariable Long id){
		return opService.getByClientId(id);
	}

	@Operation(summary = "Agregar productos a orden")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Productos agregados correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientsOrdersVo.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping(path = "/")
	public ResponseEntity<GenericResponseVo> addNew(@RequestBody OrdersProductsModel op){
		return opService.addNew(op);
	}
	
	@Operation(summary = "Actualizar productos de una orden de venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Orden actualizada correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientsOrdersVo.class)) }),
			@ApiResponse(responseCode = "409", description = "Conflicto", content = @Content),
			@ApiResponse(responseCode = "400", description = "El ID no existe", content = @Content)})
	@PutMapping(path = "/{id}")
	public ResponseEntity<GenericResponseVo> update(@PathVariable Long id, @RequestBody OrdersProductsModel op){
		return opService.update(id, op);
	}
	
	
}
