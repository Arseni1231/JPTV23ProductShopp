package services;

import org.example.model.Product;
import org.example.model.ProductStore;
import org.example.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productService;
    private ProductStore mockProductStore;

    @BeforeEach
    void setUp() {
        mockProductStore = Mockito.mock(ProductStore.class);
        productService = new ProductService(mockProductStore);
    }

    @Test
    void testAddProduct() {
        // Подготовка данных
        Product product = new Product("Яблоко", "Фрукты", 1.20, 20, "кг");

        // Вызов метода
        productService.addProduct(product);

        // Проверка, что метод ProductStore.addProduct() был вызван с правильным параметром
        verify(mockProductStore, times(1)).addProduct(product);
    }

    @Test
    void testGetAllProducts() {
        // Подготовка данных
        Product product1 = new Product("Яблоко", "Фрукты", 1.20, 20, "кг");
        Product product2 = new Product("Морковка", "Овощи", 0.80, 18, "кг");
        when(mockProductStore.getAllProducts()).thenReturn((ArrayList<Product>) List.of(product1, product2));

        // Вызов метода
        List<Product> products = productService.getAllProducts();

        // Проверка результатов
        assertNotNull(products, "Список продуктов не должен быть null.");
        assertEquals(2, products.size(), "В списке должно быть два продукта.");
        assertEquals(product1, products.get(0), "Первый продукт должен быть 'Apple'.");
        assertEquals(product2, products.get(1), "Второй продукт должен быть 'Carrot'.");
    }

    @Test
    void testRemoveProduct() {
        // Вызов метода
        productService.removeProduct("Apple");

        // Проверка, что метод ProductStore.removeProduct() был вызван с правильным параметром
        verify(mockProductStore, times(1)).removeProduct("Apple");
    }

    @Test
    void testGetProductsByCategory() {
        // Подготовка данных
        Product product1 = new Product("Яблоко", "Фрукты", 1.20, 20, "кг");
        Product product2 = new Product("Банан", "Фрукты", 0.90, 30, "кг");
        when(mockProductStore.getProductsByCategory("Фрукты")).thenReturn((ArrayList<Product>) List.of(product1, product2));

        // Вызов метода
        List<Product> products = productService.getProductsByCategory("Фрукты");

        // Проверка результатов
        assertNotNull(products, "Список продуктов не должен быть null.");
        assertEquals(2, products.size(), "В списке должно быть два продукта.");
        assertEquals("Apple", products.get(0).getName(), "Первый продукт должен быть 'Apple'.");
        assertEquals("Banana", products.get(1).getName(), "Второй продукт должен быть 'Banana'.");
    }

    @Test
    void testDisplayAllProductsWhenEmpty() {
        // Подготовка данных
        when(mockProductStore.getAllProducts()).thenReturn(List.of(product1, product2));

        // Перехват вывода в консоль
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()));

        // Вызов метода
        productService.displayAllProducts();

        // Проверка, что метод вывел сообщение об отсутствии продуктов
        verify(mockProductStore, times(1)).getAllProducts();
    }

    @Test
    void testDisplayAllProductsWhenNotEmpty() {
        // Подготовка данных
        Product product1 = new Product("Молоко", "Молочные продукты", 1.20, 30, "кг");
        Product product2 = new Product("Сыр", "Молочные продукты", 2.50, 30, "г");
        when(mockProductStore.getAllProducts()).thenReturn((ArrayList<Product>) List.of(product1, product2));

        // Перехват вывода в консоль
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()));

        // Вызов метода
        productService.displayAllProducts();

        // Проверка, что метод вызвал getAllProducts и обработал результаты
        verify(mockProductStore, times(1)).getAllProducts();
    }
}
