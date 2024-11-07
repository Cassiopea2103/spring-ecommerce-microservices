package com.cassiopea.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class InventoryResponse {

    private String sku ;
    private Boolean isPresent ;
}
