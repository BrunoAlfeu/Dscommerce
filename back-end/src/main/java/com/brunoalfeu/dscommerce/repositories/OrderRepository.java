package com.brunoalfeu.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunoalfeu.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}