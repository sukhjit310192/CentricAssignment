package controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sukhjit.assignment.controller.ProductController;
import com.sukhjit.assignment.model.Product;
import com.sukhjit.assignment.service.ProductService;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest
{

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController controller;

    @Before
    public void setUp()
    {
    }

    @Test
        public void addProductTest()
        {
        List<String> tag = new ArrayList<>();
        tag.add("Red");
        tag.add("Silk");
        tag.add("sleeves");

        Product p = new Product("123", "red", "This is red", "lole", tag,"Men", new Date(2010, 1, 3));

        doNothing().when(productService).addProduct(p);

            assertEquals(
                    new ResponseEntity<>(p, HttpStatus.CREATED),
                    controller.addProduct(p));
    }

    @After
    public void destroy()
    {

    }

    @Test
    public void getAllProductsTest()
    {
        List<String> tag = new ArrayList<String>();
        tag.add("Red");
        tag.add("Silk");
        tag.add("sleeves");

        Product p = new Product("123", "red", "This is red", "lole", tag,"Men", new Date(2010, 1, 3));
        List<Product> productlist = new ArrayList<Product>();
        productlist.add(p);
        Mockito.when(productService.getAllProducts()).thenReturn(productlist);
        assertEquals(
                new ResponseEntity<List<Product>>(productlist, HttpStatus.OK),
                controller.getAllProducts());
    }

    @Test
    public void searchProductByCategoryTest()
    {
        List<String> tag = new ArrayList<String>();
        tag.add("Red");
        tag.add("Silk");
        tag.add("sleeves");

        Product p = new Product("123", "red", "This is red", "lole", tag,"Men", new Date(2010, 1, 3));

        List<Product> list = new ArrayList<>();
        list.add(p);
        Mockito.when(productService.searchProductByCategory("Men", 0 , 5)).thenReturn(list);
        assertEquals(
                new ResponseEntity<List<Product>>(list, HttpStatus.FOUND),
                controller.searchProductByCategory("Men", 0 , 5));

    }

@Test
    public void searchProductByCategoryEmptyListTest()
    {

        List<Product> list = new ArrayList<>();
        Mockito.when(productService.searchProductByCategory("Men", 0 , 5)).thenReturn(list);
        assertEquals(
                new ResponseEntity<List<Product>>(list, HttpStatus.NO_CONTENT),
                controller.searchProductByCategory("Men", 0 , 5));

    }


}
