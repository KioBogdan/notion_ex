package com.example.notion_ex.repository;

import com.example.notion_ex.model.FinancialActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinancialActivityRepo extends JpaRepository<FinancialActivity, Long> {
    Optional<FinancialActivity> findByExpense(String expense);
    //required method - find by date
}
