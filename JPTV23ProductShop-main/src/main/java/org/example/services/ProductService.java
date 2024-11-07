package org.example.services;
import org.example.model.ProductStore;
import org.example.model.Product;

import java.util.List;
public class ProductService {
    private ProductStore productStore;

    public ProductService(ProductStore productStore) {
        this.productStore = productStore;
    }

    public void addProduct(Product product) {
        productStore.addProduct(product);
    }
    public List<Product> getAllProducts() {
        return productStore.getAllProducts();
    }

    public void removeProduct(String name) {
        productStore.removeProduct(name);
    }
    public List<Product> getProductsByCategory(String category) {
        return productStore.getProductsByCategory(category);
    }

    public void displayAllProducts() {
        List<Product> products = getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Нету доступных продуктов");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}
