package com.example.notion_ex.repository;

import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface ProjectActivityRepo extends JpaRepository<ProjectActivity, Long> {
    ProjectActivity findProjectActivityByProjectName(String s);
    Set<ProjectActivity> findByUserId(Long id);
    List<ProjectActivity> findByTask(String t);
    List<ProjectActivity> findByProjectName(String t);
    List<ProjectActivity> findByStatus(String t);
    List<ProjectActivity> findByDateStart(LocalDate d);
    List<ProjectActivity> findByDateFinish(LocalDate d);
}
