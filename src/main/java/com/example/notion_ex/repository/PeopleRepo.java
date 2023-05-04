package com.example.notion_ex.repository;

import com.example.notion_ex.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepo extends JpaRepository<People, Long> {
}
