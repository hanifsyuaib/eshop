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
    Product product;
    Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);

        this.product2 = new Product();
        this.product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        this.product2.setProductName("Sampo Cap Usep");
        this.product2.setProductQuantity(99);
    }

    @Test
    void testProductListPageViewNameAndModel() {
        List<Product> products = new ArrayList<>();

        products.add(product);
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
        String viewName = productController.createProductPost(product, mock(Model.class));

        assertEquals("redirect:list", viewName);
    }

    @Test
    void testEditProductPageViewName() {
        Model model = mock(Model.class);
        String viewName = productController.editProductPage(product.getProductId(), model);

        assertEquals("EditProduct", viewName);
    }

    @Test
    void testEditProductPostRedirectsToListPage() {
        String viewName = productController.editProductPost(product, mock(Model.class));

        assertEquals("redirect:list", viewName);
    }

    @Test
    void testDeleteProductRedirectsToListPage() {
        String viewName = productController.deleteProduct(product.getProductId());

        assertEquals("redirect:list", viewName);
    }

}
