package com.cassiopea.order_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table ( name = "order_line_items" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItem {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id ;
    private String sku ;
    private Integer quantity ;
    private BigDecimal price ;
}
