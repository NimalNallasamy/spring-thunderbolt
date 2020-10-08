package com.ekart.thunderbolt.mapper;

import com.ekart.thunderbolt.DO.ProductSubTypeDO;
import com.ekart.thunderbolt.DO.ProductTypeDO;
import com.ekart.thunderbolt.entity.ProductSubType;
import com.ekart.thunderbolt.entity.ProductType;
import com.ekart.thunderbolt.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper(componentModel = "spring")
@AllArgsConstructor
public abstract class ProductTypeMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductTypeMapper.class);

    // Convert ProductType to ProductTypeDO
    @Mapping(target = "typeId", source = "typeId")
    @Mapping(target = "typeName", source = "typeName")
    @Mapping(target = "typeDescription", source = "typeDescription")
    public abstract ProductTypeDO mapToDo(ProductType productType);

   @InheritInverseConfiguration
    public abstract ProductType mapToDBDO(ProductTypeDO productTypeDO);

   @Mapping(target = "typeId", expression = "java((Long)compareAndReturn(savedProductType.getTypeId(),productTypeDO.getTypeId()))")
   @Mapping(target = "typeName", expression = "java((String)compareAndReturn(savedProductType.getTypeName(),productTypeDO.getTypeName()))")
   @Mapping(target = "typeDescription", expression = "java((String)compareAndReturn(savedProductType.getTypeDescription(),productTypeDO.getTypeDescription()))")
   public abstract ProductType patchMap(ProductTypeDO productTypeDO, ProductType savedProductType);


   Object compareAndReturn(Object oldValue, Object newValue){
        return CommonUtils.compareAndReturn(oldValue, newValue);
    }
}
