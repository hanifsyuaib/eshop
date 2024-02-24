package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null) {
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }

        if (product.getProductQuantity() < 0) {
            product.setProductQuantity(0);
        }

        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product update(String id, Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(id)) {
                // Update the existing car with the new information
                product.setProductName(updatedProduct.getProductName());

                if (updatedProduct.getProductQuantity() < 0) {
                    product.setProductQuantity(0);
                } else {
                    product.setProductQuantity(updatedProduct.getProductQuantity());
                }
                return product;
            }
        }
        return null; // Handle the case where the car is not found
    }

    public void delete(String id) {
        productData.removeIf(product -> product.getProductId().equals(id));
    }
}
