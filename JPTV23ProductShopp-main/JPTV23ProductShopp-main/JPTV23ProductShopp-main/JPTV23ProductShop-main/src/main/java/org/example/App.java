package org.example;
import org.example.model.Product;
import org.example.model.ProductStore;
import org.example.services.ProductService;

import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
public class App {
    private static final ProductStore productStore = new ProductStore();
    private static final ProductService productService = new ProductService(productStore);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        addTestProduct();
        run();
    }

    private static void addTestProduct() {

    }

    public static void run() {


        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить продукт");
            System.out.println("2 - Просмотреть все продукты");
            System.out.println("3 - Удалить продукт");
            System.out.println("4 - Просмотреть продукты по категории");
            System.out.println("5 - Выйти");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:

                    addProduct();
                    break;
                case 2:
                    System.out.println("Все продукты в магазине:");
                    ArrayList<Product> allProducts = productStore.getAllProducts();
                    if (allProducts.isEmpty()) {
                        System.out.println("Нет доступных продуктов.");
                    } else {
                        int productIndex = 1;
                        for (Product product : allProducts) {
                            System.out.println(productIndex++ + " - " + product);
                        }
                    }
                    break;


                case 3:

                    removeProduct();
                    break;

                case 4:

                    viewProductsByCategory();
                    break;

                case 5:

                    System.out.println("Спасибо за использование магазина продуктов!");
                    break;

                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine();
        System.out.print("Введите категорию продукта: ");
        String category = scanner.nextLine();
        System.out.print("Введите цену продукта за единицу: ");
        double pricePerUnit = Double.parseDouble(scanner.nextLine());
        System.out.print("Введите количество продукта: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите единицу измерения (например, кг, л, шт): ");
        String unit = scanner.nextLine();

        Product product = new Product(name, category, pricePerUnit, quantity, unit);
        productService.addProduct(product);
    }


    private static void removeProduct() {
        System.out.print("Введите название продукта для удаления: ");
        String deleteName = scanner.nextLine();
        productStore.removeProduct(deleteName);
    }

    private static void viewProductsByCategory() {
        // Получаем все категории и выводим их пользователю
        Set<String> categories = productStore.getAllCategories();
        System.out.println("Выберите категорию:");
        int categoryIndex = 1;
        for (String cat : categories) {
            System.out.println(categoryIndex++ + " - " + cat);
        }

        // Пользователь выбирает категорию
        int selectedCategoryIndex = Integer.parseInt(scanner.nextLine()) - 1;
        String selectedCategory = (String) categories.toArray()[selectedCategoryIndex];

        // Получаем продукты в выбранной категории
        ArrayList<Product> filteredProducts = productStore.getProductsByCategory(selectedCategory);
        System.out.println("Продукты в категории '" + selectedCategory + "':");
        if (filteredProducts.isEmpty()) {
            System.out.println("Нет доступных продуктов в этой категории.");
        } else {
            int productIndex = 1;
            for (Product product : filteredProducts) {
                System.out.println(productIndex++ + " - " + product);
            }

            // Пользователь выбирает продукт
            System.out.print("Введите номер продукта, который хотите выбрать: ");
            int productChoice = Integer.parseInt(scanner.nextLine()) - 1;

            // Проверяем, существует ли выбранный продукт
            if (productChoice >= 0 && productChoice < filteredProducts.size()) {
                Product chosenProduct = filteredProducts.get(productChoice);

                // Запрашиваем количество для покупки
                System.out.print("Введите количество для покупки (доступно: " + chosenProduct.getQuantity() + " " + chosenProduct.getUnit() + "): ");
                int purchaseQuantity = Integer.parseInt(scanner.nextLine());

                // Проверяем, достаточно ли на складе
                if (purchaseQuantity <= chosenProduct.getQuantity()) {
                    chosenProduct.setQuantity(chosenProduct.getQuantity() - purchaseQuantity);
                    double totalCost = chosenProduct.getPricePerUnit() * purchaseQuantity; // расчет общей стоимости
                    System.out.println("Вы успешно купили " + purchaseQuantity + " " + chosenProduct.getName() + "(ов) на сумму " + totalCost + " евро.");
                } else {
                    System.out.println("Недостаточно товара на складе.");
                }
            } else {
                System.out.println("Неверный выбор продукта.");
            }
        }
    }
}
