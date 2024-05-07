package com.example.cfinal.vo;

import java.util.List;

public class ClientsOrdersVo {

	private String clientName;
	private String clientCountry;
	private List<OrdersVo> orders;

	public ClientsOrdersVo() {
		super();
	}

	public ClientsOrdersVo(String clientName, String clientCountry, List<OrdersVo> orders) {
		super();
		this.clientName = clientName;
		this.clientCountry = clientCountry;
		this.orders = orders;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientCountry() {
		return clientCountry;
	}

	public void setClientCountry(String clientCountry) {
		this.clientCountry = clientCountry;
	}

	public List<OrdersVo> getOrders() {
		return orders;
	}

	public void setOrders(List<OrdersVo> orders) {
		this.orders = orders;
	}

}
