package com.moonshot.restaurant.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class APIMenuWithItemAndCategory {

	private Long id;
	private String name;
	@JsonIgnore
	private int sortOrder;
	private List<APIMenuItem> menuItem;
	public APIMenuWithItemAndCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public APIMenuWithItemAndCategory(Long id, String name, int sortOrder, List<APIMenuItem> menuItem) {
		super();
		this.id = id;
		this.name = name;
		this.sortOrder = sortOrder;
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
	
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public List<APIMenuItem> getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(List<APIMenuItem> menuItem) {
		this.menuItem = menuItem;
	}
	
}
