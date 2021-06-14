package com.ekart.thunderbolt.controller;

import com.ekart.thunderbolt.DAO.ProductDAO;
import com.ekart.thunderbolt.repository.ProductRepository;
import com.ekart.thunderbolt.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    ProductService productService;

    @Mock
    ProductRepository productRepository;

//    @InjectMocks
    @Mock
    ProductDAO productDAO;

    @Test
    public void testingCreateProduct(){
        ProductDAO createProductDAO = new ProductDAO();

        createProductDAO.setProductName("New Test Create Product");
        createProductDAO.setProductDescription("New Test Description");
        createProductDAO.setBaseRate(188.2155);
        createProductDAO.setDiscountPercentage(23.1239);
        createProductDAO.setImageUrl("/images/testProduct-1.png");

        ProductDAO productCreateResponse = new ProductDAO();
        productCreateResponse.setProductId(6l);
        productCreateResponse.setProductName("New Test Create Product");
        productCreateResponse.setBaseRate(188.2155);
        productCreateResponse.setDiscountPercentage(23.1239);
        productCreateResponse.setImageUrl("/images/testProduct-1.png");
        productCreateResponse.setRating(0.0);
        productCreateResponse.setActualRate(144.7);
        productCreateResponse.setIsInStock(false);
        productCreateResponse.setIsFeatured(false);

        ProductDAO createdResponse = productService.createProduct(createProductDAO);
        Mockito.when(productService.createProduct(createProductDAO)).thenReturn(productCreateResponse);
        assertEquals(productCreateResponse, productService.createProduct(createProductDAO));
    }

    @Test
    public void getProductById(){

        ProductDAO productCreateResponse = new ProductDAO();
        productCreateResponse.setProductId(6l);
        productCreateResponse.setProductName("New Test Create Product");
        productCreateResponse.setBaseRate(188.2155);
        productCreateResponse.setDiscountPercentage(23.1239);
        productCreateResponse.setImageUrl("/images/testProduct-1.png");
        productCreateResponse.setRating(0.0);
        productCreateResponse.setActualRate(144.7);
        productCreateResponse.setIsInStock(false);
        productCreateResponse.setIsFeatured(false);

        ProductDAO createdResponse = productService.getProductById(6l);
        Mockito.when(productService.getProductById(6l)).thenReturn(productCreateResponse);
        assertEquals(productCreateResponse, productService.getProductById(6l));
    }

    @Test
    public void getProductByName(){

        ProductDAO productCreateResponse = new ProductDAO();
        productCreateResponse.setProductId(6l);
        productCreateResponse.setProductName("New Test Create Product");
        productCreateResponse.setBaseRate(188.2155);
        productCreateResponse.setDiscountPercentage(23.1239);
        productCreateResponse.setImageUrl("/images/testProduct-1.png");
        productCreateResponse.setRating(0.0);
        productCreateResponse.setActualRate(144.7);
        productCreateResponse.setIsInStock(false);
        productCreateResponse.setIsFeatured(false);

        ProductDAO createdResponse = productService.getProductByName("New Test Create Product");
        Mockito.when(productService.getProductByName("New Test Create Product")).thenReturn(productCreateResponse);
        assertEquals(productCreateResponse, productService.getProductByName("New Test Create Product"));
    }

    @Test
    public void patchProductById(){
        ProductDAO createProductDAO = new ProductDAO();
        createProductDAO.setProductId(6l);
        createProductDAO.setProductName("New Test Create Product");
        createProductDAO.setProductDescription("New Test Description");
        createProductDAO.setBaseRate(188.2155);
        createProductDAO.setDiscountPercentage(23.1239);
        createProductDAO.setImageUrl("/images/testProduct-1.png");

        ProductDAO productCreateResponse = new ProductDAO();
        productCreateResponse.setProductId(6l);
        productCreateResponse.setProductName("New Test Create Product");
        productCreateResponse.setBaseRate(188.2155);
        productCreateResponse.setDiscountPercentage(23.1239);
        productCreateResponse.setImageUrl("/images/testProduct-1.png");
        productCreateResponse.setRating(0.0);
        productCreateResponse.setActualRate(144.7);
        productCreateResponse.setIsInStock(false);
        productCreateResponse.setIsFeatured(false);

        ProductDAO createdResponse = productService.patchProduct(6l, createProductDAO);
        Mockito.when(productService.patchProduct(6l, createProductDAO)).thenReturn(productCreateResponse);
        assertEquals(productCreateResponse, productService.patchProduct(6l, createProductDAO));
    }

    @Test
    public void deleteProductById(){

        ProductDAO productCreateResponse = new ProductDAO();
        productCreateResponse.setProductId(6l);
        productCreateResponse.setProductName("New Test Create Product");
        productCreateResponse.setBaseRate(188.2155);
        productCreateResponse.setDiscountPercentage(23.1239);
        productCreateResponse.setImageUrl("/images/testProduct-1.png");
        productCreateResponse.setRating(0.0);
        productCreateResponse.setActualRate(144.7);
        productCreateResponse.setIsInStock(false);
        productCreateResponse.setIsFeatured(false);

        productService.deleteProductById(6l);
        Mockito.when(productService.deleteProductById(6l)).thenReturn("success");
        assertEquals("success", productService.deleteProductById(6l));
    }

}
