package com.moonshot.restaurant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

//@Entity
public class RestaurantAddress {	/*
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="restadd_seq_gen")
	@SequenceGenerator(name="restadd_seq_gen", sequenceName="RESTADD_SEQ")
	private Long id;
	private String type;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String country;
	private String pincode;
	//@ManyToOne
	//private Restaurant restaurant;
	
	public RestaurantAddress() {
		super();
	}

	public RestaurantAddress(Long id, String type, String line1, String line2, String city, String state, String country,
			String pincode, Restaurant restaurant) {
		super();
		this.id = id;
		this.type = type;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		//this.restaurant = restaurant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}*/
/*
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	@Override
	public String toString() {
		return "RestaurantAddress [id=" + id + ", type=" + type + ", line1=" + line1 + ", line2=" + line2 + ", city="
				+ city + ", state=" + state + ", country=" + country + ", pincode=" + pincode + "]";
	}
	
	@Override
	public String toString() {
		return "RestaurantAddress [id=" + id + ", type=" + type + ", line1=" + line1 + ", line2=" + line2 + ", city="
				+ city + ", state=" + state + ", country=" + country + ", pincode=" + pincode + ", restaurant="
				+ restaurant + "]";
	}*/

	
}
