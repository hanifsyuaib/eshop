package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ProductTest {
    Product product;
    Product product2;
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);

        this.product2 = new Product("Sampo Cap Usep",50);
        this.product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
    }
    @Test
    void testGetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    @Test
    void testGetProduct2Id() {
        assertEquals("a0f9de46-90b1-437d-a0bf-d0821dde9096", this.product2.getProductId());
    }

    @Test
    void testGetProduct2Name() {
        assertEquals("Sampo Cap Usep", this.product2.getProductName());
    }

    @Test
    void testGetProduct2Quantity() {
        assertEquals(50, this.product2.getProductQuantity());
    }
}
