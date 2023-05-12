package com.example.notion_ex.repository;

import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FinancialActivityRepo extends JpaRepository<FinancialActivity, Long> {
    List<FinancialActivity> findByExpense(String expense);
    List<FinancialActivity> findByAmount(int amount);
    List<FinancialActivity> findByCategory(String category);
    List<FinancialActivity> findByDate(LocalDate date);
    Set<FinancialActivity> findByUserId(Long id);

//    List<FinancialActivity> findByUserSo
    //required method - find by date
}
