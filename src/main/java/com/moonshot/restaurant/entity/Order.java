package com.moonshot.restaurant.entity;


import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "RestaurantOrder")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="order_seq_gen")
	@SequenceGenerator(name="order_seq_gen", sequenceName="ORDER_SEQ")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Restaurant restaurant;
	
	@OneToOne
	@NotNull
	private AppUser appUser;
	
	
	@OneToOne
	private RestaurantTable restaurantTable;
	
	private String orderType; //mobile/table
	
	@Column(precision=10, scale=2)
	private float totalAmount;
	
	@Column(precision=10, scale=2)
	private float tax;
	
	@Column(precision=10, scale=2)
	private float subTotal;
	
	private String currency;	
	
	private String status; // In-process/paid
		
	private OffsetDateTime timestamp;
	
	private String message;


	//create the orderitem in the context of order i.e first create order and later add order items
	// don't create order and order items together
	//@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="order")	
	//private List<OrderItem> item = new ArrayList<>();
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Order(Long id, Restaurant restaurant, AppUser appUser, RestaurantTable restaurantTable, String orderType,
			float totalAmount, String currency, String status, OffsetDateTime timestamp) {
		super();
		this.id = id;
		this.restaurant = restaurant;
		this.appUser = appUser;
		this.restaurantTable = restaurantTable;
		this.orderType = orderType;
		this.totalAmount = totalAmount;
		this.currency = currency;
		this.status = status;
		this.timestamp = timestamp;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	public AppUser getAppUser() {
		return appUser;
	}


	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}


	public RestaurantTable getRestaurantTable() {
		return restaurantTable;
	}


	public void setRestaurantTable(RestaurantTable restaurantTable) {
		this.restaurantTable = restaurantTable;
	}


	public String getOrderType() {
		return orderType;
	}


	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}


	public float getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public OffsetDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public float getTax() {
		return tax;
	}


	public void setTax(float tax) {
		this.tax = tax;
	}


	public float getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
		if (this.restaurant != null && this.restaurant.getTaxPercentage() > 0) {
			this.tax = (this.subTotal * this.restaurant.getTaxPercentage())/100;
		}
		this.totalAmount = this.subTotal + this.tax;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
