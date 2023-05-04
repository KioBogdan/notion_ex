package com.example.notion_ex.dto;

import jakarta.annotation.sql.DataSourceDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectActivityDTO {
    private Long id;

    private String task; // name of task
    private String status; //status of completion for the present task
    private LocalDate dateStart; //starting date of the project
    private LocalDate dateFinish; //ending date of the project
    private String projectName; //name of the project
}
