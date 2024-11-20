package services;

import org.example.intefaces.AppHelper;
import org.example.intefaces.Repository;
import org.example.model.Client;
import org.example.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    private ClientService clientService;
    private Repository<Client> mockRepository;
    private AppHelper<Client> mockAppHelper;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(Repository.class);
        mockAppHelper = Mockito.mock(AppHelper.class);
        clientService = new ClientService(mockAppHelper, mockRepository);
    }

    @Test
    void testAddClientSuccess() {
        // Подготовка данных
        Client newClient = new Client("Arseni", "Solovjov", "arseni.solovjov@gmail.com", "54129567");
        when(mockAppHelper.create()).thenReturn(newClient);

        // Вызов метода
        boolean result = clientService.add();

        // Проверка результатов
        assertTrue(result, "Метод add должен вернуть true при успешном добавлении клиента.");
        verify(mockRepository, times(1)).save(newClient);
    }

    @Test
    void testAddClientFailure() {
        // Подготовка данных
        when(mockAppHelper.create()).thenReturn(null);

        // Вызов метода
        boolean result = clientService.add();

        // Проверка результатов
        assertFalse(result, "Метод add должен вернуть false, если клиент не создан.");
        verify(mockRepository, never()).save(any(Client.class));
    }

    @Test
    void testEditClientsSuccess() {
        // Подготовка данных
        List<Client> existingClients = new ArrayList<>();
        existingClients.add(new Client("Arseni", "Solovjov", "arseni.solovjov@gmail.com", "54129567"));
        when(mockRepository.load()).thenReturn(existingClients);

        List<Client> modifiedClients = new ArrayList<>();
        modifiedClients.add(new Client("Marina", "Kaits", "marina.kaits@gmail.com", "59435725"));
        when(mockAppHelper.edit(existingClients)).thenReturn(modifiedClients);

        // Вызов метода
        boolean result = clientService.edit();

        // Проверка результатов
        assertTrue(result, "Метод edit должен вернуть true при успешном редактировании клиентов.");
        verify(mockRepository, times(1)).saveAll(modifiedClients);
    }

    @Test
    void testEditClientsFailure() {
        // Подготовка данных
        when(mockRepository.load()).thenReturn(new ArrayList<>());
        when(mockAppHelper.edit(anyList())).thenReturn(new ArrayList<>());

        // Вызов метода
        boolean result = clientService.edit();

        // Проверка результатов
        assertFalse(result, "Метод edit должен вернуть false, если список клиентов пустой или не изменен.");
        verify(mockRepository, never()).saveAll(anyList());
    }

    @Test
    void testPrintClients() {
        // Подготовка данных
        List<Client> existingClients = new ArrayList<>();
        existingClients.add(new Client("Arseni", "Solovjov", "arseni.solovjov@gmail.com", "54129567"));
        when(mockRepository.load()).thenReturn(existingClients);
        when(mockAppHelper.printList(existingClients)).thenReturn(true);

        // Вызов метода
        boolean result = clientService.print();

        // Проверка результатов
        assertTrue(result, "Метод print должен вернуть true при успешной печати списка клиентов.");
        verify(mockAppHelper, times(1)).printList(existingClients);
    }

    @Test
    void testPrintClientsFailure() {
        // Подготовка данных
        when(mockRepository.load()).thenReturn(new ArrayList<>());
        when(mockAppHelper.printList(anyList())).thenReturn(false);

        // Вызов метода
        boolean result = clientService.print();

        // Проверка результатов
        assertFalse(result, "Метод print должен вернуть false, если печать списка клиентов не удалась.");
        verify(mockAppHelper, times(1)).printList(anyList());
    }

    @Test
    void testListClients() {
        // Подготовка данных
        List<Client> existingClients = new ArrayList<>();
        existingClients.add(new Client("Arseni", "Solovjov", "arseni.solovjov@gmail.com", "54129567"));
        when(mockRepository.load()).thenReturn(existingClients);

        // Вызов метода
        List<Client> result = clientService.list();

        // Проверка результатов
        assertNotNull(result, "Список клиентов не должен быть null.");
        assertEquals(1, result.size(), "Список клиентов должен содержать одного клиента.");
        assertEquals(existingClients, result, "Метод list должен возвращать список клиентов из репозитория.");
    }
}

