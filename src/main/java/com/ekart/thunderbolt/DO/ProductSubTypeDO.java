package com.ekart.thunderbolt.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSubTypeDO {

    private Long subTypeId;
    private String subTypeName;
    private String subTypeDescription;

    public String toString(){
        return "\n Sub Type ID : "+getSubTypeId()+
                "\n Sub Type Name : "+getSubTypeName()+
                "\n Sub Type Description : "+getSubTypeDescription();
    }

}
