package com.cassiopea.order_service.service;

import com.cassiopea.order_service.dto.OrderRequest;
import com.cassiopea.order_service.mapper.OrderMapper;
import com.cassiopea.order_service.model.Order;
import com.cassiopea.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderMapper orderMapper ;
    private final OrderRepository orderRepository ;

    public String placeOrder ( OrderRequest orderRequest ) {
        // create order item from request dto :
        Order order = orderMapper.mapToOrder ( orderRequest ) ;

        // persist order :
        orderRepository.save ( order ) ;

        log.info ( "Placed successfully order {}" , order.getId () ) ;
        return "Placed successfully order " + order.getId () ;
    }
}
