package com.moonshot.restaurant.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIMenuItem;
import com.moonshot.restaurant.api.model.APIMenuItemDetails;
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.repository.MenuItemCategoryRepository;
import com.moonshot.restaurant.repository.MenuItemRepository;
import com.moonshot.restaurant.repository.MenuRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;

@Service
public class MenuItemService {
	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private MenuItemCategoryRepository menuItemCategoryRepository;
	
	@Autowired
	private MenuItemChoiceService menuItemChoiceService;
	
	@Autowired
	private MenuItemSizeService menuItemSizeService;
/*
	public List<MenuItem> getAllMenuItems(Long restaurantId) {
		// TODO Auto-generated method stub
		return menuItemRepository.findByRestaurantId(restaurantId);
	}
*/
	public APIMenuItemDetails getMenuItem(Long menuItemId) throws WriterException {
		// TODO Auto-generated method stub
		if (menuItemRepository.findOne(menuItemId) != null){
			APIMenuItemDetails apiMenuDetails = new APIMenuItemDetails();
			apiMenuDetails = (APIMenuItemDetails)copyMenuItem(menuItemRepository.findOne(menuItemId), apiMenuDetails);
			apiMenuDetails.setChoices(menuItemChoiceService.getMenuItemChoices(menuItemId));
			apiMenuDetails.setSizes(menuItemSizeService.getMenuItemSizes(menuItemId));
			return apiMenuDetails;
		}else
			throw new WriterException("Menu item not found");
	}
			

	public APIMenuItem addMenuItem(APIMenuItem apiMenuItem) throws WriterException {
		// TODO Auto-generated method stub
		MenuItem menuItem = new MenuItem();
		menuItem = copyAPIMenuItem(menuItem, apiMenuItem);	
		menuItem.setStatus("Draft");
		menuItemRepository.save(menuItem);		
		return copyMenuItem(menuItem, apiMenuItem);
	}

	public APIMenuItem updateMenuItem(Long menuItemId, APIMenuItem apiMenuItem) throws WriterException {
		// TODO Auto-generated method stub
		MenuItem menuItem = menuItemRepository.findOne(menuItemId);
		if (menuItem == null)
			throw new WriterException("Menu Item not found");
		menuItem = copyAPIMenuItem(menuItem, apiMenuItem);
		menuItemRepository.save(menuItem);
		apiMenuItem.setId(menuItemId);
		return copyMenuItem(menuItem, apiMenuItem);
	}
	
	public APIMenuItem updateMenuItemStatus(Long menuItemId, String status) throws WriterException {
		// TODO Auto-generated method stub
		MenuItem menuItem = menuItemRepository.findOne(menuItemId);
		if (menuItem == null)
			throw new WriterException("Menu Item not found");
		menuItem.setStatus(status);
		menuItemRepository.save(menuItem);
		return copyMenuItem(menuItem, new APIMenuItem());
	}

	public void deleteMenuItem(Long menuItemId) throws WriterException {
		// TODO Auto-generated method stub
		MenuItem menuItem = menuItemRepository.findOne(menuItemId);
		if (menuItem == null)
			throw new WriterException("Menu Item not found");
		menuItemRepository.delete(menuItemId);
		
	}

	public List<APIMenuItem> getAllMenuItems(Long menuid) throws WriterException{
		// TODO Auto-generated method stub
		List<MenuItem> menuItemList = menuItemRepository.findByMenuId(menuid);
		MenuItem menuItem = null;
		APIMenuItem apiMenuItem = null;
		if (menuItemList != null){
			Iterator<MenuItem> menuItemListIte = menuItemList.iterator();
			List<APIMenuItem> apiMenuItemList = new ArrayList<>();
			while(menuItemListIte.hasNext()){
				apiMenuItem = new APIMenuItem();
				menuItem = menuItemListIte.next();
				apiMenuItem = copyMenuItem(menuItem, apiMenuItem);
				apiMenuItemList.add(apiMenuItem);
			}
			return apiMenuItemList;
		}
		else{
			throw new WriterException("Menu not found");
		}
	}
	
	
	public List<APIMenuItem> getActiveMenuItems(Long menuid) throws WriterException{
		// TODO Auto-generated method stub
		List<MenuItem> menuItemList = menuItemRepository.findByMenuId(menuid);
		MenuItem menuItem = null;
		APIMenuItem apiMenuItem = null;
		if (menuItemList != null){
			Iterator<MenuItem> menuItemListIte = menuItemList.iterator();
			List<APIMenuItem> apiMenuItemList = new ArrayList<>();
			while(menuItemListIte.hasNext()){
				apiMenuItem = new APIMenuItem();
				menuItem = menuItemListIte.next();
				if(!menuItem.getStatus().equalsIgnoreCase("Published"))
					continue;
				apiMenuItem = copyMenuItem(menuItem, apiMenuItem);
				apiMenuItemList.add(apiMenuItem);
			}
			return apiMenuItemList;
		}
		else{
			throw new WriterException("Menu not found");
		}
	}
	

