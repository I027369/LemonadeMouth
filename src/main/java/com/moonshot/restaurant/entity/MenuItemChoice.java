package com.moonshot.restaurant.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class MenuItemChoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="menu_item_choice_seq_gen")
	@SequenceGenerator(name="menu_item_choice_seq_gen", sequenceName="MENUITEM_CHOICE_SEQ")
	private Long id;
	@NotNull
	@Column(nullable=false)
	private String name;
	@NotNull
	@Column(nullable=false)
	private String detail; //select 1 ; select 2...
	private boolean required;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private MenuItem menuItem;
	public MenuItemChoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuItemChoice(Long id, String name, String detail, boolean required, MenuItem menuItem) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.required = required;
		this.menuItem = menuItem;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public boolean getRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	
	
}
