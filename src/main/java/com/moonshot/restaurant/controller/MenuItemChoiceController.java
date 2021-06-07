package com.moonshot.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIMenuItemChoice;
import com.moonshot.restaurant.api.model.APIMenuItemChoiceDetails;
import com.moonshot.restaurant.service.MenuItemChoiceService;


@RestController
public class MenuItemChoiceController {

	@Autowired
	private MenuItemChoiceService menuItemChoiceService;
	
	@RequestMapping(method=RequestMethod.GET, value="/menuitems/{menuItemId}/menuitemchoices")
	public List<APIMenuItemChoiceDetails> getMenuItemChoices(@PathVariable Long menuItemId) throws WriterException {
		return menuItemChoiceService.getMenuItemChoices(menuItemId);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/menuitemchoices/{id}")
	public APIMenuItemChoiceDetails getMenuItemChoice(@PathVariable Long id)throws WriterException {
		return menuItemChoiceService.getMenuItemChoice(id);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/menuitemchoices")
	public APIMenuItemChoice addMenuItemChoice(@RequestBody APIMenuItemChoice apiMenuItemChoice) throws WriterException{
		return menuItemChoiceService.addMenuItemChoice(apiMenuItemChoice);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/menuitemchoices/{id}")
	public APIMenuItemChoice updateMenuItemChoice(@RequestBody APIMenuItemChoice apiMenuItemChoice){
		return menuItemChoiceService.updateMenuItemChoice(apiMenuItemChoice);		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/menuitemchoices/{id}")
	public void deleteMenuItemChoice(@PathVariable Long id)throws WriterException {
		menuItemChoiceService.deleteMenuItemChoice(id);		
	}	

}
