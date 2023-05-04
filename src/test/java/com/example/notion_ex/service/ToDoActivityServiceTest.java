package com.example.notion_ex.service;

import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.model.ToDoActivity;
import com.example.notion_ex.repository.ProjectActivityRepo;
import com.example.notion_ex.repository.ToDoActivityRepo;
import com.example.notion_ex.service.impl.ProjectActivityImpl;
import com.example.notion_ex.service.impl.ToDoActivityImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ToDoActivityServiceTest {
    /*private static final String TASK_NAME = "Must implement Java functions";
    private static final String NOT_TASK_NAME = "Or else, my grade at PS is going to be bad";

    private ToDoActivityImpl toDoActivityImpl;

    @Mock
    private ToDoActivityRepo toDoActivityRepo;

    private ToDoActivity todoAct;

    @BeforeEach
    void init() {
        initMocks(this);
        todoAct = new ToDoActivity();
        todoAct.setTaskName(TASK_NAME);
        when(toDoActivityRepo.findFirstByTaskName(TASK_NAME)).thenReturn(todoAct);
    }

    @Test
    void givenExistingToDoTask_Name_whenFindToDoActivtiyByTaskName_thenFindOne() {
        toDoActivityImpl = new ToDoActivityImpl(toDoActivityRepo);
        ToDoActivity tdAct = toDoActivityImpl.getFirstToDoByName(TASK_NAME);

        assertNotNull(tdAct);
        assertEquals(TASK_NAME, tdAct.getTaskName());
    }

    @Test
    void givenNonExistingToDoTask_Name_whenFindByTaskName_thenThrowException() {
        when(toDoActivityRepo.findFirstByTaskName(NOT_TASK_NAME)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            toDoActivityImpl.getFirstToDoByName(NOT_TASK_NAME);
        });
    }*/
}
