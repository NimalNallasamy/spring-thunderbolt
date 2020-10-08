package com.ekart.thunderbolt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.number.money.MonetaryAmountFormatter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@Builder
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    @NotBlank(message = "Name of the product cannot be empty")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "base_rate", columnDefinition = "decimal(10,2)")
//    @NotEmpty(message = "Base Rate of the product cannot be empty")
    private Double baseRate;

    @Column(name = "actual_rate", columnDefinition = "decimal(10,2)")
//    @NotBlank(message = "Actual Rate of the product cannot be empty")
    private Double actualRate;

    @Column(name = "discount_price", columnDefinition = "decimal(4,2) default 0.0")
    private Double discountPercentage;

    @Column(name = "is_in_stock", columnDefinition = "boolean default false")
    private Boolean isInStock;

    @Column(name = "image_url")
//    @NotBlank(message = "Image Url cannot be empty fora product")
    private String imageUrl;

    @Column(name = "rating", columnDefinition = "decimal(2,1) default 0.0")
    private Double rating;

    @Column(name = "is_featured", columnDefinition = "boolean default false")
    private Boolean isFeatured;

}