	public APIMenuItem copyMenuItem(MenuItem menuItem, APIMenuItem apiMenuItem) {
		// TODO Auto-generated method stub
		apiMenuItem.setId(menuItem.getId());
		apiMenuItem.setName(menuItem.getName());
		apiMenuItem.setDescription(menuItem.getDescription());
		apiMenuItem.setPrice(menuItem.getPrice());
		apiMenuItem.setCurrency(menuItem.getCurrency());
		apiMenuItem.setImage(menuItem.getImage());
		apiMenuItem.setType(menuItem.getType());		
		apiMenuItem.setStatus(menuItem.getStatus());
		apiMenuItem.setItemGroup(menuItem.getItemGroup());
		
		if(menuItem.getCategory()!=null) {
			apiMenuItem.setCategoryId(menuItem.getCategory().getId());
			apiMenuItem.setCategoryName(menuItemCategoryRepository.findOne(menuItem.getCategory().getId()).getName());
		}
				
		if(menuItem.getRestaurant()!=null)
			apiMenuItem.setRestaurantId(menuItem.getRestaurant().getId());	
		
		if(menuItem.getMenu()!=null){
			Iterator<Menu> menuListIte = menuItem.getMenu().iterator();
			List<Long> apiMenu = new ArrayList<>();
			while(menuListIte.hasNext()){
				apiMenu.add(menuListIte.next().getId());				
			}	
			apiMenuItem.setMenuId(apiMenu);
		}
		
		return apiMenuItem;
	}
	
	
	
	public MenuItem copyAPIMenuItem(MenuItem menuItem, APIMenuItem apiMenuItem) throws WriterException{
		//BeanUtils.copyProperties(apiMenuItem, menuItem);
		if(apiMenuItem.getCategoryId()!=null)
			menuItem.setCategory(menuItemCategoryRepository.findOne(apiMenuItem.getCategoryId()));
		if(apiMenuItem.getDescription()!=null)
			menuItem.setDescription(apiMenuItem.getDescription());
		if(apiMenuItem.getImage()!=null && apiMenuItem.getImage().length() > 0)
			menuItem.setImage(apiMenuItem.getImage());			
		if(apiMenuItem.getItemGroup()!=null && apiMenuItem.getItemGroup().length() > 0)
			menuItem.setItemGroup(apiMenuItem.getItemGroup());
		if(apiMenuItem.getName()!=null && apiMenuItem.getName().length() > 0)
			menuItem.setName(apiMenuItem.getName());
		if(apiMenuItem.getPrice() > 0)
			menuItem.setPrice(apiMenuItem.getPrice());		
		if(apiMenuItem.getType()!= null && apiMenuItem.getType().length() > 0)
			menuItem.setType(apiMenuItem.getType());
		
		if(apiMenuItem.getRestaurantId()!=null){
			menuItem.setRestaurant(restaurantRepository.findOne(apiMenuItem.getRestaurantId()));			
			if (menuItem.getRestaurant().getCurrency() != null && menuItem.getRestaurant().getCurrency().length() > 0)
				menuItem.setCurrency(menuItem.getRestaurant().getCurrency());
			else
				menuItem.setCurrency("$");
		}
				
		if(apiMenuItem.getMenuId()!=null){
			Iterator<Long> apiMenuIterator = apiMenuItem.getMenuId().iterator();
			while(apiMenuIterator.hasNext()){
				Menu menu = menuRepository.findOne(apiMenuIterator.next().longValue());
				if(menu == null)
					throw new WriterException("Menu not found");
				if(!checkIfMenuExist(menuItem, menu.getId()))
					menuItem.getMenu().add(menu);
			}
		}
				
		return menuItem;
	}
	
	private boolean checkIfMenuExist(MenuItem menuItem, Long id) {
		Iterator<Menu> menuIterator = menuItem.getMenu().iterator();
		while(menuIterator.hasNext()){
			if(id == menuIterator.next().getId())				
				return true;
		}		
		return false;
	}
	

/*	public void addMenuToMenuItem(Long menuId, Long menuItemId) {
		// TODO Auto-generated method stub
		MenuItem menuItem = menuItemRepository.findOne(menuItemId);
		Menu menu = menuRepository.findOne(menuId);
		menuItem.getMenu().add(menu);
		menuItemRepository.save(menuItem);
		
	}

	public void delMenuFromMenuItem(Long menuId, Long menuItemId) {
		// TODO Auto-generated method stub
		MenuItem menuItem = menuItemRepository.findOne(menuItemId);
		Menu menu = menuRepository.findOne(menuId);
		menuItem.getMenu().remove(menu);
		menuItemRepository.save(menuItem);
		
	}
*/

}
