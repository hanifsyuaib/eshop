package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;
    Product product;
    Product product2;
    Product product3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);

        this.product2 = new Product();
        this.product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        this.product2.setProductName("Sampo Cap Usep");
        this.product2.setProductQuantity(-99);

        this.product3 = new Product();
        this.product3.setProductId("cc888e9f-4c41-460e-8860-99af6af57ss5");
        this.product3.setProductName("Sampo Cap Kodok");
        this.product3.setProductQuantity(50);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();

        productList.add(product);
        productList.add(product3);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> foundProducts = productService.findAll();

        assertEquals(productList.size(), foundProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product3);

        when(productRepository.findById(product.getProductId())).thenReturn(product);
        Product foundProducts = productService.findById(product.getProductId());

        assertEquals(product, foundProducts);
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testEditProduct() {
        productService.update(product.getProductId(), product);

        verify(productRepository, times(1)).update(product.getProductId(), product);
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProductById(product.getProductId());

        verify(productRepository, times(1)).delete(product.getProductId());
    }


}
