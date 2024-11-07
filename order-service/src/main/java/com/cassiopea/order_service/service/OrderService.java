package com.cassiopea.order_service.service;

import com.cassiopea.inventory_service.dto.InventoryResponse;
import com.cassiopea.order_service.dto.OrderRequest;
import com.cassiopea.order_service.mapper.OrderMapper;
import com.cassiopea.order_service.model.Order;
import com.cassiopea.order_service.model.OrderLineItem;
import com.cassiopea.order_service.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderMapper orderMapper ;
    private final OrderRepository orderRepository ;
    private final WebClient webClient ;

    public String placeOrder ( OrderRequest orderRequest ) {
        // create order item from request dto :
        Order order = orderMapper.mapToOrder ( orderRequest ) ;

        String inventoryURL = "http://localhost:8082/api/inventory" ;
        List < String > skus = order.getOrderLineItems()
                .stream ()
                .map ( OrderLineItem::getSku )
                .toList () ;

        // check inventory stock and place order if item available :
        InventoryResponse [] inventoryStocks = webClient.get (  )
                        .uri (
                                inventoryURL ,
                                uriBuilder -> uriBuilder.queryParam ( "skus" , skus ).build ()
                        )

                        .retrieve ()
                        .bodyToMono ( InventoryResponse[].class )
                        .block();




        boolean allProductsInStock = Arrays.stream ( inventoryStocks )
                        .allMatch (InventoryResponse::getIsPresent) ;

        System.out.println(allProductsInStock);
        if ( allProductsInStock ) {
            orderRepository.save ( order ) ;
            log.info ( "Placed successfully order {}" , order.getId () ) ;
            return "Placed successfully order " + order.getId () ;
        }
        else {
            throw  new IllegalArgumentException( "Products not available in stock !" ) ;
        }

    }
}
