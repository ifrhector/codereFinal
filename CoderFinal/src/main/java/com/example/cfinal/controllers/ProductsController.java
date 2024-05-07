package com.example.cfinal.controllers;

import java.util.List;

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

import com.example.cfinal.models.ProductModel;
import com.example.cfinal.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/prods")
@Tag(name = "Gestion de productos", description = "Endpoints para gestionar productos")
public class ProductsController {
	
	@Autowired
	ProductService productService;

	@Operation(summary = "Obtener lista de productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductModel.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping(path = "/")
	public ResponseEntity<List<ProductModel>> getAll(){
		return productService.getAll();
	}
	
	@Operation(summary = "Agregar productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto agregado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Producto no localizado", content = @Content) })
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductModel> addNew(@RequestBody ProductModel product){
		return productService.addNew(product);
	}
	
	@Operation(summary = "Actualizar productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto actualizado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Producto no localizado", content = @Content) })
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody ProductModel product){
		return productService.update(id, product);
	}
	
	@Operation(summary = "Eliminar productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto eliminado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Producto no localizado", content = @Content) })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProductModel> deleteProduct(@PathVariable Long id){
		return productService.delete(id);
	}
}
