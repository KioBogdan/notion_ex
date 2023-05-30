package com.example.notion_ex.service.impl;

import com.example.notion_ex.dto.ToDoActivityDTO;
import com.example.notion_ex.mapper.ToDoActivityMapper;
import com.example.notion_ex.model.*;
import com.example.notion_ex.repository.ToDoActivityRepo;
import com.example.notion_ex.repository.UserRepo;
import com.example.notion_ex.service.ToDoActivityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ToDoActivityImpl implements ToDoActivityService {
    private final ToDoActivityRepo toDoActivityRepo;

    @Autowired
    private final UserRepo userRepo;
    private final ToDoActivityMapper toDoActivityMapper;

    public ToDoActivityImpl(ToDoActivityRepo toDoActRepo, UserRepo userRepo, ToDoActivityMapper toDoActivityMapper) {
        this.toDoActivityRepo = toDoActRepo;
        this.userRepo = userRepo;
        this.toDoActivityMapper = toDoActivityMapper;
    }

    //READ
    @Override
    public ToDoActivityDTO findToDoByID(Long id) {
        final ToDoActivity toDoActivity = toDoActivityRepo.findById(id).orElseThrow(
                () ->  { throw new EntityNotFoundException("Cannot find To Do task with given Id"); }
        );
        return toDoActivityMapper.mapModelToDto(toDoActivity);
    }

    @Override
    public List<ToDoActivityDTO> findAllDTO() {
        return toDoActivityRepo.findAll().stream().
                map(ToDoActivityMapper::mapModelToDto).
                collect(Collectors.toList());
    }

    @Override
    public ToDoActivity saveToDo(User user, ToDoActivity toDoActivity) {
        toDoActivity.setUser(user);

        return toDoActivityRepo.save(toDoActivity);
    }

    @Override
    public List<ToDoActivity> sortByParam(String param) {
        return toDoActivityRepo.findAll(Sort.by(Sort.Direction.ASC, param));
    }

    @Override
    public Set<ToDoActivity> findByUser(User user) {
        Set<ToDoActivity> todos = toDoActivityRepo.findByUserId(user.getId());
        if(todos.isEmpty()) {
            throw new ArrayStoreException("Cannot find any todos by user. Check the given user");
        }
        else return todos;
    }

    @Override
    public List<ToDoActivity> findByTaskNameService(String s) {
        List<ToDoActivity> todos = toDoActivityRepo.findByTaskName(s);
        if(todos.isEmpty()) {
            throw new ArrayStoreException("Cannot find any todos by task name. Check the given task name");
        }
        else return todos;
    }

    @Override
    public List<ToDoActivity> findByDueService(String s) {
        List<ToDoActivity> todos = toDoActivityRepo.findByDue(s);
        if(todos.isEmpty()) {
            throw new ArrayStoreException("Cannot find any todos by due. Check the given due");
        }
        else return todos;
    }

    //UPDATE
    @Override
    public ToDoActivity updateToDo(ToDoActivityDTO toDoActivityDTO) {
        ToDoActivity toDoActivity = toDoActivityRepo.findById(toDoActivityDTO.getId()).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find todo task with given Id"); }
        );
        User user = userRepo.findById(toDoActivityDTO.getId()).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find user for todo Id"); }
        );

        return toDoActivityRepo.save(toDoActivity);
    }

    @Override
    public void delete(Long id) {
        ToDoActivity toDoActivity = toDoActivityRepo.findById(id).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find todo task with given Id"); }
        );
        toDoActivityRepo.delete(toDoActivity);
    }
}
