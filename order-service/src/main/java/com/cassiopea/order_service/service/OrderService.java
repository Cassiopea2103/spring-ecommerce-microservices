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
        Order order = orderMapper.mapToOrder(orderRequest);

        String inventoryURL = "http://localhost:8082/api/inventory";

        List<String> skus = order.getOrderLineItems()
                .stream()
                .map(OrderLineItem::getSku)
                .toList();

        // check inventory stock and place order if item available :
        InventoryResponse[] inventoryItems = webClient.get()
                .uri(
                        inventoryURL,
                        uriBuilder -> uriBuilder.queryParam("skus", skus).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        if ( inventoryItems == null ) {
            throw new IllegalStateException ( "No inventory items found " ) ;
        }

        // check if all products are present in stock :
        boolean allProductsInStock = Arrays.stream(inventoryItems ).allMatch(InventoryResponse::getIsPresent);


        if (allProductsInStock) {
            // persist order :
            orderRepository.save(order);
            log.info("Placed successfully order {}", order.getId());
            return "Placed successfully order " + order.getId();
        } else {
            log.error("Not all products are present in inventory");
            for ( InventoryResponse item : inventoryItems ) {
                if ( !item.getIsPresent() ) {
                    log.error("Item {} is not present in inventory", item.getSku());
                }
            }
            return "Not all products are present in inventory";
        }
    }
}
