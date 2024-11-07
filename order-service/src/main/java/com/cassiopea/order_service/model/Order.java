package com.cassiopea.order_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table ( name = "orders_table" )
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id ;
    private String orderNumber ;
    @OneToMany ( cascade = CascadeType.ALL )
    private List < OrderLineItem > orderLineItems ;

}
