package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product edit(Product product) {
        String newProductName = product.getProductName();
        int newProductQuantity = product.getProductQuantity();

        for (Product savedProduct : productData) {
            String savedProductName = savedProduct.getProductName();

            if (savedProductName.equals(newProductName)) {
                savedProduct.setProductQuantity(newProductQuantity);
                break;
            }
        }
        return product;
    }

    public String delete(String productName) {
        for (Product savedProduct : productData) {
            String savedProductName = savedProduct.getProductName();

            if (savedProductName.equals(productName)) {
                productData.remove(savedProduct);
                break;
            }
        }
        return productName;
    }
}
