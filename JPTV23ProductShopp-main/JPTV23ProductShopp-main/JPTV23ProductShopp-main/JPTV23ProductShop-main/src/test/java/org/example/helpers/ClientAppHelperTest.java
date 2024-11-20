package org.example.helpers;

import org.example.intefaces.AppHelper;
import org.example.intefaces.Input;
import org.example.model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ClientAppHelperTest {
    Input inputMock;
    AppHelper<Client> clientAppHelper;
    PrintStream defaultOut = System.out;
    ByteArrayOutputStream outMock;

    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        clientAppHelper = new ClientAppHelper(inputMock);
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));
    }

    @AfterEach
    void tearDown() {
        inputMock = null;
        System.setOut(defaultOut);
        outMock=null;
    }

    @Test
    void create_ShouldReturnClientWithValidInput() {
        when(inputMock.nextLine()).thenReturn("Arseni").thenReturn("Solovjov");
        Client actual = clientAppHelper.create();
        Client expected = new Client("Arseni", "Solovjov", "arseni.solovjov@gmail.com", 54129567);
        assertEquals(actual.getFirstName(), expected.getFirstName());
        assertEquals(actual.getLastName(), expected.getLastName());
    }
    @Test
    void create_ShouldReturnNullWhenExceptionOccurs() {
        // Arrange
        when(inputMock.nextLine()).thenThrow(new RuntimeException("Input error"));
        // Act
        Client client = clientAppHelper.create();
        // Assert
        assertNull(client);
    }

    @Test
    void printList_ShouldPrintAuthorsWhenListIsNotEmpty() {
        Client client = new Client("Arseni", "Solovjov", "arseni.solovjov@gmail.com", 54129567);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        boolean result = clientAppHelper.printList(clients);
        boolean expected = true;
        assertTrue(result);
        String expectedString = "1. Arseni Solovjov";
        assertTrue(outMock.toString().contains(expectedString));
    }
    @Test
    void printList_ShouldReturnFalseWhenListIsEmpty() {
        // Arrange
        List<Client> clients = List.of();
        // Act
        boolean result = clientAppHelper.printList(clients);
        // Assert
        assertFalse(result);
    }
    @Test
    void printList_ShouldReturnFalseWhenExceptionOccurs() {
        // Arrange
        List<Client> clients = null; // Simulate an exception scenario
        // Act
        boolean result = clientAppHelper.printList(clients);
        // Assert
        assertFalse(result);
    }
}

