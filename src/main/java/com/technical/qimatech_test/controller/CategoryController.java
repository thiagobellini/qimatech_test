package com.technical.qimatech_test.controller;

import com.technical.qimatech_test.dto.CategoryDTO;
import com.technical.qimatech_test.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO categoryDTOCreated = categoryService.create(categoryDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTOCreated);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> categoryDTOList = categoryService.getAll();

        return ResponseEntity.ok(categoryDTOList);
    }
}
