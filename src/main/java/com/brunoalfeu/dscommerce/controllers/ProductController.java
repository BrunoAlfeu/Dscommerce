package com.brunoalfeu.dscommerce.controllers;

import com.brunoalfeu.dscommerce.dto.ProductDTO;
import com.brunoalfeu.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {
       Page<ProductDTO> dto = service.findAll(pageable);
        return dto;
    }

    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO dto) {
         return service.insert(dto);
    }
}
