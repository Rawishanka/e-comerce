package com.pm.inventoryservice.controller;

import com.pm.inventoryservice.dto.InventoryRequestDto;
import com.pm.inventoryservice.dto.InventoryResponseDto;
import com.pm.inventoryservice.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Tag(name = "Inventory", description = "This is API end point for Inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an Inventory")
    public ResponseEntity<InventoryResponseDto> createInventory(@RequestBody InventoryRequestDto inventoryRequestDto) {
        var result = inventoryService.addToInventory(inventoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all inventory list")
    public ResponseEntity<List<InventoryResponseDto>> getAllInventory() {
        var result = inventoryService.getAllInventory();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check availability of Inventory")
    public ResponseEntity<Boolean> checkAvailability(String skuCode, Integer quantity) {
        var result = inventoryService.checkAvailability(skuCode, quantity);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }


}
