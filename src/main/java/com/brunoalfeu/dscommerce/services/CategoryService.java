package com.brunoalfeu.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunoalfeu.dscommerce.dto.CategoryDTO;
import com.brunoalfeu.dscommerce.entities.Category;
import com.brunoalfeu.dscommerce.repositories.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository catRepository;

   

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> result = catRepository.findAll();
        return result.stream().map(x -> new CategoryDTO(x)).toList();
    }
}
