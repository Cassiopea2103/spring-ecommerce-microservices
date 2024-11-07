package com.cassiopea.inventory_service.service;


import com.cassiopea.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository ;

    public String isInStock ( String sku ) {
        log.info ( String.valueOf ( inventoryRepository.findBySku ( sku ).isPresent()  ) ) ;
        return inventoryRepository.findBySku(sku).isPresent()
                ? "Item is present in stock" : "No items available" ;
    }

}
