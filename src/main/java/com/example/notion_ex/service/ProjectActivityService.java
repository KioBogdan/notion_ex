package com.example.notion_ex.service;

import com.example.notion_ex.dto.ProjectActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public interface ProjectActivityService {
    //READ
    ProjectActivity findProjectByName(String name);
    ProjectActivityDTO findProjectByIdDto(Long id);
    List<ProjectActivityDTO> findAllDTO();
    ProjectActivity saveProject(User user, ProjectActivity projectActivity);
    Set<ProjectActivity> findByUser(User user);
    List<ProjectActivity> findByTaskService(String t);
    List<ProjectActivity> findByProjectNameService(String t);
    List<ProjectActivity> findByStatusService(String t);
    List<ProjectActivity> findByDateStartService(LocalDate d);
    List<ProjectActivity> findByDateFinishService(LocalDate d);
    List<ProjectActivity> sortByParam(String param);

    //UPDATE
    ProjectActivity updateProject(ProjectActivityDTO projectActivityDTO);
    //DELETE
    void deleteProjectActivityById(Long id);
}
