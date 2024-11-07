package com.cassiopea.order_service.controller;


import com.cassiopea.order_service.dto.OrderRequest;
import com.cassiopea.order_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( "/api/order" )
public class OrderController {

    private final OrderService orderService ;

    public OrderController ( OrderService orderService ) {
        this.orderService = orderService ;
    }

    @PostMapping
    @ResponseStatus ( HttpStatus.CREATED )
    public String placeOrder (@RequestBody OrderRequest orderRequest ) {
        return orderService.placeOrder ( orderRequest ) ;
    }
}