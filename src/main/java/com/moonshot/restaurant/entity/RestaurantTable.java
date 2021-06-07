package com.moonshot.restaurant.entity;

import java.util.Arrays;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RestaurantTable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="resturanttable_seq_gen")
	@SequenceGenerator(name="resturanttable_seq_gen", sequenceName="RESTABLE_SEQ")
	private Long id;
	
	@NotNull
	@Column(nullable=false)
	private String tableId;
	
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Restaurant restaurant;
	
	public RestaurantTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestaurantTable(Long id, String tableId, String description, Restaurant restaurant) {
		super();
		this.id = id;
		this.tableId = tableId;
		this.description = description;
		this.restaurant = restaurant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}
