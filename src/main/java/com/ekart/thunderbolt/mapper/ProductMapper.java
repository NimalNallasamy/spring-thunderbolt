package com.ekart.thunderbolt.mapper;

import com.ekart.thunderbolt.DAO.ProductDAO;
import com.ekart.thunderbolt.entity.Product;
import com.ekart.thunderbolt.repository.ProductRepository;
import com.ekart.thunderbolt.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper(componentModel = "spring")
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProductMapper {

    private ProductRepository productRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductMapper.class);

    @Mapping(target = "productId", source = "productDAO.productId")
    @Mapping(target = "productName", source = "productDAO.productName")
    @Mapping(target = "productDescription", source = "productDAO.productDescription")
    @Mapping(target = "baseRate", expression = "java(roundOffDouble(productDAO.getBaseRate(), \"Base Rate\"))")
    @Mapping(target = "discountPercentage", expression = "java(roundOffDouble(productDAO.getDiscountPercentage(), \"Discount Percentage\"))")
    @Mapping(target = "actualRate", expression = "java(calculateActualRate(productDAO))")
    @Mapping(target = "imageUrl", source = "productDAO.imageUrl")
    @Mapping(target = "rating", constant = "0.0")
    @Mapping(target = "isInStock", constant = "false")
    @Mapping(target = "isFeatured", constant = "false")
    public abstract Product mapToProduct(ProductDAO productDAO);

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
    public abstract ProductDAO mapToProductDAO(Product product);

    @Mapping(target = "productId", expression = "java((Long)compareAndReturn(savedProduct.getProductId(), productDAO.getProductId()))")
    @Mapping(target = "productName", expression = "java((String)compareAndReturn(savedProduct.getProductName(),productDAO.getProductName()))")
    @Mapping(target = "productDescription", expression = "java((String)compareAndReturn(productDAO.getProductDescription(),productDAO.getProductDescription()))")
    @Mapping(target = "baseRate", expression = "java((Double)compareAndReturn(savedProduct.getBaseRate(),(productDAO.getBaseRate())))")
    @Mapping(target = "discountPercentage", expression = "java((Double)compareAndReturn(savedProduct.getDiscountPercentage(),(productDAO.getDiscountPercentage())))")
    @Mapping(target = "actualRate", expression = "java((Double)calculateActualPatchRate(productDAO, savedProduct))")
    @Mapping(target = "imageUrl", expression = "java((String)compareAndReturn(savedProduct.getImageUrl(), productDAO.getImageUrl()))")
    @Mapping(target = "rating", expression = "java((Double)compareAndReturn(savedProduct.getRating(), productDAO.getRating()))")
    @Mapping(target = "isInStock", expression = "java((Boolean)compareAndReturn(savedProduct.getIsInStock(),productDAO.getIsInStock()))")
    @Mapping(target = "isFeatured", expression = "java((Boolean)compareAndReturn(savedProduct.getIsFeatured(),productDAO.getIsFeatured()))")
    public abstract Product patchDO(ProductDAO productDAO, Product savedProduct);

    Object compareAndReturn(Object oldValue, Object newValue){
        return CommonUtils.compareAndReturn(oldValue, newValue);
    }

    Double roundOffDouble(Double rate, String field) {
        return CommonUtils.roundOffDouble(rate, field);
    }

    Double calculateActualRate(ProductDAO productDAO){
        Double baseRate = roundOffDouble(productDAO.getBaseRate(), "Base Rate");
        Double discountedPrice = productDAO.getDiscountPercentage();

//        if(productsDO.getBaseRate() != null){
//
//        }

        LOGGER.info("Actual Rate Calculation ::: "+( baseRate - (baseRate * (discountedPrice / 100))));
        return ( baseRate - roundOffDouble(baseRate * (discountedPrice / 100), "Actual Rate"));
    }

    Double calculateActualPatchRate(ProductDAO productDAO, Product savedProduct){
        Double baseRate = roundOffDouble((Double)compareAndReturn(savedProduct.getBaseRate(),(productDAO.getBaseRate())), "Base Rate");
        Double discountedPrice = roundOffDouble((Double)compareAndReturn(savedProduct.getBaseRate(), (productDAO.getDiscountPercentage())), "Discount Percentage");

        LOGGER.info("Actual Rate Calculation ::: "+( baseRate - (baseRate * (discountedPrice / 100))));
        return ( baseRate - roundOffDouble(baseRate * (discountedPrice / 100), "Actual Rate"));
    }

}
