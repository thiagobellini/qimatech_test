package com.technical.qimatech_test.dto;

import com.technical.qimatech_test.validator.TwoDecimalPlaces;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ProductDTO extends RepresentationModel<ProductDTO> {

    private UUID idProduct;

    @NotNull
    private String name;

    @NotNull
    @Size(max = 50, message = "Description must not exceed 50 characters")
    private String description;

    @NotNull
    @Positive(message = "Price must be positive")
    @TwoDecimalPlaces
    private BigDecimal price;

    @NotNull
    private CategoryDTO category;

    @NotNull
    private Boolean available;
}
