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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="resturant_seq_gen")
	@SequenceGenerator(name="resturant_seq_gen", sequenceName="RESTURANT_SEQ")
	private Long id;
	@NotNull
	@Column(nullable=false)
	private String name;
	
	@NotNull
	@Column(nullable=false)
	private String tagLine;	
	
	@NotNull
	@Column(nullable=false)
	private String logo;
	

	private String QRLogo;
	
	@NotNull
	@Column(nullable=false)	
    private String backgroundImage;
	@NotNull
	@Column(nullable=false)
	private String backgroundColor;
	
	private String tags;
	
	@NotNull
	@Column(nullable=false)
	private String status;
	
  
	@NotNull
	@Column(nullable=false)
    private String address;
	@NotNull
	@Column(nullable=false)
	private String city;
	@NotNull
	@Column(nullable=false)
	private String state;
	@NotNull
	@Column(nullable=false)
	private String country;
	@NotNull
	@Column(nullable=false)
	private String zip;
	
	private String website;
	@NotNull
	@Column(nullable=false)
	private String phone;
	@NotNull
	@Column(nullable=false)
	private String email;
	
	@NotNull
	@Column(nullable=false)
	private String currency;
	
	private float taxPercentage;
	
	private String stripePublishKey;
	
	private String stripeSecretKey;
	
	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
		this.name = "";
		this.tagLine = "";
		this.logo = "";
		this.backgroundImage = "";
		this.backgroundColor = "";
		this.status = "Draft";
		this.address = "";
		this.city = "";
		this.state = "";
		this.country = "";
		this.zip = "";
		this.phone = "";
		this.email = "";
		this.currency = "$";
	}

	public Restaurant(Long id, String name, String tagLine, String logo, String qRLogo, String backgroundImage,
			String backgroundColor, String tags, String status, String address, String city, String state,
			String country, String zip, String website, String phone, String email, String currency,
			float taxPercentage) {
		super();
		this.id = id;
		this.name = name;
		this.tagLine = tagLine;
		this.logo = logo;
		QRLogo = qRLogo;
		this.backgroundImage = backgroundImage;
		this.backgroundColor = backgroundColor;
		this.tags = tags;
		this.status = status;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.website = website;
		this.phone = phone;
		this.email = email;
		this.currency = currency;
		this.taxPercentage = taxPercentage;
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

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getQRLogo() {
		return QRLogo;
	}

	public void setQRLogo(String qRLogo) {
		QRLogo = qRLogo;
	}

	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public float getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(float taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public String getStripePublishKey() {
		return stripePublishKey;
	}

	public void setStripePublishKey(String stripePublishKey) {
		this.stripePublishKey = stripePublishKey;
	}

	public String getStripeSecretKey() {
		return stripeSecretKey;
	}

	public void setStripeSecretKey(String stripeSecretKey) {
		this.stripeSecretKey = stripeSecretKey;
	}
		
	
}
