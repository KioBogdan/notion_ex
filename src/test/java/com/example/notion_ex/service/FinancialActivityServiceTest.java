package com.example.notion_ex.service;

import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.repository.FinancialActivityRepo;
import com.example.notion_ex.service.impl.FinancialActivityImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FinancialActivityServiceTest {
    /*private static final String EXPENSE = "Groceries";
    private static final String NOT_EXPENSE = "Not groceries, nor test success";

    private FinancialActivityImpl financialActivityImpl;

    @Mock
    private FinancialActivityRepo financialActivityRepo;

    private FinancialActivity finAct;

    @BeforeEach
    void init() {
        initMocks(this);
        finAct = new FinancialActivity();
        finAct.setExpense(EXPENSE);
        when(financialActivityRepo.findFirstByExpense(EXPENSE)).thenReturn(finAct);
    }

    @Test
    void givenExistingExpense_whenFindByExpense_thenFindOne() {
        financialActivityImpl = new FinancialActivityImpl(financialActivityRepo);
        FinancialActivity finAct = financialActivityImpl.findByExpense(EXPENSE);

        assertNotNull(finAct);
        assertEquals(EXPENSE, finAct.getExpense());
    }

    @Test
    void givenNonExistingExpense_whenFindByExpense_thenThrowException() {
        when(financialActivityRepo.findFirstByExpense(NOT_EXPENSE)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            financialActivityImpl.findByExpense(NOT_EXPENSE);
        });
    }*/
}
