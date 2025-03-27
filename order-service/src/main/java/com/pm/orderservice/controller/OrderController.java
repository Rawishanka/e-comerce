package com.pm.orderservice.controller;

import com.pm.orderservice.dto.OrderRequestDto;
import com.pm.orderservice.dto.OrderResponseDto;
import com.pm.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Tag(name = "Order", description = "API End points for order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Place an order")
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        var result = orderService.placeOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
