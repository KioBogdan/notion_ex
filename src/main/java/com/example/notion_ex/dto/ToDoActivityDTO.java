package com.example.notion_ex.dto;

import com.example.notion_ex.model.People;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToDoActivityDTO {
    private Long id;

    private String taskName; // name of the task
    private LocalDate due; //date of the expense
}
