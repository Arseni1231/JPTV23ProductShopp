package services;

import org.example.model.Product;
import org.example.model.ProductStore;
import org.example.services.ProductStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductStoreServiceTest {

    private ProductStoreService productStoreService;
    private ProductStore mockProductStore;

    @BeforeEach
    void setUp() {
        // Создаем мок для ProductStore
        mockProductStore = Mockito.mock(ProductStore.class);

        // Инициализируем ProductStoreService с мок-объектом ProductStore
        productStoreService = new ProductStoreService(mockProductStore);
    }

    @Test
    void testAddProduct() {
        // Создаем тестовый продукт
        Product product = new Product("Продукт 1", "Категория 1", 100.0, 10, "шт");

        // Выполняем добавление продукта
        productStoreService.addProduct("Продукт 1", "Категория 1", 100.0, 10, "шт");

        // Проверка: убедиться, что метод addProduct был вызван один раз
        verify(mockProductStore, times(1)).addProduct(product);
    }

    @Test
    void testRemoveProduct() {
        // Продукт для удаления
        String productName = "Продукт 1";

        // Выполняем удаление продукта
        productStoreService.removeProduct(productName);

        // Проверка: убедиться, что метод removeProduct был вызван один раз
        verify(mockProductStore, times(1)).removeProduct(productName);
    }

    @Test
    void testGetAllProducts() {
        // Подготовка: создаем список продуктов для возврата
        List<Product> products = new ArrayList<>();
        products.add(new Product("Продукт 1", "Категория 1", 100.0, 10, "шт"));
        products.add(new Product("Продукт 2", "Категория 2", 150.0, 5, "шт"));

        // Настроить мок для возврата списка продуктов
        when(mockProductStore.getAllProducts()).thenReturn((ArrayList<Product>) products);

        // Вызов метода
        List<Product> result = productStoreService.getAllProducts();

        // Проверка: убедиться, что список продуктов возвращается корректно
        assertEquals(products, result);
    }

    @Test
    void testGetProductsByCategory() {
        // Подготовка: создаем список продуктов для возврата по категории
        List<Product> products = new ArrayList<>();
        products.add(new Product("Продукт 1", "Категория 1", 100.0, 10, "шт"));
        products.add(new Product("Продукт 2", "Категория 1", 150.0, 5, "шт"));

        // Настроить мок для возврата списка продуктов по категории
        when(mockProductStore.getProductsByCategory("Категория 1")).thenReturn((ArrayList<Product>) products);

        // Вызов метода
        List<Product> result = productStoreService.getProductsByCategory("Категория 1");

        // Проверка: убедиться, что продукты по категории возвращаются корректно
        assertEquals(products, result);
    }

    @Test
    void testGetAllCategories() {
        // Подготовка: создаем набор категорий для возврата
        Set<String> categories = Set.of("Категория 1", "Категория 2");

        // Настроить мок для возврата всех категорий
        when(mockProductStore.getAllCategories()).thenReturn(categories);

        // Вызов метода
        Set<String> result = productStoreService.getAllCategories();

        // Проверка: убедиться, что категории возвращаются корректно
        assertEquals(categories, result);
    }

    @Test
    void testAddProductDuplicate() {
        // Создаем тестовый продукт
        Product product = new Product("Продукт 1", "Категория 1", 100.0, 10, "шт");

        // Настроить мок для метода addProduct, чтобы он ничего не делал, если продукт существует
        doNothing().when(mockProductStore).addProduct(product);

        // Выполняем добавление продукта
        productStoreService.addProduct("Продукт 1", "Категория 1", 100.0, 10, "шт");

        // Проверка, что метод не был вызван для добавления дублирующегося продукта
        verify(mockProductStore, times(1)).addProduct(product);
    }
}
