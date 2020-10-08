package com.ekart.thunderbolt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_type_rel")
@Builder
public class ProductTypeRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_rel_id")
    private Long typeRelId;

//    @Column(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Products products;

//    @Column(name = "type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    private ProductType productType;

//    @Column(name = "sub_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_type_id", referencedColumnName = "sub_type_id")
    private ProductSubType productSubType;

//    @Column(name = "brand_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
    private ProductBrand productBrand;
}
