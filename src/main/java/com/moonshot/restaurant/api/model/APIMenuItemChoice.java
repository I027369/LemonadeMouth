package com.moonshot.restaurant.api.model;

import com.moonshot.restaurant.entity.MenuItem;

public class APIMenuItemChoice {
	
	private Long id;
	private String name;
	private String detail;
	private boolean required;
	private Long menuItemId;
	public APIMenuItemChoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public APIMenuItemChoice(Long id, String name, String detail, boolean required, Long menuItemId) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.required = required;
		this.menuItemId = menuItemId;
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
	public Long getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}
		

}
