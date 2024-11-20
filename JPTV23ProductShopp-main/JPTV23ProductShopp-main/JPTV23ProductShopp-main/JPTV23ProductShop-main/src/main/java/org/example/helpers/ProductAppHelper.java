package org.example.helpers;

import org.example.intefaces.AppHelper;
import org.example.intefaces.Input;
import org.example.model.Product;
import org.example.intefaces.Service;

import java.util.List;

public class ProductAppHelper implements AppHelper<Product> {

    private final Input input;
    private final Service<Product> productService;

    public ProductAppHelper(Input input, Service<Product> productService) {
        this.input = input;
        this.productService = productService;
    }

    @Override
    public Product create() {

        try {
            System.out.print("Название продукта: ");
            String name = input.nextLine();
            System.out.print("Категория: ");
            String category = input.nextLine();
            System.out.print("Цена за единицу: ");
            double pricePerUnit = Double.parseDouble(input.nextLine());
            System.out.print("Количество: ");
            int quantity = Integer.parseInt(input.nextLine());
            System.out.print("Единица измерения: ");
            String unit = input.nextLine();

            Product product = new Product(name, category, pricePerUnit, quantity, unit);
            productService.add(); // добавление продукта через сервис
            System.out.println("Продукт успешно создан: " + product);
            return product;

        } catch (Exception e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean printList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Список продуктов пуст.");
            return false;
        }
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%d. %s, Категория: %s, Цена: %.2f, Количество: %d %s%n",
                    i + 1, products.get(i).getName(), products.get(i).getCategory(),
                    products.get(i).getPricePerUnit(), products.get(i).getQuantity(),
                    products.get(i).getUnit());
        }
        return true;
    }

    @Override
    public List<Product> edit(List<Product> listClazz) {
        return List.of();
    }

    // Метод редактирования, если он нужен, можно добавить сюда

}





