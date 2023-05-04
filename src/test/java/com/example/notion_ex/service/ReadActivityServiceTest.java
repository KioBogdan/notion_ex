package com.example.notion_ex.service;

import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.model.ReadActivity;
import com.example.notion_ex.repository.ProjectActivityRepo;
import com.example.notion_ex.repository.ReadActivityRepo;
import com.example.notion_ex.service.impl.ProjectActivityImpl;
import com.example.notion_ex.service.impl.ReadActivityImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReadActivityServiceTest {
    /*private static final String READ_TYPE = "Book";
    private static final String NOT_READ_TYPE = "Clearly not book";

    private ReadActivityImpl readActivityImpl;

    @Mock
    private ReadActivityRepo readActivityRepo;

    private ReadActivity readAct;

    @BeforeEach
    void init() {
        initMocks(this);
        readAct = new ReadActivity();
        readAct.setType(READ_TYPE);
        when(readActivityRepo.findReadActivityByType(READ_TYPE)).thenReturn(readAct);
    }

    @Test
    void givenExistingRead_Type_whenFindReadActivityByType_thenFindOne() {
        readActivityImpl = new ReadActivityImpl(readActivityRepo);
        ReadActivity readAct = readActivityImpl.getReadByType(READ_TYPE);

        assertNotNull(readAct);
        assertEquals(READ_TYPE, readAct.getType());
    }

    @Test
    void givenNonExistingRead_Type_whenFindByType_thenThrowException() {
        when(readActivityRepo.findReadActivityByType(NOT_READ_TYPE)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            readActivityImpl.getReadByType(NOT_READ_TYPE);
        });
    }*/
}
