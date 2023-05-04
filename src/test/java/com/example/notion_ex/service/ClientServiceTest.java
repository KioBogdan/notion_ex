package com.example.notion_ex.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ClientServiceTest {
    /*private static final String CLIENT_NAME = "Petrica";
    private static final String NOT_CLIENT_NAME = "Not Petrica, someone else";

    private ClientImpl clientImpl;

    @Mock
    private ClientRepo clientRepo;

    private Client client;

    @BeforeEach
    void init() {
        initMocks(this);
        client = new Client();
        client.setName(CLIENT_NAME);
        when(clientRepo.findClientByName(CLIENT_NAME)).thenReturn(client);
    }

    @Test
    void givenExistingClient_Name_whenFindByName_thenFindOne() {
        clientImpl = new ClientImpl(clientRepo);
        Client client2 = clientImpl.findByName(CLIENT_NAME);

        assertNotNull(client2);
        assertEquals(CLIENT_NAME, client2.getName());
    }

    @Test
    void givenNonExistingAdmin_Name_whenFindByName_thenThrowException() {
        when(clientRepo.findClientByName(NOT_CLIENT_NAME)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            clientImpl.findByName(NOT_CLIENT_NAME);
        });
    }*/
}
