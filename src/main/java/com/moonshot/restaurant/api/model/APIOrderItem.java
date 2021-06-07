package com.moonshot.restaurant.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class APIOrderItem {

	private Long id;
	
	private String name;	
	
	private String description;	
	
	private String image;
	
	private int quantity;
	
	private String notes;
	
	private float amount;
	
	private float price;
	
	private String currency;
	
	@JsonIgnore
	private int sortOrder;	
	
	private Long orderId;
	
	private Long menuItemId;
	
	private List<APIOrderItemOption> orderItemOption;
		
		
	public APIOrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}


	public APIOrderItem(Long id, Long menuItemId, int quantity, String unit, String notes, float amount, float price,
			String currency, String name, String description, String image, int sortOrder, Long orderId) {
		super();
		this.id = id;
		this.menuItemId = menuItemId;
		this.quantity = quantity;
		this.notes = notes;
		this.amount = amount;
		this.price = price;
		this.currency = currency;
		this.name = name;
		this.description = description;
		this.image = image;
		this.sortOrder = sortOrder;
		this.orderId = orderId;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getMenuItemId() {
		return menuItemId;
	}


	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public int getSortOrder() {
		return sortOrder;
	}


	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}


	public List<APIOrderItemOption> getOrderItemOption() {
		return orderItemOption;
	}


	public void setOrderItemOption(List<APIOrderItemOption> orderItemOption) {
		this.orderItemOption = orderItemOption;
	}

	
}
