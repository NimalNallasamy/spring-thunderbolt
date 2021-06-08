package com.ekart.thunderbolt.mapper;

import com.ekart.thunderbolt.DAO.ProductBrandDAO;
import com.ekart.thunderbolt.entity.ProductBrand;
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
//@NoArgsConstructor
public abstract class ProductBrandMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductBrandMapper.class);

    //Convert ProductBrandDO to ProductsBrand
    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "brandName", source = "brandName")
    @Mapping(target = "brandDescription", source = "brandDescription")
    public abstract ProductBrand mapToDBData(ProductBrandDAO productBrandDAO);

    // Convert ProductsBrand to ProductsBrandDO
    @InheritInverseConfiguration
    public abstract ProductBrandDAO mapToDO(ProductBrand productBrand);

    @Mapping(target = "brandId", expression = "java((Long)compareAndReturn(savedProductBrand.getBrandId(),productBrandDAO.getBrandId()))")
    @Mapping(target = "brandName", expression = "java((String)compareAndReturn(savedProductBrand.getBrandName(),productBrandDAO.getBrandName()))")
    @Mapping(target = "brandDescription", expression = "java((String)compareAndReturn(savedProductBrand.getBrandDescription(),productBrandDAO.getBrandDescription()))")
    public abstract ProductBrand patchMap(ProductBrandDAO productBrandDAO, ProductBrand savedProductBrand);

    Object compareAndReturn(Object oldValue, Object newValue){
        return CommonUtils.compareAndReturn(oldValue, newValue);
    }
}
