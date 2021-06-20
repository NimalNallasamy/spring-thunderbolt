package controller;

import com.ekart.thunderbolt.DAO.ProductBrandDAO;
import com.ekart.thunderbolt.repository.ProductBrandRepository;
import com.ekart.thunderbolt.service.ProductBrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductBrandControllerTest {

    @Mock
    ProductBrandService productBrandService;

    @Mock
    ProductBrandRepository productBrandRepository;

    @Mock
    ProductBrandDAO productBrandDAO;

    @Test
    public void testingCreateProduct(){

        ProductBrandDAO createProductBrandDAO = new ProductBrandDAO();
        createProductBrandDAO.setBrandName("Test Brand Name");
        createProductBrandDAO.setBrandDescription("New Brand Description");

        ProductBrandDAO productBrandCreateResponse = new ProductBrandDAO();
        productBrandCreateResponse.setBrandName("Test Brand Name");
        productBrandCreateResponse.setBrandDescription("New Brand Description");

        Mockito.when(productBrandService.saveProductBrand(createProductBrandDAO)).thenReturn(productBrandCreateResponse);
        assertEquals(productBrandCreateResponse, productBrandService.saveProductBrand(createProductBrandDAO));
    }

    @Test(expected = ResponseStatusException.class)
    public void getProductById(){

        ProductBrandDAO getBrandById = new ProductBrandDAO();
        getBrandById.setBrandName("Test Brand Name");
        getBrandById.setBrandDescription("New Brand Description");

        Mockito.when(productBrandService.getProductBrandById(1l)).thenReturn(getBrandById);
        assertEquals(getBrandById, productBrandService.getProductBrandById(1l));

        Mockito.when(productBrandService.getProductBrandById(1l)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        assertEquals(productBrandService.getProductBrandById(1l), new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @Test(expected = ResponseStatusException.class)
    public void getProductByName(){

        ProductBrandDAO getBrandByNameResponse = new ProductBrandDAO();
        getBrandByNameResponse.setBrandName("Test Brand Name");
        getBrandByNameResponse.setBrandDescription("New Brand Description");

//        ProductBrandDAO getBrandByNameResponse = productBrandService.getProductBrandByName("Test Brand Name");
        Mockito.when(productBrandService.getProductBrandByName("Test Brand Name")).thenReturn(getBrandByNameResponse);
        assertEquals(getBrandByNameResponse, productBrandService.getProductBrandByName("Test Brand Name"));

        ResponseStatusException productIdException = new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Mockito.when(productBrandService.getProductBrandByName("%%%")).thenThrow(productIdException);
        assertEquals(productBrandService.getProductBrandByName("%%%"), productIdException);
    }

    @Test(expected = ResponseStatusException.class)
    public void patchProductById(){

        ProductBrandDAO patchBrandRequest = new ProductBrandDAO();
        patchBrandRequest.setBrandName("Test Brand Name");
        patchBrandRequest.setBrandDescription("New Brand Description");

        ProductBrandDAO brandCreateResponse = new ProductBrandDAO();
        brandCreateResponse.setBrandName("Test Brand Name");
        brandCreateResponse.setBrandDescription("New Brand Description");

        Mockito.when(productBrandService.patchProductBrand(0l, patchBrandRequest)).thenReturn(brandCreateResponse);
        assertEquals(brandCreateResponse, productBrandService.patchProductBrand(0l, patchBrandRequest));

        ResponseStatusException productIdException = new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Mockito.when(productBrandService.patchProductBrand(0l, patchBrandRequest)).thenThrow(productIdException);
        assertEquals(productBrandService.patchProductBrand(0l, patchBrandRequest), productIdException);
    }

    @Test(expected = ResponseStatusException.class)
    public void deleteProductById(){

        Mockito.when(productBrandService.deleteProductById(0l)).thenReturn("success");
        assertEquals("success", productBrandService.deleteProductById(0l));

        ResponseStatusException productIdException = new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Mockito.when(productBrandService.getProductBrandById(0l)).thenThrow(productIdException);
        assertEquals(productBrandService.getProductBrandById(0l), productIdException);
    }

}
