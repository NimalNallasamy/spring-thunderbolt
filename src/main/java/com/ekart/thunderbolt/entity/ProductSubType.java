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
@Table(name = "product_sub_type")
@Builder
public class ProductSubType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_type_id")
    private Long subTypeId;

    @Column(name = "sub_type_name", columnDefinition = "varchar(200)")
    @NotBlank(message = "Product Sub Type Name cannot be null")
    private String subTypeName;

    @Column(name = "sub_type_description", columnDefinition = "varchar(1000)")
    @NotBlank(message = "Product Sub Type Description cannot be empty")
    private String subTypeDescription;
}