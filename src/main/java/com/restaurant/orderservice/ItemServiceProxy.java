package com.restaurant.orderservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="api-gateway-server")
@RibbonClient(name="item-service")
public interface ItemServiceProxy {
	@GetMapping("/item-service/items/{id}/type/{type}")
	public Item getItem(@PathVariable("id") Long id, @PathVariable("type") String type);
}
