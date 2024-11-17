package com.cassiopea.inventory_service.service;


import com.cassiopea.inventory_service.dto.InventoryResponse;
import com.cassiopea.inventory_service.mapper.InventoryMapper;
import com.cassiopea.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository ;
    private final InventoryMapper inventoryMapper ;

    public List <InventoryResponse> getInventoryStocks (List < String > skus ) {

        // find inventory for skus in database :
        Map < String , InventoryResponse > inventoryMap = inventoryRepository.findBySkuIn ( skus )
                .stream ()
                .map ( inventoryMapper::mapToInventoryResponse )
                .collect (Collectors.toMap( InventoryResponse::getSku , response -> response ) ) ;

        List <InventoryResponse > responses =  skus.stream ()
                .map (
                        sku -> inventoryMap.getOrDefault (
                                sku ,
                                InventoryResponse.builder ()
                                        .sku ( sku )
                                        .isPresent ( false )
                                        .build ()
                        )
                )
                .toList () ;

        log.info( responses.toString());

        return responses ;
    }
}
