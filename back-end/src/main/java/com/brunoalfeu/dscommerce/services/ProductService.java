package com.brunoalfeu.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brunoalfeu.dscommerce.dto.CategoryDTO;
import com.brunoalfeu.dscommerce.dto.ProductDTO;
import com.brunoalfeu.dscommerce.dto.ProductMinDTO;
import com.brunoalfeu.dscommerce.entities.Category;
import com.brunoalfeu.dscommerce.entities.Product;
import com.brunoalfeu.dscommerce.repositories.CategoryRepository;
import com.brunoalfeu.dscommerce.repositories.ProductRepository;
import com.brunoalfeu.dscommerce.services.exceptions.DatabaseException;
import com.brunoalfeu.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    
    @Autowired
    private CategoryRepository catRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result = repository.findById(id);
        Product product = result.orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        ProductDTO dto = new ProductDTO(product);
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        Page<Product> result = repository.searchByName(name, pageable);
        return result.map(x -> new ProductMinDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
            Product entity = new Product();
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }


    }


    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        
        entity.getCategories().clear();
        for (CategoryDTO catDto : dto.getCategories()) {
        	Category cat = catRepository.getReferenceById(catDto.getId());
        	cat.setId(catDto.getId());
        	entity.getCategories().add(cat);
        }
    }


}
