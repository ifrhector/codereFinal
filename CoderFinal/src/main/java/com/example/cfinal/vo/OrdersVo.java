package com.example.cfinal.vo;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersVo {

	private String order;
	private double totalOrder;
	private LocalDateTime dateOrder;
	private LocalDateTime updateOrder;
	private LocalDateTime canceledOrder;
	private String notes;
	private List<ProductsListVo> products;

	public OrdersVo() {
		super();
	}

	public OrdersVo(String order, double totalOrder, LocalDateTime dateOrder, LocalDateTime updateOrder,
			LocalDateTime canceledOrder, String notes, List<ProductsListVo> products) {
		super();
		this.order = order;
		this.totalOrder = totalOrder;
		this.dateOrder = dateOrder;
		this.updateOrder = updateOrder;
		this.canceledOrder = canceledOrder;
		this.notes = notes;
		this.products = products;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public double getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(double totalOrder) {
		this.totalOrder = totalOrder;
	}

	public LocalDateTime getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(LocalDateTime dateOrder) {
		this.dateOrder = dateOrder;
	}

	public LocalDateTime getUpdateOrder() {
		return updateOrder;
	}

	public void setUpdateOrder(LocalDateTime updateOrder) {
		this.updateOrder = updateOrder;
	}

	public LocalDateTime getCanceledOrder() {
		return canceledOrder;
	}

	public void setCanceledOrder(LocalDateTime canceledOrder) {
		this.canceledOrder = canceledOrder;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<ProductsListVo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductsListVo> products) {
		this.products = products;
	}

}
