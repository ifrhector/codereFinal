package com.example.cfinal.models;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Schema(description = "Modelo de ordenes vs producto")
@Entity
@Table(name = "orders_products")
public class OrdersProductsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private ClientOrdersModel orderId;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductModel product;

	private int quantity;

	private double price;

	private double total;

	private LocalDateTime movementDate;

	public OrdersProductsModel() {
		super();
	}

	public OrdersProductsModel(Long id, ClientOrdersModel orderId, ProductModel product, int quantity, double price,
			double total, LocalDateTime movementDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.movementDate = movementDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientOrdersModel getOrderId() {
		return orderId;
	}

	public void setOrderId(ClientOrdersModel orderId) {
		this.orderId = orderId;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDateTime getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(LocalDateTime movementDate) {
		this.movementDate = movementDate;
	}

}
