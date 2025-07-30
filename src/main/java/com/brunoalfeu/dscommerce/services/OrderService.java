package com.brunoalfeu.dscommerce.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunoalfeu.dscommerce.dto.OrderDTO;
import com.brunoalfeu.dscommerce.dto.OrderItemDTO;
import com.brunoalfeu.dscommerce.entities.Order;
import com.brunoalfeu.dscommerce.entities.OrderItem;
import com.brunoalfeu.dscommerce.entities.OrderStatus;
import com.brunoalfeu.dscommerce.entities.Product;
import com.brunoalfeu.dscommerce.entities.User;
import com.brunoalfeu.dscommerce.repositories.OrderItemRepository;
import com.brunoalfeu.dscommerce.repositories.OrderRepository;
import com.brunoalfeu.dscommerce.repositories.ProductRepository;
import com.brunoalfeu.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserService service;
	
	 @Transactional(readOnly = true)
	    public OrderDTO findById(Long id) {
	        Optional<Order> result = repository.findById(id);
	        Order order = result.orElseThrow(
	                () -> new ResourceNotFoundException("Recurso não encontrado"));
	        OrderDTO dto = new OrderDTO(order);
	        return dto;
	    }

	  @Transactional
	    public OrderDTO insert(OrderDTO dto) {
	        Order order = new Order();
	        
	        order.setMoment(Instant.now());
	        order.setStatus(OrderStatus.WAITING_PAYMENT);

	        User user = service.authenticated();
	        order.setClient(user);

	        for(OrderItemDTO itemDto : dto.getItems()){
	            Product product = productRepository.getReferenceById(itemDto.getProductId());
	            OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
	            order.getItems().add(item);
	        }
	        repository.save(order);
	        orderItemRepository.saveAll(order.getItems());

	        return new OrderDTO(order);
	    }
}
