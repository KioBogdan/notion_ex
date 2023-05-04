package com.example.notion_ex.service;

import com.example.notion_ex.dto.ToDoActivityDTO;
import com.example.notion_ex.model.ToDoActivity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ToDoActivityService {
    ToDoActivityDTO findToDoByID(Long id);
    List<ToDoActivityDTO> findAllDTO();

    //ToDoActivity getFirstToDoByName(String name);

    //UPDATE
    ToDoActivity updateToDo(ToDoActivityDTO toDoActivityDTO);

    //DELETE
    void delete(Long id);
}
