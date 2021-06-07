package com.moonshot.restaurant.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class AppUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="app_user_seq_gen")
	@SequenceGenerator(name="app_user_seq_gen", sequenceName="APPUSER_SEQ")
	private Long id;
	private String emailId;
	private String name;
	private String picture;
	private String foodPreference;
	private String defaultPaymentMode;
	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppUser(Long id, String emailId, String name, String picture, String foodPreference,
			String defaultPaymentMode) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.name = name;
		this.picture = picture;
		this.foodPreference = foodPreference;
		this.defaultPaymentMode = defaultPaymentMode;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getFoodPreference() {
		return foodPreference;
	}
	public void setFoodPreference(String foodPreference) {
		this.foodPreference = foodPreference;
	}
	public String getDefaultPaymentMode() {
		return defaultPaymentMode;
	}
	public void setDefaultPaymentMode(String defaultPaymentMode) {
		this.defaultPaymentMode = defaultPaymentMode;
	}
}
