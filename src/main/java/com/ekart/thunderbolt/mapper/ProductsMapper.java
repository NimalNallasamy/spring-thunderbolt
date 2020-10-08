package com.ekart.thunderbolt.mapper;

import antlr.StringUtils;
import com.ekart.thunderbolt.DO.ProductsDO;
import com.ekart.thunderbolt.entity.Products;
import com.ekart.thunderbolt.repository.ProductsRepository;
import com.ekart.thunderbolt.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring")
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProductsMapper {

//    @Autowired
    private ProductsRepository productsRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductsMapper.class);
//    private ProductBrandRepository productBrandRepository;
//    private ProductSubTypeRepository productSubTypeRepository;
//    private ProductTypeRelRepository productTypeRelRepository;

    /**
     * Construct ProductsDO Data Response
     */
    @Mapping(target = "productId", source = "productsDO.productId")
    @Mapping(target = "productName", source = "productsDO.productName")
    @Mapping(target = "productDescription", source = "productsDO.productDescription")
    @Mapping(target = "baseRate", expression = "java(roundOffDouble(productsDO.getBaseRate(), \"Base Rate\"))")
    @Mapping(target = "discountPercentage", expression = "java(roundOffDouble(productsDO.getDiscountPercentage(), \"Discount Percentage\"))")
    @Mapping(target = "actualRate", expression = "java(calculateActualRate(productsDO))")
    @Mapping(target = "imageUrl", source = "productsDO.imageUrl")
    @Mapping(target = "rating", constant = "0.0")
    @Mapping(target = "isInStock", constant = "false")
    @Mapping(target = "isFeatured", constant = "false")
    public abstract Products map(ProductsDO productsDO);

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "productName", source = "productName")
    @Mapping(target = "productDescription", source = "productDescription")
    @Mapping(target = "baseRate", source = "baseRate")
    @Mapping(target = "discountPercentage", source = "discountPercentage")
    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "actualRate", source = "actualRate")
    @Mapping(target = "isInStock", source = "isInStock")
    @Mapping(target = "isFeatured", source = "isFeatured")
    @Mapping(target = "rating", source = "rating")
    public abstract ProductsDO mapToProducts(Products products);

    @Mapping(target = "productId", expression = "java((Long)compareAndReturn(savedProducts.getProductId(), productsDO.getProductId()))")
    @Mapping(target = "productName", expression = "java((String)compareAndReturn(savedProducts.getProductName(),productsDO.getProductName()))")
    @Mapping(target = "productDescription", expression = "java((String)compareAndReturn(productsDO.getProductDescription(),productsDO.getProductDescription()))")
    @Mapping(target = "baseRate", expression = "java((Double)compareAndReturn(savedProducts.getBaseRate(),(productsDO.getBaseRate())))")
    @Mapping(target = "discountPercentage", expression = "java((Double)compareAndReturn(savedProducts.getDiscountPercentage(),(productsDO.getDiscountPercentage())))")
    @Mapping(target = "actualRate", expression = "java((Double)calculateActualPatchRate(productsDO, savedProducts))")
    @Mapping(target = "imageUrl", expression = "java((String)compareAndReturn(savedProducts.getImageUrl(), productsDO.getImageUrl()))")
    @Mapping(target = "rating", expression = "java((Double)compareAndReturn(savedProducts.getRating(), productsDO.getRating()))")
    @Mapping(target = "isInStock", expression = "java((Boolean)compareAndReturn(savedProducts.getIsInStock(),productsDO.getIsInStock()))")
    @Mapping(target = "isFeatured", expression = "java((Boolean)compareAndReturn(savedProducts.getIsFeatured(),productsDO.getIsFeatured()))")
    public abstract Products patchDO(ProductsDO productsDO, Products savedProducts);

    Object compareAndReturn(Object oldValue, Object newValue){
        return CommonUtils.compareAndReturn(oldValue, newValue);
    }

    Double roundOffDouble(Double rate, String field) {
        return CommonUtils.roundOffDouble(rate, field);
    }

    Double calculateActualRate(ProductsDO productsDO){
        Double baseRate = roundOffDouble(productsDO.getBaseRate(), "Base Rate");
        Double discountedPrice = productsDO.getDiscountPercentage();

//        if(productsDO.getBaseRate() != null){
//
//        }

        LOGGER.info("Actual Rate Calculation ::: "+( baseRate - (baseRate * (discountedPrice / 100))));
        return ( baseRate - roundOffDouble(baseRate * (discountedPrice / 100), "Actual Rate"));
    }

    Double calculateActualPatchRate(ProductsDO productsDO, Products savedProducts){
        Double baseRate = roundOffDouble((Double)compareAndReturn(savedProducts.getBaseRate(),(productsDO.getBaseRate())), "Base Rate");
        Double discountedPrice = roundOffDouble((Double)compareAndReturn(savedProducts.getBaseRate(), (productsDO.getDiscountPercentage())), "Discount Percentage");

        LOGGER.info("Actual Rate Calculation ::: "+( baseRate - (baseRate * (discountedPrice / 100))));
        return ( baseRate - roundOffDouble(baseRate * (discountedPrice / 100), "Actual Rate"));
    }
}
