package com.moonshot.restaurant.api.model;

import java.util.List;

public class APIMenuItemChoiceDetails extends APIMenuItemChoice {
	
	private List<APIMenuItemOption> option;

	public APIMenuItemChoiceDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIMenuItemChoiceDetails(Long id, String name, String detail, boolean required, Long menuItemId) {
		super(id, name, detail, required, menuItemId);
		// TODO Auto-generated constructor stub
	}

	public APIMenuItemChoiceDetails(List<APIMenuItemOption> option) {
		super();
		this.option = option;
	}

	public List<APIMenuItemOption> getOption() {
		return option;
	}

	public void setOption(List<APIMenuItemOption> option) {
		this.option = option;
	}
	

}
