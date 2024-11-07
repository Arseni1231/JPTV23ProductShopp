package org.example.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProductStore {
    private final ArrayList<Product> products;

    public ProductStore() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        for(Product p : products) {
            if(p.getName().equals(product.getName()) && p.getCategory().equals(product.getCategory())) {
                System.out.println("Продукт с таким именем и категорией уже существует");
                return;
            }
        }
        products.add(product);
        System.out.println("Продукт добавлен: " + product);
    }

    public void removeProduct(String name) {
        products.removeIf(product -> product.getName().equals(name));
        System.out.println("Продукт удален: " + name);
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }
    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
    public Set<String> getAllCategories() {
        Set<String> categories = new HashSet<>();
        for (Product product : products) {
            categories.add(product.getCategory());
        }
        return categories;
    }
}
