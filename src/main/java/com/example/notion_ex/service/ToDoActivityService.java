package com.example.notion_ex.service;

import com.example.notion_ex.dto.ToDoActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ToDoActivity;
import com.example.notion_ex.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface ToDoActivityService {
    ToDoActivityDTO findToDoByID(Long id);
    List<ToDoActivityDTO> findAllDTO();
    ToDoActivity saveToDo(User user, ToDoActivity toDoActivity);
    Set<ToDoActivity> findByUser(User user);
    List<ToDoActivity> findByTaskNameService(String s);
    List<ToDoActivity> findByDueService(String s);
    List<ToDoActivity> sortByParam(String param);
    //ToDoActivity getFirstToDoByName(String name);

    //UPDATE
    ToDoActivity updateToDo(ToDoActivityDTO toDoActivityDTO);

    //DELETE
    void delete(Long id);
}
