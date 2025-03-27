package com.pm.inventoryservice.mapper;

import com.pm.inventoryservice.dto.InventoryRequestDto;
import com.pm.inventoryservice.dto.InventoryResponseDto;
import com.pm.inventoryservice.model.Inventory;

public class InventoryMapper {
    public static InventoryResponseDto toInventoryResponseDto(Inventory inventory) {
        InventoryResponseDto inventoryResponseDto = new InventoryResponseDto(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity());
        return inventoryResponseDto;
    }

    public static Inventory toInventory(InventoryRequestDto inventoryRequestDto) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryRequestDto.quantity());
        inventory.setSkuCode(inventoryRequestDto.skuCode());
        return inventory;
    }
}
