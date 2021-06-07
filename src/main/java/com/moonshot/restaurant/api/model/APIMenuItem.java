package com.moonshot.restaurant.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItemCategory;
import com.moonshot.restaurant.entity.Restaurant;

public class APIMenuItem {
	private Long id;
	private String name;
	private String description;
	private float price;
	private String currency;
	private String image;
	private String itemGroup; //Chef special / day special / etc...
	private String type; //Veg / Vegan / Non Veg
	
	private String status;
	 	
	private Long restaurantId;
	
	private Long categoryId;
	
	private String categoryName;
	
	private List<Long> menuId;
	
	public APIMenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}



	public APIMenuItem(Long id, String name, String description, float price, String currency, String image,
			String itemGroup, String type, String status, Long restaurantId, Long categoryId, String categoryName,
			List<Long> menuId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.currency = currency;
		this.image = image;
		this.itemGroup = itemGroup;
		this.type = type;
		this.status = status;
		this.restaurantId = restaurantId;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.menuId = menuId;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(String group) {
		this.itemGroup = group;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<Long> getMenuId() {
		return menuId;
	}

	public void setMenuId(List<Long> menuId) {
		this.menuId = menuId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

		
	
	
}
