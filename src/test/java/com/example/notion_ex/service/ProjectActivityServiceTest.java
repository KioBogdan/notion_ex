package com.example.notion_ex.service;

import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.repository.ProjectActivityRepo;
import com.example.notion_ex.service.impl.ProjectActivityImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProjectActivityServiceTest {
    /*private static final String PROJECT_NAME = "Dealing with Java bugs";
    private static final String NOT_PROJECT_NAME = "Not dealing, just trying";

    private ProjectActivityImpl projectActivityImpl;

    @Mock
    private ProjectActivityRepo projectActivityRepo;

    private ProjectActivity projAct;

    @BeforeEach
    void init() {
        initMocks(this);
        projAct = new ProjectActivity();
        projAct.setProjectName(PROJECT_NAME);
        when(projectActivityRepo.findProjectActivityByProjectName(PROJECT_NAME)).thenReturn(projAct);
    }

    @Test
    void givenExistingProject_Name_whenFindProjectActivtiyByProjectName_thenFindOne() {
        projectActivityImpl = new ProjectActivityImpl(projectActivityRepo);
        ProjectActivity prAct = projectActivityImpl.findProjectByName(PROJECT_NAME);

        assertNotNull(prAct);
        assertEquals(PROJECT_NAME, prAct.getProjectName());
    }

    @Test
    void givenNonExistingProject_Name_whenFindByProjectName_thenThrowException() {
        when(projectActivityRepo.findProjectActivityByProjectName(NOT_PROJECT_NAME)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            projectActivityImpl.findProjectByName(NOT_PROJECT_NAME);
        });
    }*/
}
