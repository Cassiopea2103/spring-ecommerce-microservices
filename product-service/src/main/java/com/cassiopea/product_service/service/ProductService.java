package com.cassiopea.product_service.service;


import com.cassiopea.product_service.dto.ProductRequest;
import com.cassiopea.product_service.dto.ProductResponse;
import com.cassiopea.product_service.mapper.ProductMapper;
import com.cassiopea.product_service.model.Product;
import com.cassiopea.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository ;
    private final ProductMapper productMapper ;


    @Transactional
    public String createProduct (ProductRequest productRequest ) {
        Product product = Product.builder()
                .name ( productRequest.getName() )
                .description ( productRequest.getDescription() )
                .price ( productRequest.getPrice () )
                .build () ;
        productRepository.save ( product ) ;
        log.info( "Product {} saved successfully!", product.getId() );

        return "Product " + product.getId() + " saved successfully!" ;
    }


    public List <ProductResponse> getAllProducts() {
        List <Product> products = productRepository.findAll() ;
        return products.stream().map (productMapper::mapToProductResponse ).toList() ;
    }


}
