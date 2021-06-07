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
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.MenuItemCategory;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.repository.MenuItemCategoryRepository;
import com.moonshot.restaurant.repository.MenuItemRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;

@Service
public class MenuItemCategoryService {
	
	@Autowired
	private MenuItemCategoryRepository menuItemCategoryRepository;
		    
	@Autowired
	private RestaurantRepository restaurantRepository;

	public List<APIMenuItemCategory> getAllMenuItemCategories(Long restaurantId) throws WriterException {
		// TODO Auto-generated method stub
		List<APIMenuItemCategory> apiMenuItemCategoryList = new ArrayList<>();
		if(menuItemCategoryRepository.findByRestaurantId(restaurantId) != null){
			apiMenuItemCategoryList = copyCategoryToAPICategoryList(
					menuItemCategoryRepository.findByRestaurantId(restaurantId), apiMenuItemCategoryList);
			return apiMenuItemCategoryList;
		}else {
			throw new WriterException("Restaurant not found");
		}
		
	}


	public APIMenuItemCategory getMenuItemCategory(Long menuItemCategoryId) throws WriterException{
		// TODO Auto-generated method stub
		if (menuItemCategoryRepository.findOne(menuItemCategoryId) != null)
			return copyCategoryToAPICategory(menuItemCategoryRepository.findOne(menuItemCategoryId), new APIMenuItemCategory());
		else {
			throw new WriterException("Menu item category not found");
		}
	}

	public APIMenuItemCategory addMenuItemCategory(APIMenuItemCategory apiMenuItemCategory)throws WriterException {
		// TODO Auto-generated method stub
		Restaurant rest = restaurantRepository.findOne(apiMenuItemCategory.getRestaurantId());
		if(rest == null)
			throw new WriterException("Restaurant details are missing");
		MenuItemCategory menuItemCategory = new MenuItemCategory();
		menuItemCategory = copyAPICategoryToMenuCategory(menuItemCategory, apiMenuItemCategory);
		menuItemCategory.setRestaurant(rest);
		menuItemCategoryRepository.save(menuItemCategory);		
		return copyCategoryToAPICategory(menuItemCategory, apiMenuItemCategory);
	}

	public APIMenuItemCategory updateMenuItemCategory(Long menuitemcategoryid, 
			APIMenuItemCategory apiMenuItemCategory) throws WriterException{
		// TODO Auto-generated method stub
		MenuItemCategory existingItem = menuItemCategoryRepository.findOne(menuitemcategoryid);
		if (existingItem != null) {
			Long tempId = existingItem.getId();
			Restaurant rest = existingItem.getRestaurant();
			
			existingItem = copyAPICategoryToMenuCategory(existingItem, apiMenuItemCategory);
			existingItem.setId(tempId);
			existingItem.setRestaurant(rest);
			menuItemCategoryRepository.save(existingItem);
			return copyCategoryToAPICategory(existingItem, apiMenuItemCategory);
		}
		else {
			throw new WriterException("Menu item category not found");
		} 
		
	}

	public void deleteMenuItemCategory(Long menuitemcategoryid)throws WriterException {
		// TODO Auto-generated method stub
		MenuItemCategory existingItem = menuItemCategoryRepository.findOne(menuitemcategoryid);
		if (existingItem != null) {
			menuItemCategoryRepository.delete(menuitemcategoryid);
		}else {
			throw new WriterException("Menu item category not found");
		} 
		
	}
	
	private List<APIMenuItemCategory> copyCategoryToAPICategoryList(List<MenuItemCategory> menuItemCategoryList,
			List<APIMenuItemCategory> apiMenuItemCategoryList) {
		// TODO Auto-generated method stub
		MenuItemCategory menuItemCategory = null;
		APIMenuItemCategory apiMenuItemCategory = null;
		Iterator<MenuItemCategory> menuItemCategoryIte = menuItemCategoryList.iterator();
		 while(menuItemCategoryIte.hasNext()){
			 menuItemCategory = menuItemCategoryIte.next();
			 apiMenuItemCategory = new APIMenuItemCategory();
			 apiMenuItemCategory = copyCategoryToAPICategory(menuItemCategory, apiMenuItemCategory);
			 apiMenuItemCategoryList.add(apiMenuItemCategory);			 
		 }
		return apiMenuItemCategoryList;
	}


	private APIMenuItemCategory copyCategoryToAPICategory(MenuItemCategory menuItemCategory,
			APIMenuItemCategory apiMenuItemCategory) {
		// TODO Auto-generated method stub
		apiMenuItemCategory.setId(menuItemCategory.getId());
		apiMenuItemCategory.setName(menuItemCategory.getName());
		apiMenuItemCategory.setSortOrder(menuItemCategory.getSortOrder());
		if(menuItemCategory.getRestaurant() != null)
			apiMenuItemCategory.setRestaurantId(menuItemCategory.getRestaurant().getId());
		return apiMenuItemCategory;
	}
	
	private MenuItemCategory copyAPICategoryToMenuCategory(MenuItemCategory menuItemCategory,
			APIMenuItemCategory apiMenuItemCategory){
		if(apiMenuItemCategory.getName() != null)
			menuItemCategory.setName(apiMenuItemCategory.getName());
		if(apiMenuItemCategory.getSortOrder() > -1)
			menuItemCategory.setSortOrder(apiMenuItemCategory.getSortOrder());
		return menuItemCategory;
	}

}
