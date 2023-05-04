package com.example.notion_ex.controller;

import com.example.notion_ex.dto.ToDoActivityDTO;
import com.example.notion_ex.model.ToDoActivity;
import com.example.notion_ex.service.ToDoActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoActivityController {
    @Autowired
    private ToDoActivityService toDoActivityService;

    @GetMapping()
    public List<ToDoActivityDTO> findAllDto() { return toDoActivityService.findAllDTO(); }

    @GetMapping("/find/{id}")
    public ResponseEntity findToDoById (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(toDoActivityService.findToDoByID(id));
    }

    @PutMapping("/update")
    public ToDoActivity updateToDo(@RequestBody ToDoActivityDTO toDoActivityDTO) {
        return toDoActivityService.updateToDo(toDoActivityDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteToDo(@PathVariable Long id) { toDoActivityService.delete(id); }

}
