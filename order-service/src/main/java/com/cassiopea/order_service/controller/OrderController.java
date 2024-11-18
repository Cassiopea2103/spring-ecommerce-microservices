package com.cassiopea.order_service.controller;


import com.cassiopea.order_service.dto.OrderRequest;
import com.cassiopea.order_service.model.Order;
import com.cassiopea.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ( "/api/order" )
public class OrderController {

    private final OrderService orderService ;

    @GetMapping
    @ResponseStatus ( HttpStatus.OK )
    public List <Order > getOrders () {
        return orderService.getOrders () ; 
    }

    @PostMapping
    @ResponseStatus ( HttpStatus.CREATED )
    public String placeOrder (@RequestBody OrderRequest orderRequest ) {
        return orderService.placeOrder ( orderRequest ) ;
    }
}
