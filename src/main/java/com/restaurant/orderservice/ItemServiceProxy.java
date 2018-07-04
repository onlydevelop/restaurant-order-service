package com.restaurant.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="item-service", url="http://localhost:10000")
public interface ItemServiceProxy {
	@GetMapping("/items/{id}/type/{type}")
	public Item getItem(@PathVariable("id") Long id, @PathVariable("type") String type);
}