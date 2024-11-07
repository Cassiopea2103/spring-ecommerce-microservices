package com.cassiopea.inventory_service.controller;


import com.cassiopea.inventory_service.dto.InventoryResponse;
import com.cassiopea.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus ( HttpStatus.OK )
    public List < InventoryResponse > getInventoryStocks ( @RequestParam List < String > skus ) {
        return inventoryService.getInventoryStocks ( skus ) ;
    }

}
