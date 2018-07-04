package com.restaurant.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired
	private ItemServiceProxy itemServiceProxy;
	
	@PostMapping("/orders/type/{type}")
	public Order getOrder(@PathVariable String type, @RequestBody LineItem[] lineItems) {
		Order order = new Order();
		for(LineItem lineItem : lineItems) {
			
			Item item = itemServiceProxy.getItem(lineItem.getItemId(), type);
			
			lineItem.setName(item.getName());
			lineItem.setPrice(item.getPrice());
			order.addItem(lineItem);
		}
		return order;
	}
}
