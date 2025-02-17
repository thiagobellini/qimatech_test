package com.technical.qimatech_test.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="TbProduct")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Setter(AccessLevel.NONE)
    @Column(name = "IdProduct")
    private UUID idProduct;

    @Column(name = "NameProduct", nullable = false)
    private String name;

    @Column(name = "DescriptionProduct", length = 50, nullable = false)
    private String description;

    @Column(name = "PriceProduct", nullable = false)
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "IdCategoryProduct", nullable = false)
    private CategoryEntity category;

    @Column(name = "AvailableProduct", nullable = false)
    private Boolean available;
}
