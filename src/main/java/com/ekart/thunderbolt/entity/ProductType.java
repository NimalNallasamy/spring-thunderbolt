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
@Table(name = "product_type")
@Builder
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "type_name", columnDefinition = "varchar(200)")
    @NotBlank(message = "Product Type Name cannot be null")
    private String typeName;

    @Column(name = "type_description", columnDefinition = "varchar(1000)")
    @NotBlank(message = "Product Type Description cannot be empty")
    private String typeDescription;
}
