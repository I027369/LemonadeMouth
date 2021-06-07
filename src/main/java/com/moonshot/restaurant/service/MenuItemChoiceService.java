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
import com.moonshot.restaurant.api.model.APIMenuItemChoice;
import com.moonshot.restaurant.api.model.APIMenuItemChoiceDetails;
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
public class MenuItemChoiceService {
	
	@Autowired
	private MenuItemChoiceRepository menuItemChoiceRepository;
	
	@Autowired
	private MenuItemOptionService menuItemOptionService;
		    
	@Autowired
	private MenuItemRepository menuItemRepository;

	public List<APIMenuItemChoiceDetails> getMenuItemChoices(Long menuItemId) throws WriterException{
		// TODO Auto-generated method stub
		List<APIMenuItemChoiceDetails> apiMenuItemChoice = new ArrayList<>();
		if (menuItemRepository.findOne(menuItemId) != null){
			if (menuItemChoiceRepository.findByMenuItemId(menuItemId) != null){
				apiMenuItemChoice = copyChoiceToAPIChoiceList(
						menuItemChoiceRepository.findByMenuItemId(menuItemId), apiMenuItemChoice);
				}
			return apiMenuItemChoice;
		}else {
			throw new WriterException ("MenuItem not found");
		}		
	}


	private List<APIMenuItemChoiceDetails> copyChoiceToAPIChoiceList(List<MenuItemChoice> menuItemChoiceList,
			List<APIMenuItemChoiceDetails> apiMenuItemChoiceList) throws WriterException {
		// TODO Auto-generated method stub
		MenuItemChoice menuItemChoice = null;
		APIMenuItemChoiceDetails apiMenuItemChoiceDetail = null;
		Iterator<MenuItemChoice> menuItemChoiceIte = menuItemChoiceList.iterator();
		 while(menuItemChoiceIte.hasNext()){
			 menuItemChoice = menuItemChoiceIte.next();
			 apiMenuItemChoiceDetail = new APIMenuItemChoiceDetails();
			 apiMenuItemChoiceDetail = (APIMenuItemChoiceDetails)copyChoiceToAPIChoice(menuItemChoice, apiMenuItemChoiceDetail);
			 apiMenuItemChoiceDetail.setOption(menuItemOptionService.getMenuItemOptions(menuItemChoice.getId()));
			 apiMenuItemChoiceList.add(apiMenuItemChoiceDetail);			 
		 }
		return apiMenuItemChoiceList;
	}


	private APIMenuItemChoice copyChoiceToAPIChoice(MenuItemChoice menuItemChoice,
			APIMenuItemChoice apiMenuItemChoice) {
		// TODO Auto-generated method stub
		apiMenuItemChoice.setId(menuItemChoice.getId());
		apiMenuItemChoice.setName(menuItemChoice.getName());
		apiMenuItemChoice.setDetail(menuItemChoice.getDetail());
		apiMenuItemChoice.setRequired(menuItemChoice.getRequired());
		apiMenuItemChoice.setMenuItemId(menuItemChoice.getMenuItem().getId());
		return apiMenuItemChoice;
	}


	public APIMenuItemChoiceDetails getMenuItemChoice(Long id) throws WriterException {
		// TODO Auto-generated method stub
		if (menuItemChoiceRepository.findOne(id) != null){
			APIMenuItemChoiceDetails apiMenuItemChoiceDetail = new APIMenuItemChoiceDetails();
			apiMenuItemChoiceDetail = (APIMenuItemChoiceDetails) copyChoiceToAPIChoice(menuItemChoiceRepository.findOne(id), new APIMenuItemChoice());
			apiMenuItemChoiceDetail.setOption(menuItemOptionService.getMenuItemOptions(apiMenuItemChoiceDetail.getId()));
			return apiMenuItemChoiceDetail;
		}else {
			throw new WriterException ("MenItemChoice not found");
		}
	}

	public APIMenuItemChoice addMenuItemChoice(APIMenuItemChoice apiMenuItemChoice)throws WriterException {
		// TODO Auto-generated method stub
		MenuItem menuItem = menuItemRepository.findOne(apiMenuItemChoice.getMenuItemId());
		if(menuItem == null)
			throw new WriterException("MenuItem details are missing");
		MenuItemChoice menuItemChoice = new MenuItemChoice();
		menuItemChoice = copyAPIChoiceToChoice(menuItemChoice, apiMenuItemChoice);
		menuItemChoice.setMenuItem(menuItem);
		menuItemChoiceRepository.save(menuItemChoice);		
		return copyChoiceToAPIChoice(menuItemChoice, apiMenuItemChoice);
	}

	private MenuItemChoice copyAPIChoiceToChoice(MenuItemChoice menuItemChoice, APIMenuItemChoice apiMenuItemChoice) {
		// TODO Auto-generated method stub
		if(apiMenuItemChoice.getName() != null)
			menuItemChoice.setName(apiMenuItemChoice.getName());
		if(apiMenuItemChoice.getDetail() != null)
			menuItemChoice.setDetail(apiMenuItemChoice.getDetail());
		if (apiMenuItemChoice.getRequired())
			menuItemChoice.setRequired(true);
		else
			menuItemChoice.setRequired(false);
		return menuItemChoice;
	}


	public APIMenuItemChoice updateMenuItemChoice(APIMenuItemChoice apiMenuItemChoice) {
		// TODO Auto-generated method stub
		MenuItemChoice menuItemChoice = menuItemChoiceRepository.findOne(apiMenuItemChoice.getId());
		Long tempId = menuItemChoice.getId();
		MenuItem menuItem = menuItemChoice.getMenuItem();
		
		menuItemChoice = copyAPIChoiceToChoice(menuItemChoice, apiMenuItemChoice);
		menuItemChoice.setId(tempId);
		menuItemChoice.setMenuItem(menuItem);
		menuItemChoiceRepository.save(menuItemChoice);
		return copyChoiceToAPIChoice(menuItemChoice, apiMenuItemChoice);
	}

	public void deleteMenuItemChoice(Long id) throws WriterException {
		// TODO Auto-generated method stub
		if (menuItemChoiceRepository.findOne(id) != null)
			menuItemChoiceRepository.delete(id);
		else {
			throw new WriterException ("MenItemChoice not found");
		}
		
	}	
	
	public List<MenuItemChoice> getMenuItemChoiceForOrder(Long id) {
		return menuItemChoiceRepository.findByMenuItemId(id);
	}
	
}
