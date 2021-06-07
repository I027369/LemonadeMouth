package com.moonshot.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.moonshot.restaurant.api.model.PaymentRequest;
import com.moonshot.restaurant.entity.OrderPayment;
import com.moonshot.restaurant.repository.OrderRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;
import com.moonshot.restaurant.service.MenuService;
import com.moonshot.restaurant.service.OrderPaymentService;
import com.moonshot.restaurant.service.StripeClient;
import com.stripe.model.Charge;

@RestController
public class PaymentController {

    private StripeClient stripeClient;
    
	@Autowired
	private OrderPaymentService orderPayment;	

/*
    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }*/

    @RequestMapping(method=RequestMethod.POST, value="/payment/charge")
    public OrderPayment chargeCard(@RequestBody PaymentRequest request) throws Exception {
    	stripeClient = orderPayment.getStripeClient(request.getOrderId());
    	Charge charge = this.stripeClient.chargeCreditCard(request);
    	OrderPayment payment = orderPayment.addOrderPayment(charge, request);
    	return payment;
    }   

    
}
