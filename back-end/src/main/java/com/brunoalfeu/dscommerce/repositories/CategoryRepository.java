package com.brunoalfeu.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunoalfeu.dscommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}