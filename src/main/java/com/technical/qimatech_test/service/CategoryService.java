package com.technical.qimatech_test.service;

import com.technical.qimatech_test.dto.CategoryDTO;
import com.technical.qimatech_test.entity.CategoryEntity;
import com.technical.qimatech_test.exception.NotFoundEntityException;
import com.technical.qimatech_test.mapper.ICategoryMapper;
import com.technical.qimatech_test.repository.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;

    public CategoryService(ICategoryRepository categoryRepository, ICategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO create(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);
        return categoryMapper.toDTO(categoryRepository.save(categoryEntity));
    }

    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    public CategoryDTO getById(UUID id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO)
                .orElseThrow(() -> new NotFoundEntityException("Category not found: id = " + id));
    }

    public CategoryEntity getCategoryEntity(CategoryDTO categoryDTO) {
        return categoryMapper.toEntity(getById(categoryDTO.getIdCategory()));
    }
}
