package com.moonshot.restaurant.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIMenu;
import com.moonshot.restaurant.api.model.APIOrder;
import com.moonshot.restaurant.api.model.APIOrderItem;
import com.moonshot.restaurant.controller.ResturantAddressController;
import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.Order;
import com.moonshot.restaurant.entity.OrderItem;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantAddress;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.repository.AppUserRepository;
import com.moonshot.restaurant.repository.OrderRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;
import com.moonshot.restaurant.repository.RestaurantTableRepository;
import com.moonshot.restaurant.util.BeanUtilsIgnoreNull;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private RestaurantTableRepository tableRepository;
	
	@Autowired
	private OrderItemService orderItemService;
	
	public List<Order> getAllOrdersByRestaurant(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findByRestaurantId(id);
	}

	public APIOrder getOrder(Long id) throws WriterException{
		// TODO Auto-generated method stub		
		APIOrder apiOrder = new APIOrder();
		Order order = orderRepository.findOne(id);
		if (order != null){
			BeanUtilsIgnoreNull.myCopyProperties(order, apiOrder);
			apiOrder.setRestaurantId(order.getRestaurant().getId());
			apiOrder.setTableId(order.getRestaurantTable().getId());
			apiOrder.setAppUserId(order.getAppUser().getId());
			apiOrder.setOrderItem(orderItemService.getAllOrderItems(order.getId()));
			return apiOrder;
		}
		else 
			throw new WriterException("Order not found");

	}
	
	public void deleteOrder(Long id) throws WriterException{
		// TODO Auto-generated method stub
		Order localOrder = orderRepository.findOne(id);
		if (localOrder == null)
			throw new WriterException ("Order not found");
		List<APIOrderItem> apiOrderItemList = orderItemService.getAllOrderItems(id);		
		Iterator<APIOrderItem> orderItemListIterator = apiOrderItemList.iterator();
		APIOrderItem apiOrderItem = null;
		while (orderItemListIterator.hasNext()) {	
			apiOrderItem = orderItemListIterator.next();
			orderItemService.deleteOrderItem(apiOrderItem.getId());
		}	
		orderRepository.delete(id);		
	}

	public List<Order> findByRestaurantTableId(Long id){
		return orderRepository.findByRestaurantTableId(id);
	}
	public List<Order> findByAppUserId(Long id){
		return orderRepository.findByAppUserId(id);		
	}

	public APIOrder createOrder(APIOrder apiOrder) throws WriterException{
		// TODO Auto-generated method stub
		Order newOrder = new Order();
		if(apiOrder.getRestaurantId() != null){
			Restaurant rest = restaurantRepository.findOne(apiOrder.getRestaurantId());
			newOrder.setRestaurant(rest);
			if (rest.getCurrency() != null)
				newOrder.setCurrency(rest.getCurrency());
			else 
				newOrder.setCurrency("$");
		}
		if(apiOrder.getTableId() != null){
			newOrder.setRestaurantTable(tableRepository.findOne(apiOrder.getTableId()));
		}
		if(apiOrder.getAppUserId() != null){
			newOrder.setAppUser(appUserRepository.findOne(apiOrder.getAppUserId()));
		}
		newOrder.setStatus("New");
		newOrder.setTimestamp(OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.ofHours(0)));
		newOrder.setOrderType(apiOrder.getOrderType());
		newOrder.setMessage(apiOrder.getMessage());
		
		
		try {
			orderRepository.save(newOrder);	
			List<APIOrderItem> apiOrderItemList = createOrderItems(newOrder, apiOrder);
			BeanUtilsIgnoreNull.myCopyProperties(newOrder, apiOrder);
		} catch (Exception e){
			// delete the newly created order in case of exception while creating order items. 
			orderRepository.delete(newOrder.getId());
			throw new WriterException(e.getMessage());
		}
		
		return apiOrder; 
	}

	private List<APIOrderItem> createOrderItems(Order newOrder, APIOrder apiOrder) throws WriterException{
	// TODO Auto-generated method stub		
		List<APIOrderItem> apiOrderItemList = new ArrayList<APIOrderItem>();
		APIOrderItem apiOrderItem = null;
		if(apiOrder.getOrderItem() != null){
			Iterator<APIOrderItem> orderItemListIterator = apiOrder.getOrderItem().iterator();
			while (orderItemListIterator.hasNext()) {	
				apiOrderItem = orderItemListIterator.next();
				apiOrderItem = orderItemService.addOrderItem(newOrder.getId(), apiOrderItem);
				apiOrderItemList.add(apiOrderItem);
			}			
		}
		return apiOrderItemList;
	}

	public APIOrder updateOrder(APIOrder order, Long id) throws WriterException {
		// TODO Auto-generated method stub
		Order localOrder = orderRepository.findOne(id);
		if (localOrder == null)
			throw new WriterException ("Order not found");
		if(order.getTableId() != null){
			localOrder.setRestaurantTable(tableRepository.findOne(order.getTableId()));
		}
		//localOrder.setStatus(order.getStatus());
		localOrder.setOrderType(order.getOrderType());
		orderRepository.save(localOrder);	
		BeanUtilsIgnoreNull.myCopyProperties(localOrder, order);
		return order;
	}
	
	public APIOrder updateOrderStatus(Long orderId, String localStatus) throws WriterException {
		// TODO Auto-generated method stub
		Order localOrder = orderRepository.findOne(orderId);
		if (localOrder == null)
			throw new WriterException("Order not found");		
		localOrder.setStatus(localStatus);;
		orderRepository.save(localOrder);
		APIOrder apiOrder = new APIOrder();
		apiOrder = copyOrder(localOrder, apiOrder);
		return apiOrder;
		
	}
	
	public APIOrder copyOrder(Order order, APIOrder apiOrder){
		
		apiOrder.setId(order.getId());
		apiOrder.setTimestamp(order.getTimestamp());
		apiOrder.setTotalAmount(order.getTotalAmount());
		apiOrder.setCurrency(order.getCurrency());
		apiOrder.setTableId(order.getRestaurantTable().getId());
		apiOrder.setRestaurantId(order.getRestaurant().getId());
		apiOrder.setAppUserId(order.getAppUser().getId());
		apiOrder.setStatus(order.getStatus());
		apiOrder.setMessage(order.getMessage());
		

		List<APIOrderItem> apiOrderItemList = orderItemService.getAllOrderItems(order.getId());		
		apiOrder.setOrderItem(apiOrderItemList);		
		return apiOrder;
	}
	
	public List<APIOrder> getActiveOrderForUser(Long userId, String localStatus) {
		// TODO Auto-generated method stub
		List<APIOrder> apiOrderList = new ArrayList<>();
		Order localOrder = null;
		APIOrder apiOrder = null;
		Iterator<Order> orderListIterator = orderRepository.findByAppUserId(userId).iterator();
		while (orderListIterator.hasNext()) {
			localOrder = orderListIterator.next();
			apiOrder =  new APIOrder();
			if(localOrder.getStatus().equalsIgnoreCase(localStatus)){
				apiOrder = copyOrder(localOrder, apiOrder);
				apiOrderList.add(apiOrder);
			}				
		}		
		return apiOrderList;
		
	}
	
	public List<APIOrder> getActiveOrderForTable(Long tableId, String localStatus) {
		// TODO Auto-generated method stub
		
		List<APIOrder> apiOrderList = new ArrayList<>();
		Order localOrder = null;
		APIOrder apiOrder = null;
		Iterator<Order> orderListIterator = orderRepository.findByRestaurantTableId(tableId).iterator();
		while (orderListIterator.hasNext()) {
			localOrder = orderListIterator.next();
			apiOrder =  new APIOrder();
			if(localOrder.getStatus().equalsIgnoreCase(localStatus)){
				apiOrder = copyOrder(localOrder, apiOrder);
				apiOrderList.add(apiOrder);
			}				
		}		
		return apiOrderList;		
	}
	
	public List<APIOrder> getActiveOrdersByRestaurant(Long restaurantId, String localStatus) {
		// TODO Auto-generated method stub
		Order localOrder = null;
		APIOrder apiOrder = null;
		List<APIOrder> apiOrderList = new ArrayList<APIOrder>();
		Iterator<Order> orderListIterator = getAllOrdersByRestaurant(restaurantId).iterator();
		while (orderListIterator.hasNext()) {
			localOrder = orderListIterator.next();
			apiOrder = new APIOrder();
			if (localStatus.equalsIgnoreCase("New")) {
				if(localOrder.getStatus().equalsIgnoreCase(localStatus)){
					apiOrder = copyOrder(localOrder, apiOrder);
					apiOrderList.add(apiOrder);
				}	
			} else if (localStatus.equalsIgnoreCase("In-Process")){
				if(localOrder.getStatus().equalsIgnoreCase(localStatus) || localOrder.getStatus().equalsIgnoreCase("Completed")){
					apiOrder = copyOrder(localOrder, apiOrder);
					apiOrderList.add(apiOrder);
				}	
			}else if (localStatus.equalsIgnoreCase("Finished")){
				if(localOrder.getStatus().equalsIgnoreCase(localStatus) || localOrder.getStatus().equalsIgnoreCase("Canceled")){
					apiOrder = copyOrder(localOrder, apiOrder);
					apiOrderList.add(apiOrder);
				}	
			}
			
		}	
		return apiOrderList;
	}
	
	public List<APIOrder> getAllOrderByRestaurant(Long restaurantId) {
		// TODO Auto-generated method stub		
		return copyAllOrderToAPIOrder(getAllOrdersByRestaurant(restaurantId));
	}

	public List<APIOrder> getAllOrderForTable(Long tableId) {
		// TODO Auto-generated method stub		
		return copyAllOrderToAPIOrder(orderRepository.findByRestaurantTableId(tableId));
	}
	
	private List<APIOrder> copyAllOrderToAPIOrder(List<Order> orderList){
		Order localOrder = null;
		APIOrder apiOrder = null;
		List<APIOrder> apiOrderList = new ArrayList<APIOrder>();
		Iterator<Order> orderListIterator = orderList.iterator();
		while (orderListIterator.hasNext()) {
			localOrder = orderListIterator.next();
			apiOrder = new APIOrder();
			apiOrder = copyOrder(localOrder, apiOrder);			
			apiOrderList.add(apiOrder);
		}
		return apiOrderList;
	}
	
	public List<APIOrder> getAllOrdersForUser(Long userId) {
		// TODO Auto-generated method stub
		List<APIOrder> apiOrderList = new ArrayList<>();
		Order localOrder = null;
		APIOrder apiOrder = null;
		Iterator<Order> orderListIterator = orderRepository.findByAppUserId(userId).iterator();
		while (orderListIterator.hasNext()) {
			localOrder = orderListIterator.next();
			apiOrder =  new APIOrder();
			apiOrder = copyOrder(localOrder, apiOrder);
			apiOrderList.add(apiOrder);							
		}		
		return apiOrderList;		
	}
}
