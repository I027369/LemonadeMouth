package com.moonshot.restaurant.entity;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemOption {
	private Long menuItemOptionId;
	private String name;
	private String description;
	private float price;
	private String currency;
	public OrderItemOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItemOption(Long menuItemOption, String name, String description, float price, String currency) {
		super();
		this.menuItemOptionId = menuItemOption;
		this.name = name;
		this.description = description;
		this.price = price;
		this.currency = currency;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getMenuItemOptionId() {
		return menuItemOptionId;
	}

	public void setMenuItemOptionId(Long menuItemOption) {
		this.menuItemOptionId = menuItemOption;
	}
	

}
