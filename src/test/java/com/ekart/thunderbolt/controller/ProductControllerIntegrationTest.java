package com.ekart.thunderbolt.controller;

import com.ekart.thunderbolt.DAO.ProductDAO;
import com.ekart.thunderbolt.service.ProductService;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerIntegrationTest {

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getProductDetails() throws Exception{

        ProductDAO getProductDetailsResponse = new ProductDAO();
        getProductDetailsResponse.setProductId((long) 2);
        getProductDetailsResponse.setProductName("Test Product Name");
        getProductDetailsResponse.setProductDescription("Test Product Description");
        getProductDetailsResponse.setDiscountPercentage(23.1239);
        getProductDetailsResponse.setBaseRate(185.6554);
        getProductDetailsResponse.setRating(0.0);
        getProductDetailsResponse.setActualRate(142.73);
        getProductDetailsResponse.setIsFeatured(false);
        getProductDetailsResponse.setIsInStock(false);
        getProductDetailsResponse.setImageUrl("/images/testProduct-1.png");

        List<ProductDAO> listOfAllProducts = Arrays.asList(getProductDetailsResponse);

        Mockito.when(productService.getAllProducts()).thenReturn(listOfAllProducts);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/product")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mockResponse = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse apiResponse = mockResponse.getResponse();
        assertEquals(HttpStatus.OK.value(), apiResponse.getStatus());
    }

    @Test
    public void getProductById() throws Exception{

        ProductDAO getProductDetailsResponse = new ProductDAO();
        getProductDetailsResponse.setProductId((long) 2);
        getProductDetailsResponse.setProductName("Test Product Name");
        getProductDetailsResponse.setProductDescription("Test Product Description");
        getProductDetailsResponse.setDiscountPercentage(23.1239);
        getProductDetailsResponse.setBaseRate(185.6554);
        getProductDetailsResponse.setRating(0.0);
        getProductDetailsResponse.setActualRate(142.73);
        getProductDetailsResponse.setIsFeatured(false);
        getProductDetailsResponse.setIsInStock(false);
        getProductDetailsResponse.setImageUrl("/images/testProduct-1.png");

        Mockito.when(productService.getProductById(2l)).thenReturn(getProductDetailsResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/product/id/2")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mockResponse = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse apiResponse = mockResponse.getResponse();
        assertEquals(HttpStatus.OK.value(), apiResponse.getStatus());

    }

    @Test
    public void getProductByName() throws Exception{

        ProductDAO getProductDetailsResponse = new ProductDAO();
        getProductDetailsResponse.setProductId((long) 2);
        getProductDetailsResponse.setProductName("Test Product Name");
        getProductDetailsResponse.setProductDescription("Test Product Description");
        getProductDetailsResponse.setDiscountPercentage(23.1239);
        getProductDetailsResponse.setBaseRate(185.6554);
        getProductDetailsResponse.setRating(0.0);
        getProductDetailsResponse.setActualRate(142.73);
        getProductDetailsResponse.setIsFeatured(false);
        getProductDetailsResponse.setIsInStock(false);
        getProductDetailsResponse.setImageUrl("/images/testProduct-1.png");

        Mockito.when(productService.getProductById(2l)).thenReturn(getProductDetailsResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/product/name/Test%20Product%20Name")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mockResponse = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse apiResponse = mockResponse.getResponse();
        assertEquals(HttpStatus.OK.value(), apiResponse.getStatus());

    }

    @Test
    public void postProductDetails() throws Exception{

        ProductDAO createProductDAO = new ProductDAO();
        createProductDAO.setProductName("Test Product Name");
        createProductDAO.setProductDescription("Test Product Description");
        createProductDAO.setDiscountPercentage(23.1239);
        createProductDAO.setBaseRate(185.6554);
        createProductDAO.setImageUrl("/images/testProduct-1.png");

        ProductDAO createProductResponse = new ProductDAO();
        createProductResponse.setProductId((long) 2);
        createProductResponse.setProductName("Test Product Name");
        createProductResponse.setProductDescription("Test Product Description");
        createProductResponse.setDiscountPercentage(23.1239);
        createProductResponse.setBaseRate(185.6554);
        createProductResponse.setRating(0.0);
        createProductResponse.setActualRate(142.73);
        createProductResponse.setIsFeatured(false);
        createProductResponse.setIsInStock(false);
        createProductResponse.setImageUrl("/images/testProduct-1.png");

        Mockito.when(productService.createProduct(createProductDAO)).thenReturn(createProductResponse);

        RequestBuilder httpRequestBuilder = MockMvcRequestBuilders.post("/api/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .content(createProductDAO.asJSONString());

        MvcResult mockResponse = mockMvc.perform(httpRequestBuilder).andReturn();
        MockHttpServletResponse apiResponse = mockResponse.getResponse();
        assertEquals(HttpStatus.CREATED.value(), apiResponse.getStatus());
    }

    @Test
    public void patchProductDetails() throws Exception{

        ProductDAO updateProductDAO = new ProductDAO();
        updateProductDAO.setRating(2.0);

        ProductDAO updateProductResponse = new ProductDAO();
        updateProductResponse.setProductId((long) 2);
        updateProductResponse.setProductName("Test Product Name");
        updateProductResponse.setProductDescription("Test Product Description");
        updateProductResponse.setDiscountPercentage(23.1239);
        updateProductResponse.setBaseRate(185.6554);
        updateProductResponse.setRating(2.0);
        updateProductResponse.setActualRate(142.73);
        updateProductResponse.setIsFeatured(false);
        updateProductResponse.setIsInStock(false);
        updateProductResponse.setImageUrl("/images/testProduct-1.png");

        Mockito.when(productService.createProduct(updateProductDAO)).thenReturn(updateProductResponse);

        RequestBuilder httpRequestBuilder = MockMvcRequestBuilders.post("/api/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .content(updateProductDAO.asJSONString());

        MvcResult mockResponse = mockMvc.perform(httpRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        MockHttpServletResponse apiResponse = mockResponse.getResponse();
        assertEquals(HttpStatus.CREATED.value(), apiResponse.getStatus());
    }

    @Test
    public void deleteProductById() throws Exception{

        JSONObject deleteResponse = new JSONObject();
        deleteResponse.put("message", "Product deleted successfully");

        RequestBuilder httpRequestBuilder = MockMvcRequestBuilders.delete("/api/product/2")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mockResponse = mockMvc.perform(httpRequestBuilder).andReturn();

        MockHttpServletResponse apiResponse = mockResponse.getResponse();
        assertEquals(HttpStatus.OK.value(), apiResponse.getStatus());

    }


}
