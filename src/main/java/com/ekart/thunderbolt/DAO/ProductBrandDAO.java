package com.ekart.thunderbolt.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBrandDAO {

    private Long brandId;
    private String brandName;
    private String brandDescription;


    public String toString(){
        return "\n Brand ID : "+getBrandId()+
                "\n Brand Name : "+getBrandName()+
                "\n Brand Description : "+getBrandDescription();
    }
}
