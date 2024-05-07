package com.example.cfinal.models;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Schema(description = "Modelo de cliente vs ordenes")
@Entity
@Table(name = "client_orders")
public class ClientOrdersModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderIdentifier;

	@Column(name = "date_order")
	private LocalDateTime makeDateOrder;

	@Column(name = "date_updated")
	private LocalDateTime updateDateOrder;

	@Column(name = "date_canceled")
	private LocalDateTime canceledDateOrder;

	private String notes;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private ClientModel client;

	@ManyToOne
	@JoinColumn(name = "order_status_id")
	private CatOrderStatus statusId;

	private double total;

	public ClientOrdersModel() {
		super();
	}

	public ClientOrdersModel(Long id, String orderIdentifier, LocalDateTime makeDateOrder,
			LocalDateTime updateDateOrder, LocalDateTime canceledDateOrder, String notes, ClientModel client,
			CatOrderStatus statusId, double total) {
		super();
		this.id = id;
		this.orderIdentifier = orderIdentifier;
		this.makeDateOrder = makeDateOrder;
		this.updateDateOrder = updateDateOrder;
		this.canceledDateOrder = canceledDateOrder;
		this.notes = notes;
		this.client = client;
		this.statusId = statusId;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderIdentifier() {
		return orderIdentifier;
	}

	public void setOrderIdentifier(String orderIdentifier) {
		this.orderIdentifier = orderIdentifier;
	}

	public LocalDateTime getMakeDateOrder() {
		return makeDateOrder;
	}

	public void setMakeDateOrder(LocalDateTime makeDateOrder) {
		this.makeDateOrder = makeDateOrder;
	}

	public LocalDateTime getUpdateDateOrder() {
		return updateDateOrder;
	}

	public void setUpdateDateOrder(LocalDateTime updateDateOrder) {
		this.updateDateOrder = updateDateOrder;
	}

	public LocalDateTime getCanceledDateOrder() {
		return canceledDateOrder;
	}

	public void setCanceledDateOrder(LocalDateTime canceledDateOrder) {
		this.canceledDateOrder = canceledDateOrder;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ClientModel getClient() {
		return client;
	}

	public void setClient(ClientModel client) {
		this.client = client;
	}

	public CatOrderStatus getStatusId() {
		return statusId;
	}

	public void setStatusId(CatOrderStatus statusId) {
		this.statusId = statusId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
