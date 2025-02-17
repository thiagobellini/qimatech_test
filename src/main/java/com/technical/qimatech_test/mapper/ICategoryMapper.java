package com.technical.qimatech_test.mapper;

import com.technical.qimatech_test.dto.CategoryDTO;
import com.technical.qimatech_test.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {

    CategoryEntity toEntity(CategoryDTO categoryDTO);
    CategoryDTO toDTO(CategoryEntity categoryEntity);
}
