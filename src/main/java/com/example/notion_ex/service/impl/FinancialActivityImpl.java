package com.example.notion_ex.service.impl;

import com.example.notion_ex.dto.FinancialActivityDTO;
import com.example.notion_ex.mapper.FinancialActivityMapper;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.User;
import com.example.notion_ex.repository.FinancialActivityRepo;
import com.example.notion_ex.repository.UserRepo;
import com.example.notion_ex.service.FinancialActivityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FinancialActivityImpl implements FinancialActivityService {
    private final FinancialActivityRepo financialActivityRepo;

    @Autowired
    private final UserRepo userRepo;

    private final FinancialActivityMapper financialActivityMapper;

    public FinancialActivityImpl(FinancialActivityRepo finActRepo, UserRepo userRepo, FinancialActivityMapper financialActivityMapper) {
        this.financialActivityRepo = finActRepo;
        this.userRepo = userRepo;
        this.financialActivityMapper = financialActivityMapper;
    }

    //READ
    @Override
    public FinancialActivity findFinancialActivityByID(Long id){ return financialActivityRepo.findById(id).orElse(null); }

    @Override
    public FinancialActivityDTO findFinancialByIDDTO(Long id) {
        final FinancialActivity financialActivity = financialActivityRepo.findById(id).orElseThrow(
                () ->  { throw new EntityNotFoundException("Cannot find financial task with given Id"); }
        );
        return financialActivityMapper.mapModelToDto(financialActivity);
    }

    @Override
    public List<FinancialActivityDTO> findAllDTO() {
        return financialActivityRepo.findAll().stream()
                .map(FinancialActivityMapper::mapModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FinancialActivity> findAll() {
        return financialActivityRepo.findAll();
    }

    @Override
    public Optional<FinancialActivity> findByExpense(String expense) {
        return financialActivityRepo.findByExpense(expense);
    }

    //UPDATE
    @Override
    public FinancialActivity updateFinancial(FinancialActivityDTO fActivityDTO) {
        FinancialActivity financialActivity = financialActivityRepo.findById(fActivityDTO.getId()).get();
        User user = userRepo.findById(fActivityDTO.getId()).get();
        user.getFinancialActivityList().add(financialActivity);

        return financialActivityRepo.save(financialActivity);
    }

    //DELETE
    @Override
    public void deleteFinancial(Long id) {
        FinancialActivity financialActivity = financialActivityRepo.findById(id).get();
        financialActivityRepo.delete(financialActivity);
        //return financialActivity;
    }

}
