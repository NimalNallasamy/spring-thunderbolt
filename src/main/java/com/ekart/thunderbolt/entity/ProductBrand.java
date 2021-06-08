package com.ekart.thunderbolt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_brand")
@Builder
public class ProductBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "brand_name", columnDefinition = "varchar(200)")
    @NotBlank(message = "Product Brand Name cannot be null")
    private String brandName;

    @Column(name = "brand_description", columnDefinition = "varchar(1000)")
    @NotBlank(message = "Product Brand Description cannot be empty")
    private String brandDescription;
}
