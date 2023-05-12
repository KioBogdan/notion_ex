package com.example.notion_ex.repository;

import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ToDoActivity;
import com.example.notion_ex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ToDoActivityRepo extends JpaRepository<ToDoActivity, Long> {
    ToDoActivity findFirstByTaskName(String taskName);
    Set<ToDoActivity> findByUserId(Long id);
    List<ToDoActivity> findByTaskName(String s);
    List<ToDoActivity> findByDue(String s);
}
