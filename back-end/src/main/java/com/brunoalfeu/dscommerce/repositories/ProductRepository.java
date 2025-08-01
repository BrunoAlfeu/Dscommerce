package com.brunoalfeu.dscommerce.repositories;

import com.brunoalfeu.dscommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT obj FROM Product obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%',:name, '%'))")
    Page<Product> searchByName(@Param("name") String name, Pageable pageable);
}

