package com.ekart.thunderbolt.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDAO {

    private Long productId;
    private String productName;
    private String productDescription;
    private Double baseRate;
    private Double discountPercentage;
    private String imageUrl;
    private Double actualRate;
    private Double rating;
    private Boolean isInStock;
    private Boolean isFeatured;

    public String toString(){
        return " Product Id : " + getProductId() +
                "\n Product Name : " + getProductName() +
                "\n Product Description" + getProductDescription() +
                "\n Base Rate : " + getBaseRate() +
                "\n Discount Percentage : " + getDiscountPercentage() +
                "\n Image URL : " + getImageUrl() +
                "\n Actual Rate : " + getActualRate() +
                "\n Rating : " + getRating() +
                "\n Is In Stock : " + getIsInStock() +
                "\n Is Featured : " + getIsFeatured();
    }


}
