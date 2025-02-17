package com.technical.qimatech_test.mapper;

import com.technical.qimatech_test.dto.ProductDTO;
import com.technical.qimatech_test.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ICategoryMapper.class)
public interface IProductMapper {

    ProductEntity toEntity(ProductDTO productDTO);
    ProductDTO toDTO(ProductEntity productEntity);
}
