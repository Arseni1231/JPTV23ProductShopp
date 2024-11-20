package org.example.services;

import org.example.intefaces.Service;
import org.example.model.Product;
import org.example.model.ProductStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ProductStoreService implements Service<ProductStore> {
    private final ProductStore productStore;

    public ProductStoreService() {
        this.productStore = new ProductStore();
    }

    public void addProduct(String name, String category, double pricePerUnit, int quantity, String unit) {
        Product product = new Product(name, category, pricePerUnit, quantity, unit);
        productStore.addProduct(product);
    }

    public void removeProduct(String name) {
        productStore.removeProduct(name);
    }

    public ArrayList<Product> getAllProducts() {
        return productStore.getAllProducts();
    }

    public ArrayList<Product> getProductsByCategory(String category) {
        return productStore.getProductsByCategory(category);
    }

    public Set<String> getAllCategories() {
        return productStore.getAllCategories();
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean print() {
        return false;
    }

    @Override
    public List<ProductStore> list() {
        return Collections.emptyList();
    }
}
