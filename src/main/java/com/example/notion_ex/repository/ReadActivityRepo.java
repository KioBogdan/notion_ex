package com.example.notion_ex.repository;

import com.example.notion_ex.model.ReadActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadActivityRepo extends JpaRepository<ReadActivity, Long> {
    ReadActivity findReadActivityByType(String type);
}
