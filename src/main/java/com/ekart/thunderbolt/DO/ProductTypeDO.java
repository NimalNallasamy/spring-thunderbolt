package com.ekart.thunderbolt.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeDO {

    private Long typeId;
    private String typeName;
    private String typeDescription;

    public String toString(){
        return "\n Type ID : "+getTypeId()+
                "\n Type Name : "+getTypeName()+
                "\n Type Description : "+getTypeDescription();
    }

}
