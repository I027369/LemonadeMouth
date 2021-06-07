package com.moonshot.restaurant.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class RestaurantAccountUser {	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="rest_act_user_seq_gen")
	@SequenceGenerator(name="rest_act_user_seq_gen", sequenceName="RESTACTUSER_SEQ")
	private Long id;
	@NotNull
	@Column(unique=true)
	private String emailId;
	@NotNull
	private String name;
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@NotNull
	private String role;
	private String company;
	private String phone;
	@ManyToOne
	private Restaurant restaurant;
	private boolean blocked;
	
	public RestaurantAccountUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestaurantAccountUser(Long id, String emailId, String name, String password, String role, String company,
			String phone, Restaurant restaurant, boolean blocked) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.name = name;
		this.password = password;
		this.role = role;
		this.company = company;
		this.phone = phone;
		this.restaurant = restaurant;
		this.blocked = blocked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	

	public boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	@Override
	public String toString() {
		return "RestaurantAccountUser [id=" + id + ", emailId=" + emailId + ", name=" + name + ", password=" + password
				+ ", role=" + role + ", company=" + company + ", phone=" + phone + 
				", blocked=" + blocked + ", restaurant=" + restaurant + "]";
	}
	

}
