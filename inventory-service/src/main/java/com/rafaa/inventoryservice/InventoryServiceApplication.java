package com.rafaa.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rafaa.inventoryservice.model.Inventory;
import com.rafaa.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {

			Inventory inventory = new Inventory();
			inventory.setSkuCode("pc_one");
			inventory.setQuantity(100);
			
			Inventory inventory1 = new Inventory();
			inventory.setSkuCode("computer two");
			inventory.setQuantity(0);
			
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);

		};
	}
}
