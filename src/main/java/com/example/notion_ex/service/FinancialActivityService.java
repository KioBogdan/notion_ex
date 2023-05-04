package com.example.notion_ex.service;

import com.example.notion_ex.dto.FinancialActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface FinancialActivityService {
    //READ
    FinancialActivityDTO findFinancialByIDDTO(Long id);
    List<FinancialActivityDTO> findAllDTO();
    Optional<FinancialActivity> findByExpense(String expense);

    //UPDATE
    FinancialActivity updateFinancial(FinancialActivityDTO fActivityDTO);

    //DELETE
    void deleteFinancial(Long id);

    //unused methods
    FinancialActivity findFinancialActivityByID(Long id);
    List<FinancialActivity> findAll();
}
