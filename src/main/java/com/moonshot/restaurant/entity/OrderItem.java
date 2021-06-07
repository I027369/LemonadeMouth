package com.moonshot.restaurant.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="orderitem_seq_gen")
	@SequenceGenerator(name="orderitem_seq_gen", sequenceName="ORDERITEM_SEQ")
	private Long id;
	
	/*
	@OneToOne
	@NotNull
	private MenuItem menuItem;	
	*/
	
	@NotNull
	@Column(nullable=false)
	private String name;	
	
	private String description;	
	
	private String image;
	
	@NotNull
	@Column(nullable=false)
	private int quantity;
	

	private String notes;
	
	@NotNull
	@Column(nullable=false)
	private float price;
	
	@NotNull
	@Column(nullable=false)
	private float amount;
	
	@NotNull
	@Column(nullable=false)
	private String currency;	
	
	private int sortOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;
	
	@ElementCollection
	@JoinTable(name="OrderItemOption")
	@GenericGenerator(name="hilo-gen", strategy="seqhilo")
	@CollectionId(columns = { @Column(name="OrderItemOptionId") }, generator = "hilo-gen", type = @Type(type="long"))
	private Collection<OrderItemOption> listOfOption = new ArrayList<OrderItemOption>();
	
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrderItem(Long id, String name, String description, String image, int quantity, String unit, String notes,
			float price, float amount, String currency, int sortOrder, Order order) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.quantity = quantity;
		this.notes = notes;
		this.price = price;
		this.amount = amount;
		this.currency = currency;
		this.sortOrder = sortOrder;
		this.order = order;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}


	public Collection<OrderItemOption> getListOfOption() {
		return listOfOption;
	}


	public void setListOfOption(Collection<OrderItemOption> listOfOption) {
		this.listOfOption = listOfOption;
	}

	
}
