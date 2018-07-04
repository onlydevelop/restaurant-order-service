package com.restaurant.orderservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

	private String LINE_ITEM_URL = "http://localhost:10000/items/{item}/type/{type}";
	
	@PostMapping("/orders/type/{type}")
	public Order getOrder(@PathVariable String type, @RequestBody LineItem[] lineItems) {
		Order order = new Order();
		for(LineItem lineItem : lineItems) {
			
			Map<String, String> uriVariables = new HashMap<>();
			uriVariables.put("item", Long.toString(lineItem.getItemId()));
			uriVariables.put("type", type);
			
			ResponseEntity<Item> responseEntity = new RestTemplate().getForEntity(LINE_ITEM_URL, Item.class, uriVariables);
			Item item = responseEntity.getBody();
			
			lineItem.setName(item.getName());
			lineItem.setPrice(item.getPrice());
			order.addItem(lineItem);
		}
		return order;
	}
}
