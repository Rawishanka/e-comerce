package com.pm.orderservice.service;


import com.pm.orderservice.client.InventoryClient;
import com.pm.orderservice.dto.OrderRequestDto;
import com.pm.orderservice.dto.OrderResponseDto;
import com.pm.orderservice.mapper.OrderMapper;
import com.pm.orderservice.model.Order;
import com.pm.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository repository;
    private InventoryClient inventoryClient;
    @Autowired
    public OrderService(OrderRepository repository, InventoryClient inventoryClient) {
        this.repository = repository;
        this.inventoryClient = inventoryClient;
    }

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        var isAvailable = inventoryClient.isStockAvailable(orderRequestDto.skuCode(), orderRequestDto.quantity());
        if (isAvailable) {
            Order order = OrderMapper.toOrder(orderRequestDto);
            order = repository.save(order);
            return OrderMapper.toOrderResponseDto(order);
        }else{
            throw new RuntimeException("Stock is not available");
        }

    }
}
