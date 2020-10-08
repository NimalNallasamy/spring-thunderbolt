package com.ekart.thunderbolt.DO;

import com.ekart.thunderbolt.entity.ProductBrand;
import com.ekart.thunderbolt.entity.ProductSubType;
import com.ekart.thunderbolt.entity.ProductType;
import com.ekart.thunderbolt.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeRelResponseDO {

    private Long typeRelId;
    private Products products;
    private ProductBrand productBrand;
    private ProductType productType;
    private ProductSubType productSubType;

    public String toString(){
        return "\n Type Rel ID : "+getTypeRelId()+
                "\n Product : "+getProducts()+
                "\n Product Brand : "+getProductBrand()+
                "\n Product Type : "+getProductType()+
                "\n Product Sub Type : "+getProductSubType();
    }

}
