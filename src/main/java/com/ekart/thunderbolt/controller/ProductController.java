package com.ekart.thunderbolt.controller;


import com.ekart.thunderbolt.DAO.ProductDAO;
import com.ekart.thunderbolt.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@Tag(name="Product" , description = "Product APIs")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productsService;

    /**
     * This API is used to create a new product in Thunderbolt.
     * */
    @PostMapping
    @Operation(
            summary = "Create a new Product",
            responses = {
                    @ApiResponse( description = "Create product", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDAO.class))),
                    @ApiResponse( description = "Unable to create product", responseCode = "400", content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<ProductDAO> createProducts(@Parameter(description = "Products Data to create a new product", required = true, content = @Content(schema = @Schema(implementation = ProductDAO.class))) @RequestBody ProductDAO productDAO){
        LOGGER.info("Inside Create Product Details ::: "+productDAO.toString());
        ProductDAO savedProductDAO = productsService.createProduct(productDAO);
        return status(HttpStatus.CREATED).body(savedProductDAO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDAO> updateProducts(@PathVariable Long id, @RequestBody ProductDAO productDAO){
        LOGGER.info("Inside Update Products Details ::: "+productDAO.toString());
        ProductDAO savedProductsDo = productsService.patchProductData(id, productDAO);
        return status(HttpStatus.OK).body(savedProductsDo);
    }

    /**
     * This API is used to fetch all the Products created.
     * */
    @GetMapping
    @Operation(
            summary = "To fetch all the Product Details",
            responses = {
                    @ApiResponse( description = "To fetch the details of all the products available in the Database", responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse( description = "Error while fetching the response", responseCode = "500", content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<List<ProductDAO>> getAllProductDetails(){

        LOGGER.info("Inside Get All Product Details");
        List<ProductDAO> productsDO = productsService.getAllProducts();
        LOGGER.info("Logger of Products Fetching123 :::: " + productsDO.toString());

        return status(HttpStatus.OK).body(productsDO);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductDAO> getProductById(@PathVariable Long id){
        LOGGER.info("Inside Products Controller Fetching the details by product ID ::: "+id);
        return status(HttpStatus.OK).body(productsService.getProductById(id));
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<ProductDAO> getProductById(@PathVariable String productName){
        LOGGER.info("Inside Products Controller Fetching the details by product ID ::: "+productName);
        return status(HttpStatus.OK).body(productsService.getProductByName(productName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){

        LOGGER.info("Inside Products Controller, Deleting the products by Product ID : "+id);
        productsService.deleteProductById(id);
        JSONObject responseJson = new JSONObject().put("message" , "Product Deleted Successfully. ");
        LOGGER.info("Inside Products Controller, responseJson : "+responseJson);
        return status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseJson.toString());
    }

}