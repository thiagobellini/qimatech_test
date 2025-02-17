package com.technical.qimatech_test.service;

import com.technical.qimatech_test.controller.ProductController;
import com.technical.qimatech_test.dto.CategoryDTO;
import com.technical.qimatech_test.dto.ProductDTO;
import com.technical.qimatech_test.entity.CategoryEntity;
import com.technical.qimatech_test.entity.ProductEntity;
import com.technical.qimatech_test.exception.NotFoundEntityException;
import com.technical.qimatech_test.mapper.IProductMapper;
import com.technical.qimatech_test.repository.IProductRepository;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private static final String NOT_FOUND = "Product not found: id = ";

    private final IProductRepository productRepository;
    private final IProductMapper productMapper;
    private final CategoryService categoryService;

    public ProductService(IProductRepository productRepository, IProductMapper productMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    public ProductDTO create(ProductDTO productDTO) {
        productDTO.setCategory(categoryService.getById(productDTO.getCategory().getIdCategory()));

        ProductEntity productEntity = productMapper.toEntity(productDTO);
        ProductDTO productDTOCreated = productMapper.toDTO(productRepository.save(productEntity));
        addLinks(productDTOCreated);

        return productDTOCreated;
    }

    public List<ProductDTO> getAll() {
        List<ProductDTO> productDTOList = productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .toList();
        productDTOList.forEach(this::addLinks);

        return productDTOList;
    }

    public ProductDTO getById(UUID id) {
        ProductDTO productDTO = productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND + id));
        addLinks(productDTO);

        return productDTO;
    }

    public ProductDTO update(UUID id, ProductDTO productDTO) {
        ProductDTO productDTOUpdated = productRepository.findById(id)
                .map(productEntity -> {
                    productEntity.setName(productDTO.getName());
                    productEntity.setDescription(productDTO.getDescription());
                    productEntity.setPrice(productDTO.getPrice());
                    productEntity.setAvailable(productDTO.getAvailable());

                    if (!productEntity.getCategory().getName().equals(productDTO.getCategory().getName())) {
                        productEntity.setCategory(getCategoryEntity(productDTO.getCategory()));
                    }

                    return productMapper.toDTO(productRepository.save(productEntity));
                })
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND + id));
        addLinks(productDTOUpdated);

        return productDTOUpdated;
    }

    public void delete(UUID id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND + id));

        productRepository.delete(productEntity);
    }

    private CategoryEntity getCategoryEntity(CategoryDTO categoryDTO) {
        return categoryService.getCategoryEntity(categoryDTO);
    }

    private void addLinks(ProductDTO productDTO) {
        Link getByIdLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getById(productDTO.getIdProduct())).withRel("Get by Id").withType("GET");
        Link getAllLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getAll()).withRel("Get all").withType("GET");
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).update(productDTO.getIdProduct(), productDTO)).withRel("Update").withType("PUT");
        Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).delete(productDTO.getIdProduct())).withRel("Delete").withType("DELETE");

        productDTO.add(getByIdLink, getAllLink, updateLink, deleteLink);
    }
}
