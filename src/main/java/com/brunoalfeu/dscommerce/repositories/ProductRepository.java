package com.brunoalfeu.dscommerce.repositories;

import com.brunoalfeu.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
