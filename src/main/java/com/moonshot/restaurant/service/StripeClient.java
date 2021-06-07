package com.moonshot.restaurant.service;

import com.moonshot.restaurant.api.model.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeClient {	
	
	public StripeClient() {
		super();
	}

   /* @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;
    
    @PostConstruct
    public void init() {*/
	
    public void setKey(String secretKey) {	
        Stripe.apiKey = secretKey;
    }

    public Charge chargeCreditCard(PaymentRequest request) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(request.getAmount() * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", request.getToken());
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

}
