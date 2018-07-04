package com.restaurant.orderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ItemServiceProxy itemServiceProxy;
	
	@PostMapping("/orders/type/{type}")
	public Order getOrder(@PathVariable String type, @RequestBody LineItem[] lineItems) {
		logger.info("order-service: type -> {}", type);
		Order order = new Order();
		for(LineItem lineItem : lineItems) {
			
			Item item = itemServiceProxy.getItem(lineItem.getItemId(), type);
			
			lineItem.setName(item.getName());
			lineItem.setPrice(item.getPrice());
			lineItem.setPort(item.getPort());
			order.addItem(lineItem);
		}
		return order;
	}
}
