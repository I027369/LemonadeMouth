package com.moonshot.restaurant.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class MenuItemSize {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="menu_item_size_seq_gen")
	@SequenceGenerator(name="menu_item_size_seq_gen", sequenceName="MENUITEM_SIZE_SEQ")
	private Long id;
	@NotNull
	@Column(nullable=false)
	private String name;
	private String description;
	private float price;
	private String currency;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private MenuItem menuItem;
	public MenuItemSize() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuItemSize(Long id, String name, String description, float price, String currency, MenuItem menuItem) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.currency = currency;
		this.menuItem = menuItem;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	
}
