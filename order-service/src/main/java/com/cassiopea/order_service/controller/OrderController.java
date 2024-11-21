package com.cassiopea.order_service.controller;


import com.cassiopea.order_service.dto.OrderRequest;
import com.cassiopea.order_service.model.Order;
import com.cassiopea.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    @CircuitBreaker( name = "inventory" , fallbackMethod = "fallbackMethod" )
    @TimeLimiter( name = "inventory" , fallbackMethod = "timeoutFallback")
    @ResponseStatus ( HttpStatus.CREATED )
    public CompletableFuture < String > placeOrder (@RequestBody OrderRequest orderRequest ) {
        return CompletableFuture.supplyAsync( () -> orderService.placeOrder ( orderRequest ) ) ;
    }


    public CompletableFuture < String > fallbackMethod ( OrderRequest request , RuntimeException exception ) {
        return CompletableFuture.supplyAsync (() -> "Oops! Something went wrong . Come back later..." );
    }

    public CompletableFuture < String > timeoutFallback ( OrderRequest request ) {
        return CompletableFuture.supplyAsync(() -> "Request timeout!!! Please try again later..." );
    }
}
