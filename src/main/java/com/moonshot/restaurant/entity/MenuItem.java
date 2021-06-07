package com.moonshot.restaurant.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class MenuItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="menuitem_seq_gen")
	@SequenceGenerator(name="menuitem_seq_gen", sequenceName="MENUITEM_SEQ")
	private Long id;
	
	@NotNull
	@Column(nullable=false)
	private String name;
	
	private String description;
	@NotNull
	@Column(nullable=false)
	private float price;
	@NotNull
	@Column(nullable=false)
	private String currency;
	
	private String image;
	private String type; //Veg / Vegan / Non Veg	
	private String itemGroup; //Chef special / day special / etc... 	
	
	private String status;
	
	@OneToOne
	@NotNull
	private MenuItemCategory category; //Starter / main course / desserts etc.. ( refers to Item Category ) 
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Restaurant restaurant;
	
	@ManyToMany
	/*(mappedBy="menuItem")
	@JsonBackReference*/
	private List<Menu> menu = new ArrayList<>();
	
	
	public MenuItem() {
		super();
	}
	public MenuItem(Long id, String name, String description, float price, String currency, String image,
			MenuItemCategory category, String itemType, String status, String menuItemGroup, Restaurant restaurant , List<Menu> menu) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.currency = currency;
		this.image = image;
		this.category = category;
		this.type = itemType;
		this.status = status;
		this.itemGroup = menuItemGroup;
		this.restaurant = restaurant;
		this.menu = menu;
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
	public MenuItemCategory getCategory() {
		return category;
	}
	public void setCategory(MenuItemCategory category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String itemType) {
		this.type = itemType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(String menuItemGroup) {
		this.itemGroup = menuItemGroup;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public List<Menu> getMenu() {
		return menu;
	}
	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	/*
	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", currency=" + currency + ", image=" + Arrays.toString(image) + ", category=" + category
				+ ", itemType=" + itemType + ", status=" + status + ", menuItemGroup=" + menuItemGroup + ", restaurant=" + restaurant
				+ ", menu=" + menu + "]";
	}
	*/
	
}
