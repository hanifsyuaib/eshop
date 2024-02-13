package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProductListPageViewNameAndModel() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);

        products.add(product1);
        products.add(product2);

        when(productService.findAll()).thenReturn(products);
        Model model = mock(Model.class);
        String viewName = productController.productListPage(model);

        assertEquals("ListProduct", viewName);
        verify(model, times(1)).addAttribute("products", products);
    }

    @Test
    void testCreateProductPageViewName() {
        String viewName = productController.createProductPage(mock(Model.class));

        assertEquals("CreateProduct", viewName);
    }

    @Test
    void testCreateProductPostRedirectsToListPage() {
        String viewName = productController.createProductPost(new Product(), mock(Model.class));

        assertEquals("redirect:list", viewName);
    }

    @Test
    void testEditProductPageViewName() {
        String productName = "Sampo Cap Bambang";
        int productQuantity = 100;
        Model model = mock(Model.class);
        String viewName = productController.editProductPage(productName, productQuantity, model);

        assertEquals("EditProduct", viewName);
    }

    @Test
    void testEditProductPostRedirectsToListPage() {
        String viewName = productController.editProductPost(new Product(), mock(Model.class));

        assertEquals("redirect:list", viewName);
    }

    @Test
    void testDeleteProductRedirectsToListPage() {
        String viewName = productController.deleteProduct("Sampo Cap Bambang", mock(Model.class));

        assertEquals("redirect:list", viewName);
    }

}
