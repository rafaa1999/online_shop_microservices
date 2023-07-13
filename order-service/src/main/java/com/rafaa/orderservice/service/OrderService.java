package com.rafaa.orderservice.service;

import java.util.UUID;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rafaa.orderservice.dto.OrderLineItemsDto;
import com.rafaa.orderservice.dto.OrderRequest;
import com.rafaa.orderservice.model.Order;
import com.rafaa.orderservice.model.OrderLineItems;
import com.rafaa.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	
	private final OrderRepository orderRepository;
	
	public void placeOrder(@RequestBody OrderRequest orderRequest) {
		
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		 List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
					.stream()
					.map(this::mapTpDto)
					.collect(Collectors.toList());
		order.setOrderLineItemsList(orderLineItems);
		
		orderRepository.save(order);
	}
	
	public OrderLineItems mapTpDto(OrderLineItemsDto orderLineItemsDto) {
		
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
		
	}
}
