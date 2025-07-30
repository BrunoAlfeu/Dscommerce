package com.brunoalfeu.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunoalfeu.dscommerce.entities.OrderItem;
import com.brunoalfeu.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}