package com.cassiopea.inventory_service.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Table ( name = "inventory_table" )
@Entity
public class Inventory {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id ;
    private String sku ;
    private Integer quantity ;
}
