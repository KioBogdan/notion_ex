package com.example.notion_ex.controller;

import com.example.notion_ex.dto.ToDoActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ToDoActivity;
import com.example.notion_ex.model.User;
import com.example.notion_ex.repository.ToDoActivityRepo;
import com.example.notion_ex.service.ToDoActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/todo")
@RequiredArgsConstructor
public class ToDoActivityController {

    private final ToDoActivityService toDoActivityService;
    private final ToDoActivityRepo toDoActivityRepo;

    @PutMapping("")
    public ResponseEntity createToDo(@AuthenticationPrincipal User user, @RequestBody ToDoActivity toDoActivity){
        //ToDoActivity toDoActivity = toDoActivityService.saveToDo(user);

        return ResponseEntity.ok(toDoActivityService.saveToDo(user, toDoActivity));
    }

    @GetMapping("")
    public ResponseEntity getToDoActivities(@AuthenticationPrincipal User user) {
        Set<ToDoActivity> toDoActivitySet = toDoActivityRepo.findByUserId(user.getId());
        return ResponseEntity.ok(toDoActivitySet);
    }

    @GetMapping("/sort/{param}")
    public ResponseEntity getToDoActivitiesSorted(@AuthenticationPrincipal User user, @PathVariable String param) {
        //Set<FinancialActivity> financialActivities = financialActivityRepo.findByUserId(user.getId());
        List<ToDoActivity> toDoActivities = toDoActivityService.sortByParam(param);
        return ResponseEntity.ok(toDoActivities);
    }

    @GetMapping("/search/{param}")
    public ResponseEntity getToDoActivitiesSearch(@AuthenticationPrincipal User user, @PathVariable String param) {
        List<ToDoActivity> toDoActivities = toDoActivityService.findByTaskNameService(param);
        return ResponseEntity.ok(toDoActivities);
    }

    //code that ill use later
    @GetMapping("/todoDTO")
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
