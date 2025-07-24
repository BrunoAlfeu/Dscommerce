package com.brunoalfeu.dscommerce.controllers;

import com.brunoalfeu.dscommerce.dto.ProductDTO;
import com.brunoalfeu.dscommerce.entities.Product;
import com.brunoalfeu.dscommerce.repositories.ProductRepository;
import com.brunoalfeu.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value ="/{id}")
    public ProductDTO findById(@PathVariable("id") Long id) {
        ProductDTO dto = service.findById(id);
        return dto;
    }
}
