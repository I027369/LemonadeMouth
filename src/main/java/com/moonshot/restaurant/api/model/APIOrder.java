package com.moonshot.restaurant.api.model;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantTable;


public class APIOrder {

	private Long id;
	
	private Long restaurantId;	
	
	private Long appUserId;
	
	private Long tableId;
	
	private String orderType; //mobile/table
	
	private float totalAmount;
	
	private float tax;
	
	private float subTotal;
	
	private String currency;	
	
	private String status;		
	
	private OffsetDateTime timestamp;
	
	private List<APIOrderItem> orderItem;
	
	private String message;
	

	public APIOrder() {
		super();
		// TODO Auto-generated constructor stub
	}


	public APIOrder(Long id, Long restaurantId, Long appUserId, Long tableId, String orderType, float totalAmount,
			String currency, String status, OffsetDateTime timestamp, List<APIOrderItem> orderItem) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.appUserId = appUserId;
		this.tableId = tableId;
		this.orderType = orderType;
		this.totalAmount = totalAmount;
		this.currency = currency;
		this.status = status;
		this.timestamp = timestamp;
		this.orderItem = orderItem;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}


	public Long getAppUserId() {
		return appUserId;
	}


	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}


	public Long getTableId() {
		return tableId;
	}


	public void setTableId(Long tableId) {
		this.tableId = tableId;
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


	public List<APIOrderItem> getOrderItem() {
		return orderItem;
	}


	public void setOrderItem(List<APIOrderItem> orderItem) {
		this.orderItem = orderItem;
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
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
}
