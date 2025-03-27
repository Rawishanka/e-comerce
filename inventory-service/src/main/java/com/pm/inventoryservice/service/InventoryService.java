package com.pm.inventoryservice.service;

import com.pm.inventoryservice.dto.InventoryRequestDto;
import com.pm.inventoryservice.dto.InventoryResponseDto;
import com.pm.inventoryservice.mapper.InventoryMapper;
import com.pm.inventoryservice.model.Inventory;
import com.pm.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryResponseDto addToInventory(InventoryRequestDto inventoryRequestDto) {
        Inventory inventory = InventoryMapper.toInventory(inventoryRequestDto);
        this.inventoryRepository.save(inventory);
        return InventoryMapper.toInventoryResponseDto(inventory);
    }
    public List<InventoryResponseDto> getAllInventory() {
        List<Inventory> inventoryList = this.inventoryRepository.findAll();
        List<InventoryResponseDto> inventoryResponseDtoList = inventoryList.stream().map(InventoryMapper::toInventoryResponseDto).toList();
        return inventoryResponseDtoList;
    }

    public Boolean checkAvailability(String skuCode, Integer quantity) {
        Boolean result = this.inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
        return result;
    }
}
