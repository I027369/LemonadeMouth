package com.moonshot.restaurant.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIMenuItemOption;
import com.moonshot.restaurant.api.model.APIMenuWithItemAndCategory;
import com.moonshot.restaurant.api.model.APIOrder;
import com.moonshot.restaurant.api.model.APIOrderItem;
import com.moonshot.restaurant.api.model.APIOrderItemOption;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.MenuItemChoice;
import com.moonshot.restaurant.entity.MenuItemOption;
import com.moonshot.restaurant.entity.Order;
import com.moonshot.restaurant.entity.OrderItem;
import com.moonshot.restaurant.entity.OrderItemOption;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantAddress;
import com.moonshot.restaurant.repository.MenuItemCategoryRepository;
import com.moonshot.restaurant.repository.MenuItemRepository;
import com.moonshot.restaurant.repository.MenuRepository;
import com.moonshot.restaurant.repository.OrderItemRepository;
import com.moonshot.restaurant.repository.OrderRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;
import com.moonshot.restaurant.util.BeanUtilsIgnoreNull;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private MenuItemCategoryRepository menuItemCategoryRepository;	
	
	@Autowired
	private MenuItemChoiceService menuItemChoiceService;
	
	@Autowired
	private MenuItemOptionService menuItemOptionService;
	
	public List<APIOrderItem> getAllOrderItems(Long orderId) {
		// TODO Auto-generated method stub
		return copyAllOrderItems(orderItemRepository.findByOrderId(orderId));	
	}

	public APIOrderItem getOrderItem(Long orderItemId) {
		// TODO Auto-generated method stub
		APIOrderItem apiOrderItem = new APIOrderItem();		
		return copyOrderItem(orderItemRepository.findOne(orderItemId), apiOrderItem);
	}

	public APIOrderItem addOrderItem(Long orderId, APIOrderItem orderItem) throws WriterException{
		// TODO Auto-generated method stub
		OrderItem localOrderItem = new OrderItem();
		MenuItem menuItem = menuItemRepository.findOne(orderItem.getMenuItemId());
		if (menuItem == null)
			throw new WriterException("Menu item not found");
		if (isChoiceMandatory(menuItem)) {
			if (orderItem.getOrderItemOption() == null || orderItem.getOrderItemOption().isEmpty()) {
				throw new WriterException ("Select atleast one option");
			}else {
				List<APIOrderItemOption> apiOrderItemOptionList = orderItem.getOrderItemOption();
				Iterator<APIOrderItemOption> apiOrderItemOptionListIterator = apiOrderItemOptionList.iterator();
				APIOrderItemOption apiOrderItemOption = null;
				MenuItemOption menuItemOption = null;
				while (apiOrderItemOptionListIterator.hasNext()) {
					apiOrderItemOption = apiOrderItemOptionListIterator.next();
					menuItemOption = menuItemOptionService.getMenuItemOptionForOrder(apiOrderItemOption.getMenuItemOptionId());		
					OrderItemOption orderItemOption = new OrderItemOption();
					BeanUtilsIgnoreNull.myCopyProperties(menuItemOption, orderItemOption);
					orderItemOption.setMenuItemOptionId(menuItemOption.getId());
					localOrderItem.getListOfOption().add(orderItemOption);
				}
			}
		}	
		
		//localOrderItem.setMenuItem(menuItem);		
		Order order = orderRepository.findOne(orderId);
		localOrderItem.setOrder(order);
		
		localOrderItem.setName(menuItem.getName());
		localOrderItem.setDescription(menuItem.getDescription());
		localOrderItem.setPrice(menuItem.getPrice());		
		localOrderItem.setQuantity(orderItem.getQuantity());
		localOrderItem.setNotes(orderItem.getNotes());		
		localOrderItem.setImage(menuItem.getImage());
		
		if(menuItem.getCategory() != null){
			localOrderItem.setSortOrder(menuItemCategoryRepository.findOne(menuItem.getCategory().getId()).getSortOrder());
		}		
						
		setAmount(menuItem, localOrderItem, order);
		orderRepository.save(order);
		orderItemRepository.save(localOrderItem);
		
		orderItem = copyOrderItem(localOrderItem, orderItem);
		
		return orderItem;
	}

	private boolean isChoiceMandatory(MenuItem menuItem) throws WriterException{
		// TODO Auto-generated method stub
		List menuItemChoice = menuItemChoiceService.getMenuItemChoiceForOrder(menuItem.getId());
		if (menuItemChoice != null ) {
			Iterator<MenuItemChoice> menuItemChoiceListIterator = menuItemChoice.iterator();
			MenuItemChoice localMenuItemChoice = null;
			while (menuItemChoiceListIterator.hasNext()) {
				localMenuItemChoice = menuItemChoiceListIterator.next();
				if (localMenuItemChoice.getRequired())
					return true;
			}
		}
		return false;
	}

	public APIOrderItem updateOrderItem(Long orderItemId, APIOrderItem orderItem) {
		// TODO Auto-generated method stub
		OrderItem existing = orderItemRepository.findOne(orderItemId);
		existing.setQuantity(orderItem.getQuantity());
		existing.setNotes(orderItem.getNotes());
		
		updateAmount(existing, orderItem);
		orderItemRepository.save(existing);	

		orderItem = copyOrderItem(existing, orderItem);	
		return orderItem;
		
	}

	public void deleteOrderItem(Long orderItemId) {
		// TODO Auto-generated method stub
		OrderItem orderItem = orderItemRepository.findOne(orderItemId);
		Order order = orderRepository.findOne(orderItem.getOrder().getId());
		order.setSubTotal(order.getTotalAmount()-orderItem.getAmount());	
		orderRepository.save(order);
		orderItemRepository.delete(orderItemId);
		//updateamount()
		
	}
	
	private void setAmount(MenuItem menuItem, OrderItem orderItem, Order order){
		orderItem.setAmount(menuItem.getPrice()*orderItem.getQuantity());
		
		Collection<OrderItemOption> orderItemOptionCollection = orderItem.getListOfOption();
		Iterator<OrderItemOption> orderItemOptionCollectionIterator = orderItemOptionCollection.iterator();
		OrderItemOption orderItemOption = null;
		while (orderItemOptionCollectionIterator.hasNext()) {
			orderItemOption = orderItemOptionCollectionIterator.next();
			orderItem.setAmount(orderItem.getAmount() + orderItemOption.getPrice());
		}	
		
		orderItem.setCurrency(menuItem.getCurrency());
		order.setSubTotal(order.getTotalAmount()+orderItem.getAmount());
	}

	private void updateAmount(OrderItem orderItem, APIOrderItem apiOrderItem) {
		// TODO Auto-generated method stub
		// update the order object as the order_item quantity may have changed 
		Order order = orderRepository.findOne(orderItem.getOrder().getId());
		order.setSubTotal(order.getTotalAmount()-orderItem.getAmount());		
		orderItem.setAmount(orderItem.getPrice()*apiOrderItem.getQuantity());
		orderItem.setCurrency(orderItem.getCurrency());
		order.setSubTotal(order.getTotalAmount()+orderItem.getAmount());
		orderRepository.save(order);
		
	}
	
	public List<APIOrderItem> copyAllOrderItems(List<OrderItem> orderItemList){
		OrderItem localOrderItem = null;
		APIOrderItem apiOrderItem = null;
		List<APIOrderItem> apiOrderItemList = new ArrayList<APIOrderItem>();
		Iterator<OrderItem> orderItemListIterator = orderItemList.iterator();
		while (orderItemListIterator.hasNext()) {
			localOrderItem = orderItemListIterator.next();
			apiOrderItem = new APIOrderItem();
			apiOrderItem = copyOrderItem(localOrderItem, apiOrderItem);			
			apiOrderItemList.add(apiOrderItem);
		}
		apiOrderItemList.sort(Comparator.comparing(APIOrderItem::getSortOrder));
		return apiOrderItemList;
	}
	
	private APIOrderItem copyOrderItem(OrderItem localOrderItem, APIOrderItem apiOrderItem){
		apiOrderItem.setId(localOrderItem.getId());
		/*if(localOrderItem.getMenuItem() != null)
			apiOrderItem.setMenuItemId(localOrderItem.getMenuItem().getId());
		*/
		if(localOrderItem.getOrder() != null)
			apiOrderItem.setOrderId(localOrderItem.getOrder().getId());
		apiOrderItem.setQuantity(localOrderItem.getQuantity());
		apiOrderItem.setAmount(localOrderItem.getAmount());
		apiOrderItem.setCurrency(localOrderItem.getCurrency());
		apiOrderItem.setNotes(localOrderItem.getNotes());
		apiOrderItem.setPrice(localOrderItem.getPrice());
		
		apiOrderItem.setName(localOrderItem.getName());
		apiOrderItem.setDescription(localOrderItem.getDescription());
		apiOrderItem.setImage(localOrderItem.getImage());
		apiOrderItem.setSortOrder(localOrderItem.getSortOrder());
		
		
		// remove all the options from APIOrderItem ( which would have come at time of creation)
		// populate all the options from OrderItem into APIOrderItem
		if (apiOrderItem.getOrderItemOption() != null)
			apiOrderItem.getOrderItemOption().clear();
		Collection<OrderItemOption> orderItemOptionCollection = localOrderItem.getListOfOption();
		Iterator<OrderItemOption> orderItemOptionCollectionIterator = orderItemOptionCollection.iterator();
		OrderItemOption orderItemOption = null;
		APIOrderItemOption apiOrderItemOption = null;
		while (orderItemOptionCollectionIterator.hasNext()) {
			orderItemOption = orderItemOptionCollectionIterator.next();
			apiOrderItemOption = new APIOrderItemOption();
			BeanUtilsIgnoreNull.myCopyProperties(orderItemOption, apiOrderItemOption);
			apiOrderItemOption.setMenuItemOptionId(orderItemOption.getMenuItemOptionId());
			if (apiOrderItem.getOrderItemOption() == null)
				apiOrderItem.setOrderItemOption(new ArrayList<APIOrderItemOption>());
			apiOrderItem.getOrderItemOption().add(apiOrderItemOption);
		}			
		
		return apiOrderItem;
	}

}
