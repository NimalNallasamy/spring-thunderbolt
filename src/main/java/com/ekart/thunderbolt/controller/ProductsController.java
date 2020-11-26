package com.ekart.thunderbolt.controller;

import com.ekart.thunderbolt.DO.ProductsDO;
import com.ekart.thunderbolt.entity.Products;
import com.ekart.thunderbolt.service.ProductsService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/products")
@AllArgsConstructor
@Tag(name="Product" , description = "Product APIs")
public class ProductsController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);
    private final ProductsService productsService;

    /**
     * This API is used to create a new product in Thunderbolt.
     * */
    @PostMapping("/product")
    @Operation(
            summary = "Create a new Product",
            responses = {
                    @ApiResponse( description = "Create product", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductsDO.class)) ),
                    @ApiResponse( description = "Unable to create product", responseCode = "400", content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<ProductsDO> createProducts(@Parameter(description = "Products Data to create a new product", required = true, content = @Content(schema = @Schema(implementation = ProductsDO.class))) @RequestBody ProductsDO productsDO){
        LOGGER.info("Inside Create Product Details ::: "+productsDO.toString());
        ProductsDO savedProductsDo = productsService.saveProduct(productsDO);
        return status(HttpStatus.CREATED).body(savedProductsDo);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<ProductsDO> updateProducts(@PathVariable Long id, @RequestBody ProductsDO productsDO){
        LOGGER.info("Inside Update Products Details ::: "+productsDO.toString());
        ProductsDO savedProductsDo = productsService.patchProducts(id, productsDO);
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
    public ResponseEntity<List<ProductsDO>> getAllProductDetails(){

        LOGGER.info("Inside Get All Product Details");
        List<ProductsDO> productsDO = productsService.getProducts();
        LOGGER.info("Logger of Products Fetching123 :::: " + productsDO.toString());

        return status(HttpStatus.OK).body(productsDO);
    }

    @GetMapping("/product/id/{id}")
    public ResponseEntity<ProductsDO> getProductById(@PathVariable Long id){
        LOGGER.info("Inside Products Controller Fetching the details by product ID ::: "+id);
        return status(HttpStatus.OK).body(productsService.getProductById(id));
    }

    @GetMapping("/product/name/{productName}")
    public ResponseEntity<ProductsDO> getProductById(@PathVariable String productName){
        LOGGER.info("Inside Products Controller Fetching the details by product ID ::: "+productName);
        return status(HttpStatus.OK).body(productsService.getProductByName(productName));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){

        LOGGER.info("Inside Products Controller, Deleting the products by Product ID : "+id);
        productsService.deleteProductsById(id);
        JSONObject responseJson = new JSONObject().put("message" , "Product Deleted Successfully. ");
        LOGGER.info("Inside Products Controller, responseJson : "+responseJson);
        return status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseJson.toString());
    }

}
