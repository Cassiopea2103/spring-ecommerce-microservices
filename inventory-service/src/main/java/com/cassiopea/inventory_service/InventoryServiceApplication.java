package com.cassiopea.inventory_service;

import com.cassiopea.inventory_service.model.Inventory;
import com.cassiopea.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData (InventoryRepository inventoryRepository ) {
		return args -> {
			Inventory inventory0 = new Inventory () ;
			inventory0.setSku ( "Huawei P9" ) ;
			inventory0.setQuantity ( 0 ) ;

			Inventory inventory1 = new Inventory () ;
			inventory1.setSku ( "Iphone13" ) ;
			inventory1.setQuantity ( 100 ) ;

			inventoryRepository.save ( inventory0  ) ;
			inventoryRepository.save ( inventory1  ) ;
		};
	}
}
