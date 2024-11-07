package com.cassiopea.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemDTO {

    private Long id ;
    private String sku ;
    private Integer quantity ;
    private BigDecimal price ;
}
