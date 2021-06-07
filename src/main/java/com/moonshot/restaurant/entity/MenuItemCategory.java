package com.moonshot.restaurant.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class MenuItemCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="menuitemcategory_seq_gen")
	@SequenceGenerator(name="menuitemcategory_seq_gen", sequenceName="MENUITEMCATEGORY_SEQ")
	private Long id;
	@NotNull
	@Column(nullable=false)
	private String name;
		
	private int sortOrder;
	
	@ManyToOne
	private Restaurant restaurant;
	public MenuItemCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuItemCategory(Long id, String name, int sortOrder, Restaurant restaurant) {
		super();
		this.id = id;
		this.name = name;
		this.sortOrder = sortOrder;
		this.restaurant = restaurant;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	
		
}