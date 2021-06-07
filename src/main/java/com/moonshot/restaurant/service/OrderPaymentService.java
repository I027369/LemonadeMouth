package com.moonshot.restaurant.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.moonshot.restaurant.api.model.PaymentRequest;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.Order;
import com.moonshot.restaurant.entity.OrderPayment;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.repository.MenuItemRepository;
import com.moonshot.restaurant.repository.OrderPaymentRepository;
import com.moonshot.restaurant.repository.OrderRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;
import com.moonshot.restaurant.repository.RestaurantTableRepository;
import com.stripe.model.Charge;

@Service
public class OrderPaymentService {
	@Autowired
	private OrderPaymentRepository orderPaymentRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	public List<OrderPayment> getPaymentByOrder(Long orderId) {
		// TODO Auto-generated method stub
		return orderPaymentRepository.findByOrderId(orderId);
	}

	public OrderPayment addOrderPayment(Charge charge, PaymentRequest request) throws WriterException, IOException{
		// TODO Auto-generated method stub
		OrderPayment payment = new OrderPayment();
		payment.setAmount(charge.getAmount());
		payment.setCurrency(charge.getCurrency());
		payment.setPaymentId(charge.getId());
		payment.setOrderId(request.getOrderId());
		payment.setStatus(charge.getStatus());
		payment.setProvider("Stripe");
		orderPaymentRepository.save(payment);
		return payment;
				
	}
	
    public StripeClient getStripeClient(Long orderId){
    	StripeClient stripeClient = new StripeClient();
    	Order order = orderRepository.findOne(orderId);
    	Restaurant rest = order.getRestaurant();
    	stripeClient.setKey(rest.getStripeSecretKey());
    	return stripeClient;
    }
}
