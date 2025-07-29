package com.brunoalfeu.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunoalfeu.dscommerce.dto.OrderDTO;
import com.brunoalfeu.dscommerce.entities.Order;
import com.brunoalfeu.dscommerce.repositories.OrderRepository;
import com.brunoalfeu.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	 @Transactional(readOnly = true)
	    public OrderDTO findById(Long id) {
	        Optional<Order> result = repository.findById(id);
	        Order order = result.orElseThrow(
	                () -> new ResourceNotFoundException("Recurso não encontrado"));
	        OrderDTO dto = new OrderDTO(order);
	        return dto;
	    }
}
