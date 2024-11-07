package com.cassiopea.inventory_service.controller;


import com.cassiopea.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping ( "/{sku}" )
    @ResponseStatus ( HttpStatus.OK )
    public String isInStock (@PathVariable ("sku") String sku ) {
        return inventoryService.isInStock ( sku ) ;
    }

}
