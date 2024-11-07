package com.cassiopea.product_service.mapper;

import com.cassiopea.product_service.dto.ProductResponse;
import com.cassiopea.product_service.model.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    public ProductResponse mapToProductResponse (Product product ) {
        return ProductResponse.builder ()
                .id ( product.getId () )
                .name ( product.getName () )
                .description ( product.getDescription () )
                .price ( product.getPrice () )
                .build() ;
    }

}
