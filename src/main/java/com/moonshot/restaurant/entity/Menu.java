package com.moonshot.restaurant.entity;

import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Menu {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="menu_seq_gen")
	@SequenceGenerator(name="menu_seq_gen", sequenceName="MENU_SEQ")
	private Long id;
	@NotNull
	@Column(nullable=false)
	private String name;
	
	//private String description;
	
	private String status;
	//private String duration; //Breakfast, Lunch, Brunch, Breakfast/Lunch, Dinner, Dinner/Lunch, Late Night, All
	@NotNull
	@Column(nullable=false)
	private java.time.OffsetTime availableFrom;
	@NotNull
	@Column(nullable=false)
	private java.time.OffsetTime availableTo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Restaurant restaurant;
	
	/*
	@ManyToMany
	//@JsonManagedReference
	private List<MenuItem> menuItem = new ArrayList<>(); */
	
	
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Menu(Long id, String name, String status, OffsetTime availableFrom, OffsetTime availableTo,
			Restaurant restaurant) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.availableFrom = availableFrom;
		this.availableTo = availableTo;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OffsetTime getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(OffsetTime availableFrom) {
		this.availableFrom = availableFrom;
	}

	public OffsetTime getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(OffsetTime availableTo) {
		this.availableTo = availableTo;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
}