package com.cassiopea.product_service;

import com.cassiopea.product_service.dto.ProductRequest;
import com.cassiopea.product_service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc ;

	@Autowired
	private ObjectMapper objectMapper;

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");
    @Autowired
    private ProductRepository productRepository;


	@DynamicPropertySource
	static void setProperties ( DynamicPropertyRegistry  registry ) {
		registry.add ( "spring.data.mongodb.uri" , mongoDBContainer::getReplicaSetUrl ) ;
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest requestData = getProductRequest () ;
		String requestDataString = objectMapper.writeValueAsString ( requestData ) ;

		mockMvc.perform (
				MockMvcRequestBuilders.post("/api/product" )
						.contentType ( MediaType.APPLICATION_JSON )
						.content ( requestDataString )
		).andExpect (
				status().isCreated()
		);

		// if product is created , length of products list should be 1 .
        Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private ProductRequest getProductRequest () {
		return ProductRequest.builder()
				.name ( "IPhone XR" )
				.description ( "IPhone XR" )
				.price (BigDecimal.valueOf(140000))
				.build () ;
	}

}
