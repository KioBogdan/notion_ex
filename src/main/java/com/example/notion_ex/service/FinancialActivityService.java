package com.example.notion_ex.service;

import com.example.notion_ex.dto.FinancialActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public interface FinancialActivityService {
    //READ
    FinancialActivityDTO findFinancialByIDDTO(Long id);
    List<FinancialActivityDTO> findAllDTO();
    List<FinancialActivity> findByExpenseService(String expense);
    List<FinancialActivity> findByAmountService(int amount);
    List<FinancialActivity> findByCategoryService(String category);
    List<FinancialActivity> findByDateService(LocalDate date);

    boolean isNumeric(String s);
    boolean isDate(String s);
    FinancialActivity saveFinancial(User user, FinancialActivity financialActivity);
    Set<FinancialActivity> findByUser(User user);
    List<FinancialActivity> sortByParam(String param);
    //UPDATE
    FinancialActivity updateFinancial(FinancialActivityDTO fActivityDTO);

    //DELETE
    void deleteFinancial(Long id);

    //unused methods
    FinancialActivity findFinancialActivityByID(Long id);
    List<FinancialActivity> findAll();
}
