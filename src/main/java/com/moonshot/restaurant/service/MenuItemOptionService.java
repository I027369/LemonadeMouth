package com.moonshot.restaurant.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIMenu;
import com.moonshot.restaurant.api.model.APIMenuItemCategory;
import com.moonshot.restaurant.api.model.APIMenuItemOption;
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.MenuItemCategory;
import com.moonshot.restaurant.entity.MenuItemChoice;
import com.moonshot.restaurant.entity.MenuItemOption;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.repository.MenuItemCategoryRepository;
import com.moonshot.restaurant.repository.MenuItemChoiceRepository;
import com.moonshot.restaurant.repository.MenuItemOptionRepository;
import com.moonshot.restaurant.repository.MenuItemRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;

@Service
public class MenuItemOptionService {
	
	@Autowired
	private MenuItemOptionRepository menuItemOptionRepository;
		    
	@Autowired
	private MenuItemChoiceRepository menuItemChoiceRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;

	public List<APIMenuItemOption> getMenuItemOptions(Long menuItemChoicesId) throws WriterException{
		// TODO Auto-generated method stub
		List<APIMenuItemOption> apiMenuItemOption = new ArrayList<>();
		if (menuItemChoiceRepository.findOne(menuItemChoicesId) != null){
			if (menuItemOptionRepository.findByMenuItemChoiceId(menuItemChoicesId) != null){
				apiMenuItemOption = copyOptionsToAPIOptions(
						menuItemOptionRepository.findByMenuItemChoiceId(menuItemChoicesId), apiMenuItemOption);
				}
		}
		return apiMenuItemOption;
	}


	private List<APIMenuItemOption> copyOptionsToAPIOptions(List<MenuItemOption> menuItemOptionList,
			List<APIMenuItemOption> apiMenuItemOptionList) {
		// TODO Auto-generated method stub
		MenuItemOption menuItemOption = null;
		APIMenuItemOption apiMenuItemOption = null;
		Iterator<MenuItemOption> menuItemOptionIte = menuItemOptionList.iterator();
		 while(menuItemOptionIte.hasNext()){
			 menuItemOption = menuItemOptionIte.next();
			 apiMenuItemOption = new APIMenuItemOption();
			 apiMenuItemOption = copyOptionToAPIOption(menuItemOption, apiMenuItemOption);
			 apiMenuItemOptionList.add(apiMenuItemOption);			 
		 }
		return apiMenuItemOptionList;
	}


	private APIMenuItemOption copyOptionToAPIOption(MenuItemOption menuItemOption,
			APIMenuItemOption apiMenuItemOption) {
		// TODO Auto-generated method stub
		apiMenuItemOption.setId(menuItemOption.getId());
		apiMenuItemOption.setName(menuItemOption.getName());
		apiMenuItemOption.setDescription(menuItemOption.getDescription());
		apiMenuItemOption.setPrice(menuItemOption.getPrice());
		apiMenuItemOption.setCurrency(menuItemOption.getCurrency());
		apiMenuItemOption.setMenuItemChoiceId(menuItemOption.getMenuItemChoice().getId());
		return apiMenuItemOption;
	}


	public APIMenuItemOption getMenuItemOption(Long id) throws WriterException {
		// TODO Auto-generated method stub
		if (menuItemOptionRepository.findOne(id) != null){
			return copyOptionToAPIOption(menuItemOptionRepository.findOne(id), new APIMenuItemOption());
		}else {
			throw new WriterException ("MenItemOption not found");
		}
	}

	public APIMenuItemOption addMenuItemOption(APIMenuItemOption apiMenuItemOption)throws WriterException {
		// TODO Auto-generated method stub
		MenuItemChoice menuItemChoice = menuItemChoiceRepository.findOne(apiMenuItemOption.getMenuItemChoiceId());
		if(menuItemChoice == null)
			throw new WriterException("MenuItemChoice details are missing");
		MenuItemOption menuItemOption = new MenuItemOption();
		menuItemOption = copyAPIOptionToOption(menuItemOption, apiMenuItemOption);
		menuItemOption.setMenuItemChoice(menuItemChoice);
		menuItemOptionRepository.save(menuItemOption);		
		return copyOptionToAPIOption(menuItemOption, apiMenuItemOption);
	}

	private MenuItemOption copyAPIOptionToOption(MenuItemOption menuItemOption, APIMenuItemOption apiMenuItemOption) {
		// TODO Auto-generated method stub
		if(apiMenuItemOption.getName() != null)
			menuItemOption.setName(apiMenuItemOption.getName());
		if(apiMenuItemOption.getDescription() != null)
			menuItemOption.setDescription(apiMenuItemOption.getDescription());
		if(apiMenuItemOption.getPrice() > 0)
			menuItemOption.setPrice(apiMenuItemOption.getPrice());
		//if(apiMenuItemOption.getCurrency() != null)
			//menuItemOption.setCurrency(apiMenuItemOption.getCurrency());		
		String currency = menuItemRepository.findOne(menuItemChoiceRepository.findOne(apiMenuItemOption.getMenuItemChoiceId()).getMenuItem().getId()).getCurrency();
		menuItemOption.setCurrency(currency);
		
		return menuItemOption;
	}


	public APIMenuItemOption updateMenuItemOption(APIMenuItemOption apiMenuItemOption) {
		// TODO Auto-generated method stub
		MenuItemOption menuItemOption = menuItemOptionRepository.findOne(apiMenuItemOption.getId());
		Long tempId = menuItemOption.getId();
		MenuItemChoice menuItemChoice = menuItemOption.getMenuItemChoice();
		
		menuItemOption = copyAPIOptionToOption(menuItemOption, apiMenuItemOption);
		menuItemOption.setId(tempId);
		menuItemOption.setMenuItemChoice(menuItemChoice);
		menuItemOptionRepository.save(menuItemOption);
		return copyOptionToAPIOption(menuItemOption, apiMenuItemOption);
	}

	public void deleteMenuItemOption(Long id) throws WriterException {
		// TODO Auto-generated method stub
		if (menuItemOptionRepository.findOne(id) != null)
			menuItemOptionRepository.delete(id);
		else {
			throw new WriterException ("MenItemOption not found");
		}
		
	}	
	
	public MenuItemOption getMenuItemOptionForOrder(Long menuItemOptionId){
		return menuItemOptionRepository.findOne(menuItemOptionId);
	}
	
}
