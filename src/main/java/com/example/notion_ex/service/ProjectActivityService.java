package com.example.notion_ex.service;

import com.example.notion_ex.dto.ProjectActivityDTO;
import com.example.notion_ex.model.ProjectActivity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProjectActivityService {
    //READ
    ProjectActivity findProjectByName(String name);
    ProjectActivityDTO findProjectByIdDto(Long id);
    List<ProjectActivityDTO> findAllDTO();

    //UPDATE
    ProjectActivity updateProject(ProjectActivityDTO projectActivityDTO);
    //DELETE
    void deleteProjectActivityById(Long id);
}
