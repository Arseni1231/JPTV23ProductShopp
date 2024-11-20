package org.example.helpers;

import org.example.intefaces.AppHelper;
import org.example.intefaces.Input;
import org.example.model.Product;
import org.example.model.ProductStore;

import java.util.List;

public class ProductStoreAppHelper implements AppHelper<Product> {
    private final Input input;
    private final ProductStore productStore;

    public ProductStoreAppHelper(Input input, ProductStore productStore) {
        this.input = input;
        this.productStore = productStore;
    }

    @Override
    public Product create() {
        System.out.print("Введите имя продукта: ");
        String name = input.nextLine();
        System.out.print("Введите категорию продукта: ");
        String category = input.nextLine();

        Product product = new Product(name, category);
        productStore.addProduct(product);
        return product;
    }

    @Override
    public boolean printList(List<Product> productList) {
        if (productList.isEmpty()) {
            System.out.println("Нет доступных продуктов.");
            return false;
        }
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            System.out.printf("%d. %s - %s%n", i + 1, product.getName(), product.getCategory());
        }
        return true;
    }

    @Override
    public List<Product> edit(List<Product> productList) {
        System.out.print("Введите номер продукта для редактирования: ");
        int index = Integer.parseInt(input.nextLine()) - 1;
        if (index < 0 || index >= productList.size()) {
            System.out.println("Неверный номер продукта.");
            return productList;
        }

        Product product = productList.get(index);
        System.out.print("Введите новое имя продукта (оставьте пустым для пропуска): ");
        String newName = input.nextLine();
        System.out.print("Введите новую категорию продукта (оставьте пустым для пропуска): ");
        String newCategory = input.nextLine();

        if (!newName.isEmpty()) {
            product.setName(newName);
        }
        if (!newCategory.isEmpty()) {
            product.setCategory(newCategory);
        }

        System.out.println("Продукт обновлен: " + product);
        return productList;
    }

    public void removeProduct() {
        System.out.print("Введите имя продукта для удаления: ");
        String name = input.nextLine();
        productStore.removeProduct(name);
    }
}
