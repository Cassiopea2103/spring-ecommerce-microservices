package com.cassiopea.order_service.mapper;

import com.cassiopea.order_service.dto.OrderLineItemDTO;
import com.cassiopea.order_service.dto.OrderRequest;
import com.cassiopea.order_service.model.Order;
import com.cassiopea.order_service.model.OrderLineItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderMapper {

    public Order mapToOrder ( OrderRequest orderRequest ) {

        // new order instance :
        Order order = new Order () ;

        // set the order number :
        order.setOrderNumber (UUID.randomUUID().toString() ) ;

        // map orderRequest orderLineItem DTO to order line item model :
        List < OrderLineItem > orderLineItems = orderRequest.getOrderLineItemDTOs()
                .stream ()
                .map ( this::mapToOrderLineItem )
                .toList() ;

        order.setOrderLineItems ( orderLineItems ) ;

        return order ;
    }


    private OrderLineItem mapToOrderLineItem ( OrderLineItemDTO orderLineItemDTO ) {
        return OrderLineItem.builder()
                .sku ( orderLineItemDTO.getSku () )
                .quantity ( orderLineItemDTO.getQuantity () )
                .price ( orderLineItemDTO.getPrice () )
                .build () ;
    }
}
