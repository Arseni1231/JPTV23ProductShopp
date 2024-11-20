package org.example.helpers;

import org.example.model.Product;
import org.example.model.ProductStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProductStoreAppHelperTest {

    private ProductStore productStore;

    @BeforeEach
    void setUp() {
        // Инициализация ProductStore перед каждым тестом
        productStore = new ProductStore();
    }

    @Test
    void testAddProductsInCategories() {
        // Примеры продуктов в категориях: фрукты, овощи, ягоды, молочные продукты
        List<Product> products = List.of(
                new Product("Яблоки", "Фрукты", 1.20, 20, "кг"),
                new Product("Бананы", "Фрукты", 1.00, 30, "кг"),
                new Product("Апельсины", "Фрукты", 1.50, 50, "кг"),
                new Product("Виноград", "Фрукты", 2.50, 15, "кг"),
                new Product("Ананасы", "Фрукты", 3.00, 15, "кг"),
                new Product("Манго", "Фрукты", 2.20, 40, "кг"),
                new Product("Персики", "Фрукты", 1.80, 45, "кг"),
                new Product("Лимоны", "Фрукты", 0.90, 40, "кг"),
                new Product("Вишня", "Фрукты", 2.30, 30, "кг"),
                new Product("Киви", "Фрукты", 1.70, 35, "кг"),

                new Product("Морковки", "Овощи", 0.80, 18, "кг"),
                new Product("Картошка", "Овощи", 0.60, 50, "кг"),
                new Product("Помидоры", "Овощи", 1.50, 60, "кг"),
                new Product("Огурцы", "Овощи", 1.00, 50, "кг"),
                new Product("Перцы", "Овощи", 1.80, 15, "кг"),
                new Product("Лук", "Овощи", 0.70, 25, "кг"),
                new Product("Чеснок", "Овощи", 1.20, 80, "кг"),
                new Product("Капуста", "Овощи", 1.30, 15, "кг"),
                new Product("Брокколи", "Овощи", 2.00, 20, "кг"),
                new Product("Шпинат", "Овощи", 2.50, 33, "кг"),

                new Product("Клубника", "Ягоды", 3.00, 15, "л"),
                new Product("Черника", "Ягоды", 4.00, 15, "л"),
                new Product("Малина", "Ягоды", 3.50, 15, "л"),
                new Product("Ежевика", "Ягоды", 4.50, 15, "л"),
                new Product("Клюква", "Ягоды", 2.50, 15, "л"),
                new Product("Крыжовник", "Ягоды", 3.00, 15, "л"),
                new Product("Красная смородина", "Ягоды", 2.80, 15, "л"),
                new Product("Черная смородина", "Ягоды", 3.20, 15, "л"),
                new Product("Шелковица", "Ягоды", 3.50, 15, "л"),
                new Product("Бузина", "Ягоды", 4.00, 15, "л"),

                new Product("Молоко", "Молочные продукты", 1.20, 30, "л"),
                new Product("Йогурт", "Молочные продукты", 0.90, 390, "г"),
                new Product("Сыр", "Молочные продукты", 2.50, 30, "г"),
                new Product("Масло", "Молочные продукты", 1.80, 40, null),
                new Product("Творог", "Молочные продукты", 2.00, 40, null),
                new Product("Сметана", "Молочные продукты", 1.50, 56, null),
                new Product("Крем", "Молочные продукты", 1.70, 5, "г"),
                new Product("Кефир", "Молочные продукты", 1.40, 30, "л"),
                new Product("Сыр Моццарела", "Молочные продукты", 2.80, 15, "г"),
                new Product("Фета", "Молочные продукты", 2.70, 45, null)
        );

        // Добавляем продукты в хранилище
        products.forEach(productStore::addProduct);

        // Проверка количества добавленных продуктов
        assertEquals(40, productStore.getAllProducts().size(), "Должны быть добавлены 40 продуктов.");
    }

    @Test
    void testGetProductsByCategoryFruits() {
        // Добавляем продукты
        productStore.addProduct(new Product("Яблоки", "Фрукты", 1.20, 20, "кг"));
        productStore.addProduct(new Product("Бананы", "Фрукты", 1.00, 30, "кг"));

        List<Product> fruits = productStore.getProductsByCategory("Фрукты");

        assertEquals(2, fruits.size(), "Должны быть найдены 2 фрукта.");
        assertTrue(fruits.stream().anyMatch(p -> p.getName().equals("Яблоко")));
        assertTrue(fruits.stream().anyMatch(p -> p.getName().equals("Банан")));
    }

    @Test
    void testGetAllCategories() {
        // Добавляем несколько продуктов из разных категорий
        productStore.addProduct(new Product("Яблоки", "Фрукты", 1.20, 20, "кг"));
        productStore.addProduct(new Product("Морковки", "Овощи", 0.80, 18, "кг"));
        productStore.addProduct(new Product("Черника", "Ягоды", 4.00, 15, "л"));
        productStore.addProduct(new Product("Молоко", "Молочные продукты", 1.20, 15, "л"));

        Set<String> categories = productStore.getAllCategories();

        assertEquals(4, categories.size(), "Должны быть найдены 4 уникальные категории.");
        assertTrue(categories.contains("Фрукты"));
        assertTrue(categories.contains("Овощи"));
        assertTrue(categories.contains("Ягоды"));
        assertTrue(categories.contains("Молочные продукты"));
    }
}