package org.example.helpers;

import org.example.intefaces.Input;
import org.example.intefaces.Service;
import org.example.model.Client;
import org.example.model.HistoryPurchasedProducts;
import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HistoryPurchasedProductsServiceAppHelperTest {

    private HistoryPurchasedProductsAppHelper historyHelper;
    private Input mockInput;
    private Service<Product> mockProductService;
    private Service<Client> mockClientService;

    @BeforeEach
    void setUp() {
        // Создаем моки для зависимостей
        mockInput = Mockito.mock(Input.class);
        mockProductService = Mockito.mock(Service.class);
        mockClientService = Mockito.mock(Service.class);

        // Инициализируем HistoryPurchasedProductsAppHelper с моками
        historyHelper = new HistoryPurchasedProductsAppHelper(mockInput, mockProductService, mockClientService);
    }

    @Test
    void testCreateSuccess() {
        // Настройка моков для успешного создания HistoryPurchasedProducts
        Product mockProduct = new Product();
        Client mockClient = new Client();
        when(mockProductService.print()).thenReturn(true);
        when(mockProductService.list()).thenReturn(List.of(mockProduct));
        when(mockInput.nextLine()).thenReturn("1"); // Выбор продукта
        when(mockClientService.print()).thenReturn(true);
        when(mockClientService.list()).thenReturn(List.of(mockClient));
        when(mockInput.nextLine()).thenReturn("1"); // Выбор клиента

        // Выполнение метода create
        HistoryPurchasedProducts result = historyHelper.create();

        // Проверка результата
        assertNotNull(result);
        assertEquals(mockProduct, result.getProduct());
        assertEquals(mockClient, result.getClient());
        assertEquals(LocalDate.now(), result.getBorrowedProductDate());
    }

    @Test
    void testCreateProductServicePrintFails() {
        // Настройка моков, чтобы productService.print() вернул false
        when(mockProductService.print()).thenReturn(false);

        // Выполнение метода create
        HistoryPurchasedProducts result = historyHelper.create();

        // Проверка результата
        assertNull(result);
    }

    @Test
    void testCreateClientServicePrintFails() {
        // Настройка моков для успешного выбора продукта, но клиентский сервис не удается
        Product mockProduct = new Product();
        when(mockProductService.print()).thenReturn(true);
        when(mockProductService.list()).thenReturn(List.of(mockProduct));
        when(mockInput.nextLine()).thenReturn("1"); // Выбор продукта
        when(mockClientService.print()).thenReturn(false);

        // Выполнение метода create
        HistoryPurchasedProducts result = historyHelper.create();

        // Проверка результата
        assertNull(result);
    }

    // Дополнительные тесты, такие как проверка списка, возврат и т.д., можно создать аналогично.
}

