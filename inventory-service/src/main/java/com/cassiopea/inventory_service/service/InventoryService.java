package com.cassiopea.inventory_service.service;


import com.cassiopea.inventory_service.dto.InventoryResponse;
import com.cassiopea.inventory_service.mapper.InventoryMapper;
import com.cassiopea.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository ;
    private final InventoryMapper inventoryMapper ;

    public List <InventoryResponse> getInventoryStocks (List < String > skus ) {
        return inventoryRepository.findBySkuIn ( skus )
                .stream()
                .map ( inventoryMapper::mapToInventoryResponse )
                .toList () ;
    }
}
