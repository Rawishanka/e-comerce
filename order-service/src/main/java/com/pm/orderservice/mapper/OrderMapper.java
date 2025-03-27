package com.pm.orderservice.mapper;

import com.pm.orderservice.dto.OrderRequestDto;
import com.pm.orderservice.dto.OrderResponseDto;
import com.pm.orderservice.model.Order;

public class OrderMapper {
    static  public OrderResponseDto toOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto= new OrderResponseDto(order.getId(),order.getOrderNumber(), order.getSkuCode(),order.getPrice(), order.getQuantity());
        return orderResponseDto;
    }
    static  public Order toOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setOrderNumber(orderRequestDto.orderNumber());
        order.setPrice(orderRequestDto.price());
        order.setQuantity(orderRequestDto.quantity());
        order.setSkuCode(orderRequestDto.skuCode());
        return order;
    }
}
