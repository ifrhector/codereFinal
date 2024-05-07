package com.example.cfinal.vo;

public class ProductsListVo {

	private String productName;
	private String dateAdded;
	private int quantity;
	private double price;
	private double total;

	public ProductsListVo() {
		super();
	}

	public ProductsListVo(String productName, String dateAdded, int quantity, double price, double total) {
		super();
		this.productName = productName;
		this.dateAdded = dateAdded;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
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

}
