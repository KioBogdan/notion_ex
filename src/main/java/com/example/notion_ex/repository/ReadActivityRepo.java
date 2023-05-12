package com.example.notion_ex.repository;

import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ReadActivity;
import com.example.notion_ex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ReadActivityRepo extends JpaRepository<ReadActivity, Long> {
    ReadActivity findReadActivityByType(String type);
    Set<ReadActivity> findByUserId(Long id);
    List<ReadActivity> findByName(String t);
    List<ReadActivity> findByAuthor(String t);
    List<ReadActivity> findByType(String t);
    List<ReadActivity> findByStatus(String t);
    List<ReadActivity> findByScore(int t);
    List<ReadActivity> findByDateOfCompletion(String t);

}
