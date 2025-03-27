package com.pm.inventoryservice.repository;

import com.pm.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Boolean existsBySkuCodeAndQuantityGreaterThanEqual(String skuCode, Integer quantity);
}
