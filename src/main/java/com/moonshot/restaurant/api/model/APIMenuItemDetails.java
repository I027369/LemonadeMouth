package com.moonshot.restaurant.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItemCategory;
import com.moonshot.restaurant.entity.Restaurant;

public class APIMenuItemDetails extends APIMenuItem{
	
	private List<APIMenuItemSize> sizes;
	private List<APIMenuItemChoiceDetails> choices;

	public APIMenuItemDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIMenuItemDetails(Long id, String name, String description, float price, String currency, String image,
			String itemGroup, String type, String status, Long restaurantId, Long categoryId, String categoryName,
			List<Long> menuId) {
		super(id, name, description, price, currency, image, itemGroup, type, status, restaurantId, categoryId, categoryName,
				menuId);
		// TODO Auto-generated constructor stub
	}

	public APIMenuItemDetails(List<APIMenuItemSize> sizes, List<APIMenuItemChoiceDetails> choices) {
		super();
		this.sizes = sizes;
		this.choices = choices;
	}
	public List<APIMenuItemSize> getSizes() {
		return sizes;
	}
	public void setSizes(List<APIMenuItemSize> sizes) {
		this.sizes = sizes;
	}
	public List<APIMenuItemChoiceDetails> getChoices() {
		return choices;
	}
	public void setChoices(List<APIMenuItemChoiceDetails> choices) {
		this.choices = choices;
	}
	
		
}
