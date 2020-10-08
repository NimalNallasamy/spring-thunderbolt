package com.ekart.thunderbolt.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeRelDO {

    private Long typeRelId;
    private Long productId;
    private Long productBrandId;
    private Long productTypeId;
    private Long productSubTypeId;
    private String productName;
    private String productBrandName;
    private String productTypeName;
    private String productSubTypeName;

    public String toString(){
        return "\n Type Rel ID : "+getTypeRelId()+
                "\n Product ID : "+getProductId()+
                "\n Product Name : "+getProductName()+
                "\n Product Brand ID : "+getProductBrandId()+
                "\n Product Brand Name : "+getProductBrandName()+
                "\n Product Type ID : "+getProductTypeId()+
                "\n Product Type Name : "+getProductTypeName()+
                "\n Product Sub Type ID : "+getProductSubTypeId()+
                "\n Product Sub Type Name : "+getProductSubTypeName();
    }

}
