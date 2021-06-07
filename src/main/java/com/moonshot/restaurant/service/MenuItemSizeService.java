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
import com.moonshot.restaurant.api.model.APIMenuItemSize;
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.MenuItemCategory;
import com.moonshot.restaurant.entity.MenuItemOption;
import com.moonshot.restaurant.entity.MenuItemSize;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.repository.MenuItemCategoryRepository;
import com.moonshot.restaurant.repository.MenuItemOptionRepository;
import com.moonshot.restaurant.repository.MenuItemRepository;
import com.moonshot.restaurant.repository.MenuItemSizeRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;

@Service
public class MenuItemSizeService {
	
	@Autowired
	private MenuItemSizeRepository menuItemSizeRepository;
		    
	@Autowired
	private MenuItemRepository menuItemRepository;

	public List<APIMenuItemSize> getMenuItemSizes(Long menuItemId) throws WriterException {
		// TODO Auto-generated method stub
		List<APIMenuItemSize> apiMenuItemSizeList = new ArrayList<>();
		if (menuItemRepository.findOne(menuItemId) != null){
			if (menuItemSizeRepository.findByMenuItemId(menuItemId) != null){
				apiMenuItemSizeList = copySizesToAPISizes(
				menuItemSizeRepository.findByMenuItemId(menuItemId), apiMenuItemSizeList);				
			}
			return apiMenuItemSizeList;
		}else {
			throw new WriterException("MenuItem not found");
		}
	}


	private List<APIMenuItemSize> copySizesToAPISizes(List<MenuItemSize> menuItemSizeList,
			List<APIMenuItemSize> apiMenuItemSizeList) {
		// TODO Auto-generated method stub
		MenuItemSize menuItemSize = null;
		APIMenuItemSize apiMenuItemSize = null;
		Iterator<MenuItemSize> menuItemSizeIte = menuItemSizeList.iterator();
		 while(menuItemSizeIte.hasNext()){
			 menuItemSize = menuItemSizeIte.next();
			 apiMenuItemSize = new APIMenuItemSize();
			 apiMenuItemSize = copySizeToAPISize(menuItemSize, apiMenuItemSize);
			 apiMenuItemSizeList.add(apiMenuItemSize);			 
		 }
		return apiMenuItemSizeList;
	}


	private APIMenuItemSize copySizeToAPISize(MenuItemSize menuItemSize,
			APIMenuItemSize apiMenuItemSize) {
		// TODO Auto-generated method stub
		apiMenuItemSize.setId(menuItemSize.getId());
		apiMenuItemSize.setName(menuItemSize.getName());
		apiMenuItemSize.setDescription(menuItemSize.getDescription());
		apiMenuItemSize.setPrice(menuItemSize.getPrice());
		apiMenuItemSize.setCurrency(menuItemSize.getCurrency());
		apiMenuItemSize.setMenuItemId(menuItemSize.getId());
		return apiMenuItemSize;
	}


	public APIMenuItemSize getMenuItemSize(Long id) throws WriterException {
		// TODO Auto-generated method stub
		if(menuItemSizeRepository.findOne(id) != null) 
			return copySizeToAPISize(menuItemSizeRepository.findOne(id), new APIMenuItemSize());
		else {
			throw new WriterException("MenuItemSize not found");
		}
	}

	public APIMenuItemSize addMenuItemSize(APIMenuItemSize apiMenuItemSize)throws WriterException {
		// TODO Auto-generated method stub
		MenuItem menuItem = menuItemRepository.findOne(apiMenuItemSize.getMenuItemId());
		if(menuItem == null)
			throw new WriterException("MenuItem details are missing");
		MenuItemSize menuItemSize = new MenuItemSize();
		menuItemSize = copyAPISizeToSize(menuItemSize, apiMenuItemSize);
		menuItemSize.setMenuItem(menuItem);
		menuItemSizeRepository.save(menuItemSize);		
		return copySizeToAPISize(menuItemSize, apiMenuItemSize);
	}

	private MenuItemSize copyAPISizeToSize(MenuItemSize menuItemSize, APIMenuItemSize apiMenuItemSize) {
		// TODO Auto-generated method stub
		if(apiMenuItemSize.getName() != null)
			menuItemSize.setName(apiMenuItemSize.getName());
		if(apiMenuItemSize.getDescription() != null)
			menuItemSize.setDescription(apiMenuItemSize.getDescription());
		if(apiMenuItemSize.getPrice() > 0)
			menuItemSize.setPrice(apiMenuItemSize.getPrice());
		if(apiMenuItemSize.getCurrency() != null)
			menuItemSize.setCurrency(apiMenuItemSize.getCurrency());
		return menuItemSize;
	}


	public APIMenuItemSize updateMenuItemSize(APIMenuItemSize apiMenuItemSize) {
		// TODO Auto-generated method stub
		MenuItemSize menuItemSize = menuItemSizeRepository.findOne(apiMenuItemSize.getId());
		Long tempId = menuItemSize.getId();
		MenuItem menuItem = menuItemSize.getMenuItem();
		
		menuItemSize = copyAPISizeToSize(menuItemSize, apiMenuItemSize);
		menuItemSize.setId(tempId);
		menuItemSize.setMenuItem(menuItem);
		menuItemSizeRepository.save(menuItemSize);
		return copySizeToAPISize(menuItemSize, apiMenuItemSize);
	}

	public void deleteMenuItemSize(Long id) throws WriterException {
		// TODO Auto-generated method stub
		if (menuItemSizeRepository.findOne(id)!= null)
			menuItemSizeRepository.delete(id);
		else {
			throw new WriterException("MenuItemSize not found");
		}
		
	}	
	
}
