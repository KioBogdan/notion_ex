package com.example.notion_ex.mapper;

import com.example.notion_ex.dto.FinancialActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.repository.FinancialActivityRepo;
import org.springframework.stereotype.Component;

@Component
public class FinancialActivityMapper {
    public static FinancialActivityDTO mapModelToDto(FinancialActivity financialActivity) {
        FinancialActivityDTO financialActivityDTO = new FinancialActivityDTO();
        financialActivityDTO.setId(financialActivity.getId());
        financialActivityDTO.setExpense(financialActivity.getExpense());
        financialActivityDTO.setAmount(financialActivity.getAmount());
        financialActivityDTO.setCategory(financialActivity.getCategory());
        financialActivityDTO.setDate(financialActivity.getDate());

        return financialActivityDTO;
    }

    public FinancialActivity mapDtoToModel (FinancialActivityDTO financialActivityDTO) {
        FinancialActivity financialActivity = new FinancialActivity();
        financialActivity.setId(financialActivityDTO.getId());
        financialActivity.setExpense(financialActivityDTO.getExpense());
        financialActivity.setAmount(financialActivity.getAmount());
        financialActivity.setCategory(financialActivityDTO.getCategory());
        financialActivity.setDate(financialActivityDTO.getDate());

        return financialActivity;
    }
}
