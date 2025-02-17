package com.technical.qimatech_test.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CategoryDTO extends RepresentationModel<CategoryDTO> {

    private UUID idCategory;

    @NotNull
    private String name;
}
