package com.brunoalfeu.dscommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunoalfeu.dscommerce.dto.OrderDTO;
import com.brunoalfeu.dscommerce.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable("id") Long id) {
		OrderDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
}
