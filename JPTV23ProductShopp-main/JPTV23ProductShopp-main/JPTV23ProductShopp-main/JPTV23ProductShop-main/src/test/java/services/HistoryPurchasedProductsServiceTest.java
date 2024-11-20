package services;



import org.example.helpers.HistoryPurchasedProductsAppHelper;
import org.example.model.HistoryPurchasedProducts;
import org.example.intefaces.AppHelper;
import org.example.intefaces.Repository;

import org.example.services.HistoryPurchasedProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HistoryPurchasedProductsServiceTest {

    private HistoryPurchasedProductsService historyPurchasedProductsService;
    private AppHelper<HistoryPurchasedProducts> mockHistoryPurchasedProductsAppHelper;

    private Repository<HistoryPurchasedProducts> mockRepository;


    @BeforeEach
    void setUp() {
        // Создаем моки для зависимостей
        mockHistoryPurchasedProductsAppHelper = Mockito.mock(AppHelper.class);
        mockRepository = Mockito.mock(Repository.class);


        // Инициализируем HistoryPurchasedProductsService с моками
        historyPurchasedProductsService = new HistoryPurchasedProductsService(mockHistoryPurchasedProductsAppHelper, mockRepository);
    }

    @Test
    void testAddHistoryPurchasedProductSuccess() {
        // Подготовка: создать HistoryPurchasedProducts и настроить заглушки
        HistoryPurchasedProducts mockHistoryPurchasedProducts = new HistoryPurchasedProducts();
       when(mockHistoryPurchasedProductsAppHelper.create()).thenReturn(mockHistoryPurchasedProducts);

        boolean result = HistoryPurchasedProductsService.add();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).save(mockHistoryPurchasedProducts); // Убедиться, что метод save был вызван один раз
    }

    @Test
    void testAddHistoryPurchasedProductFailureWhenHistoryIsNull() {
        // Настроить заглушку, чтобы create возвращал null
        when(mockHistoryPurchasedProductsAppHelper.create()).thenReturn(null);

        // Выполняем метод add
        boolean result = historyPurchasedProductsService.add();

        // Проверка
        assertFalse(result);
        verify(mockRepository, never()).save(any()); // Убедиться, что метод save не был вызван
    }

    @Test
    void testAddHistoryPurchasedProductExceptionHandling() {
       HistoryPurchasedProducts mockHistoryPurchasedProducts = new HistoryPurchasedProducts();
       when(mockHistoryPurchasedProductsAppHelper.create()).thenReturn(mockHistoryPurchasedProducts);
       doThrow(new RuntimeException("Save error")).when(mockRepository).save(mockHistoryPurchasedProducts);
    }

    @Test
    void testPrintHistoryPurchasedProducts() {
        // Подготовка: создать список LibraryCart и настроить заглушки
        List<HistoryPurchasedProducts> mockHistoryPurchasedProductsList = List.of(new HistoryPurchasedProducts());
        when(mockRepository.load()).thenReturn(mockHistoryPurchasedProductsList);
        when(mockHistoryPurchasedProductsAppHelper.printList(mockHistoryPurchasedProductsList)).thenReturn(true);

        // Выполняем метод print
        boolean result = historyPurchasedProductsService.print();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).load(); // Убедиться, что метод load был вызван один раз
        verify(mockHistoryPurchasedProductsAppHelper, times(1)).printList(mockHistoryPurchasedProductsList); // Убедиться, что метод printList был вызван один раз
    }

    @Test
    void testHistoryPurchasedProductsList() {
        // Подготовка: создать список LibraryCart и настроить заглушки
        List<HistoryPurchasedProducts> mockHistoryPurchasedProductsList = List.of(new HistoryPurchasedProducts());
        when(mockRepository.load()).thenReturn(mockHistoryPurchasedProductsList);

        // Выполняем метод list
        List<HistoryPurchasedProducts> result = historyPurchasedProductsService.list();

        // Проверка
        assertEquals(mockHistoryPurchasedProductsList, result);
        verify(mockRepository, times(1)).load(); // Убедиться, что метод load был вызван один раз
    }

    @Test
    void testReturnProductSuccess() {
        // Подготовка: создать список HistoryPurchasedProducts и настроить моки
        HistoryPurchasedProductsAppHelper mockHistoryPurchasedProductsAppHelperCast = mock(HistoryPurchasedProductsAppHelper.class);
        List<HistoryPurchasedProducts> mockHistoryPurchasedProductsList = List.of(new HistoryPurchasedProducts());
        when(mockHistoryPurchasedProductsAppHelperCast.returnBack(mockHistoryPurchasedProductsList)).thenReturn(mockHistoryPurchasedProductsList);
        when(mockRepository.load()).thenReturn(mockHistoryPurchasedProductsList);

        // Вызов метода returnBook
        boolean result = new HistoryPurchasedProductsService(mockHistoryPurchasedProductsAppHelperCast, mockRepository).returnProduct();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).saveAll(mockHistoryPurchasedProductsList); // Убедиться, что метод saveAll был вызван один раз
    }

    @Test
    void testReturnProductFailure() {
        // Подготовка: создать список HistoryPurchasedProducts и настроить заглушки
        HistoryPurchasedProductsAppHelper mockHistoryPurchasedProductsAppHelperCast = mock(HistoryPurchasedProductsAppHelper.class);
        List<HistoryPurchasedProducts> mockHistoryPurchasedProductsList = List.of(new HistoryPurchasedProducts());
        when(mockHistoryPurchasedProductsAppHelperCast.returnBack(mockHistoryPurchasedProductsList)).thenReturn(null);
        when(mockRepository.load()).thenReturn(mockHistoryPurchasedProductsList);

        // Вызов метода returnProduct
        boolean result = new HistoryPurchasedProductsService(mockHistoryPurchasedProductsAppHelperCast, mockRepository).returnProduct();

        // Проверка
        assertFalse(result);
        verify(mockRepository, never()).saveAll(any()); // Убедиться, что метод saveAll не был вызван
    }
}
