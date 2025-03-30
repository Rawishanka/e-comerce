package com.pm.orderservice.client;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;


public interface InventoryClient{

    @GetExchange("/api/inventory/check")
    boolean isStockAvailable(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") Integer quantity);
}
