package com.cassiopea.inventory_service.mapper;

import com.cassiopea.inventory_service.dto.InventoryResponse;
import com.cassiopea.inventory_service.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryResponse mapToInventoryResponse (Inventory inventory ) {
        return InventoryResponse.builder()
                .sku ( inventory.getSku () )
                .isPresent ( inventory.getQuantity () > 0 )
                .build () ;
    }
}
