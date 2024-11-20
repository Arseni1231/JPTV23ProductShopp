package org.example.helpers;

import org.example.intefaces.AppHelper;
import org.example.intefaces.Input;
import org.example.model.Client;
import org.example.model.Product;
import org.example.repositories.Storage;
import org.example.services.ClientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductAppHelperTest {
    Input inputMock;
    ClientAppHelper clientAppHelper;
    AppHelper<Product> productAppHelper;
    ClientService clientService;
    PrintStream defaultOut = System.out;
    ByteArrayOutputStream outMock;

    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        clientAppHelper = mock(ClientAppHelper.class);
        Storage<Client> storage = Mockito.mock(Storage.class);
        clientService = new ClientService(clientAppHelper, storage);
        productAppHelper = new ProductAppHelper(inputMock, clientService);
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));
    }

    @AfterEach
    void tearDown() {
        inputMock = null;
        clientService = null;
        productAppHelper = null;
        System.setOut(defaultOut);
        outMock = null;
    }

    @Test
    void create_ShouldReturnNullWhenAddClientChoiceIsY() {
        when(inputMock.nextLine()).thenReturn("Яблоки", "y");
        Product result = productAppHelper.create();
        assertNull(result);
    }

    @Test
    void create_ShouldReturnProductWithValidInput() {
        Client client = new Client("Arseni", "Solovjov", "arseni@mail.com", "12345");
        List<Client> clients = new ArrayList<>();
        clients.add(client);

        when(clientService.list()).thenReturn(clients);
        when(inputMock.nextLine()).thenReturn("Яблоки", "n", "1", "1", "100");

        Product result = productAppHelper.create();
        Product expected = new Product("Яблоки", "Фрукты");

        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getCategory(), result.getCategory());
        assertEquals(100, result.getPricePerUnit());
    }

    @Test
    void printList_ShouldReturnTrueWhenProductsExist() {
        List<Product> products = List.of(new Product("Яблоки", "Фрукты", 1.20, 20, "кг"));
        productAppHelper.printList(products);

        String output = outMock.toString();
        assertTrue(output.contains("Яблоко"));
    }

    @Test
    void printList_ShouldReturnFalseWhenProductsListIsEmpty() {
        List<Product> products = new ArrayList<>();
        boolean result = productAppHelper.printList(products);

        assertFalse(result);
    }

    @Test
    void edit_ShouldUpdateProductDetailsWhenValidInput() {
        List<Product> products = List.of(new Product("Яблоки", "Фрукты", 1.20, 20, "кг"));

        when(inputMock.nextLine()).thenReturn(
                "1",  // номер продукта
                "y",  // изменить: да
                "Банан",  // новое название
                "n",  // изменить категорию: нет
                "y",  // изменить цену: да
                "1.50",  // новая цена
                "y",  // изменить количество: да
                "25"   // новое количество
        );

        List<Product> result = productAppHelper.edit(products);
        assertEquals("Банан", result.get(0).getName());
        assertEquals(60, result.get(0).getPricePerUnit());
        assertEquals(25, result.get(0).getQuantity());
    }

    @Test
    void edit_ShouldReturnNullWhenExceptionOccurs() {
        List<Product> products = new ArrayList<>();
        when(inputMock.nextLine()).thenThrow(new RuntimeException("Input error"));

        List<Product> result = productAppHelper.edit(products);
        assertNull(result);
    }
}