package serviceTest;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sukhjit.assignment.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.sukhjit.assignment.controller.ProductController;
import com.sukhjit.assignment.model.Product;
import com.sukhjit.assignment.service.ProductService;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest
{
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Before
    public void setUp()
    {

    }

    @Test
    public void searchProductByCategoryTest()
    {
        List<String> tag = new ArrayList<String>();
        tag.add("Red");
        tag.add("Silk");
        tag.add("sleeves");

        Product p = new Product("123", "red", "This is red", "lole", tag,"Men", new Date(2010, 1, 3));
        List<Product> productList = new ArrayList<>();
        productList.add(p);
        Pageable pageable = PageRequest.of(0, 5);
        Mockito.when(productRepository.findByCategoryOrderByCreatedAtDesc(pageable, "Men")).thenReturn(productList);
		assertEquals(
                productList,
                productService.searchProductByCategory("Men",0,5));

    }

    @After
    public void destroy()
    {

    }
}
