package com.brunoalfeu.dscommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brunoalfeu.dscommerce.dto.CategoryDTO;
import com.brunoalfeu.dscommerce.dto.ProductMinDTO;
import com.brunoalfeu.dscommerce.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	 @GetMapping
	    public ResponseEntity<List<CategoryDTO>> findAll() {
	        List<CategoryDTO> dto = service.findAll();
	        return ResponseEntity.ok(dto);
	    }

}
