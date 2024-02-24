package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    Product product;
    Product product2;
    Product product3;
    Product product4;
    @BeforeEach
    void setUp() {
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

        this.product4 = new Product();
        this.product4.setProductId(null);
        this.product4.setProductName("Sampo Cap Budi");
        this.product4.setProductQuantity(50);
    }
    @Test
    void testCreateAndFind() {
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testCreateIfMinusQuantityAndFind() {
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertEquals(product2.getProductName(), savedProduct.getProductName());
        assertEquals(0, savedProduct.getProductQuantity());
    }
    @Test
    void testCreateIfNoProductIdAndFind() {
        productRepository.create(product4);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertNotEquals(null, savedProduct.getProductId());
        assertEquals(product4.getProductName(), savedProduct.getProductName());
        assertEquals(product4.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        productRepository.create(product);
        productRepository.create(product3);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product3.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindByIdIfEmpty() {
        Product productEmpty = productRepository.findById(product3.getProductId());
        assertEquals(null,productEmpty);
    }

    @Test
    void testFindById() {
        productRepository.create(product);
        Product savedProduct = productRepository.findById(product.getProductId());
        assertEquals(product, savedProduct);
    }

    @Test
    void testEditAndFind() {
        productRepository.create(product);

        product.setProductQuantity(99);
        productRepository.update(product.getProductId(), product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testEditIfMinusQuantityAndFind() {
        productRepository.create(product);

        product.setProductQuantity(-99);
        productRepository.update(product.getProductId(), product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(0, savedProduct.getProductQuantity());
    }
    @Test
    void testEditNotSavedProductAndFind() {
        productRepository.create(product);

        product3.setProductQuantity(30);
        productRepository.update(product3.getProductId(), product3);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteAndFindIfEmpty() {
        productRepository.create(product);

        productRepository.delete(product.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testDeleteAndFindIfMoreThanOneProduct() {
        productRepository.create(product);
        productRepository.create(product2);

        productRepository.delete(product.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertNotEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteEmptyProduct() {
        productRepository.create(product);

        productRepository.delete(product.getProductId());
        productRepository.delete(product.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
}
