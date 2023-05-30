package com.example.notion_ex.service.impl;

import com.example.notion_ex.dto.FinancialActivityDTO;
import com.example.notion_ex.mapper.FinancialActivityMapper;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ToDoActivity;
import com.example.notion_ex.model.User;
import com.example.notion_ex.repository.FinancialActivityRepo;
import com.example.notion_ex.repository.UserRepo;
import com.example.notion_ex.service.FinancialActivityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinancialActivityImpl implements FinancialActivityService {
    private final FinancialActivityRepo financialActivityRepo;
    private final FinancialActivityMapper financialActivityMapper;
    private final UserRepo userRepo;

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
    public FinancialActivity saveFinancial(User user, FinancialActivity financialActivity) {
        //FinancialActivity financialActivity = new FinancialActivity();
        //financialActivity.setCategory("Dunno what this is");
        financialActivity.setUser(user);

        return financialActivityRepo.save(financialActivity);
    }

    @Override
    public Set<FinancialActivity> findByUser(User user) {
        Set<FinancialActivity> financials = financialActivityRepo.findByUserId(user.getId());
        if(financials.isEmpty()) {
            throw new ArrayStoreException("Cannot find any financials by user. Check the given user");
        }
        else return financials;
    }

    @Override
    public List<FinancialActivity> findAll() {
        return financialActivityRepo.findAll();
    }

    @Override
    public List<FinancialActivity> sortByParam(String param) {
        return financialActivityRepo.findAll(Sort.by(Sort.Direction.ASC, param));
    }

    @Override
    public List<FinancialActivity> findByExpenseService(String expense) {
        List<FinancialActivity> financials = financialActivityRepo.findByExpense(expense);
        if(financials.isEmpty()) {
            throw new ArrayStoreException("Cannot find any financials by expense. Check the given expense");
        }
        else return financials;
    }

    @Override
    public List<FinancialActivity> findByAmountService(int amount) {
        List<FinancialActivity> financials = financialActivityRepo.findByAmount(amount);
        if(financials.isEmpty()) {
            throw new ArrayStoreException("Cannot find any financials by amount. Check the given amount");
        }
        else return financials;
    }

    @Override
    public List<FinancialActivity> findByCategoryService(String category) {
        List<FinancialActivity> financials = financialActivityRepo.findByCategory(category);
        if(financials.isEmpty()) {
            throw new ArrayStoreException("Cannot find any financials by category. Check the given category");
        }
        else return financials;
    }

    @Override
    public List<FinancialActivity> findByDateService(LocalDate date) {
        List<FinancialActivity> financials = financialActivityRepo.findByDate(date);
        if(financials.isEmpty()) {
            throw new ArrayStoreException("Cannot find any financials by date. Check the given date");
        }
        else return financials;
    }

    @Override
    public boolean isNumeric(String s) {
            if (s == null) {
                return false;
            }
            try {
                double d = Double.parseDouble(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
    }

    @Override
    public boolean isDate(String s) {
        if (s == null) {
            return false;
        }
        try {
            LocalDate d = LocalDate.parse(s);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    //UPDATE
    @Override
    public FinancialActivity updateFinancial(FinancialActivityDTO fActivityDTO) {
        FinancialActivity financialActivity = financialActivityRepo.findById(fActivityDTO.getId()).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find financial task with given Id"); }
        );
        User user = userRepo.findById(fActivityDTO.getId()).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find user for financial Id"); }
        );
        user.getFinancialActivityList().add(financialActivity);

        return financialActivityRepo.save(financialActivity);
    }

    //DELETE
    @Override
    public void deleteFinancial(Long id) {
        FinancialActivity financialActivity = financialActivityRepo.findById(id).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find read task with given Id"); }
        );
        financialActivityRepo.delete(financialActivity);
        //return financialActivity;
    }

}
