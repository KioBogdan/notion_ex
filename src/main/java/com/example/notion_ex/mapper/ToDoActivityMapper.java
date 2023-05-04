package com.example.notion_ex.mapper;

import com.example.notion_ex.dto.ProjectActivityDTO;
import com.example.notion_ex.dto.ToDoActivityDTO;
import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.model.ToDoActivity;
import org.springframework.stereotype.Component;

@Component
public class ToDoActivityMapper {
    public static ToDoActivityDTO mapModelToDto(ToDoActivity toDoActivity) {
        ToDoActivityDTO toDoActivityDTO = new ToDoActivityDTO();
        toDoActivityDTO.setId(toDoActivity.getId());
        toDoActivityDTO.setDue(toDoActivity.getDue());
        toDoActivityDTO.setTaskName(toDoActivity.getTaskName());

        return toDoActivityDTO;
    }

    public ToDoActivity mapDtoToModel (ToDoActivityDTO toDoActivityDTO) {
        ToDoActivity toDoActivity = new ToDoActivity();
        toDoActivity.setId(toDoActivityDTO.getId());
        toDoActivity.setDue(toDoActivityDTO.getDue());
        toDoActivity.setTaskName(toDoActivityDTO.getTaskName());

        return toDoActivity;
    }
}
