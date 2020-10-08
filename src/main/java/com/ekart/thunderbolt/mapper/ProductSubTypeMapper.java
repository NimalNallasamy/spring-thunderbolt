package com.ekart.thunderbolt.mapper;

import com.ekart.thunderbolt.DO.ProductSubTypeDO;
import com.ekart.thunderbolt.DO.ProductTypeDO;
import com.ekart.thunderbolt.entity.ProductSubType;
import com.ekart.thunderbolt.entity.ProductType;
import com.ekart.thunderbolt.utils.CommonUtils;
import lombok.AllArgsConstructor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper(componentModel = "spring")
@AllArgsConstructor
public abstract class ProductSubTypeMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductSubTypeMapper.class);

    // Sub Type DO to DB Mapping
    @Mapping( target = "subTypeId", source = "subTypeId" )
    @Mapping( target = "subTypeName", source = "subTypeName" )
    @Mapping( target =  "subTypeDescription", source = "subTypeDescription" )
    public abstract ProductSubTypeDO mapDbToDo(ProductSubType productSubType);

    // Sub Type DB to DO Mapping
    @InheritInverseConfiguration
    public abstract ProductSubType mapDoToDb(ProductSubTypeDO productSubTypeDO);

    @Mapping(target = "subTypeId", expression = "java((Long)compareAndReturn(savedProductSubType.getSubTypeId(),productSubTypeDO.getSubTypeId()))")
    @Mapping(target = "subTypeName", expression = "java((String)compareAndReturn(savedProductSubType.getSubTypeName(),productSubTypeDO.getSubTypeName()))")
    @Mapping(target = "subTypeDescription", expression = "java((String)compareAndReturn(savedProductSubType.getSubTypeDescription(),productSubTypeDO.getSubTypeDescription()))")
    public abstract ProductSubType patchMap(ProductSubTypeDO productSubTypeDO, ProductSubType savedProductSubType);

    Object compareAndReturn(Object oldValue, Object newValue){
        return CommonUtils.compareAndReturn(oldValue, newValue);
    }

}
