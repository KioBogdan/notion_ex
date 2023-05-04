package com.example.notion_ex.mapper;

import com.example.notion_ex.dto.FinancialActivityDTO;
import com.example.notion_ex.dto.ProjectActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ProjectActivity;
import org.springframework.stereotype.Component;

@Component
public class ProjectActivityMapper {
    public static ProjectActivityDTO mapModelToDto(ProjectActivity projectActivity) {
        ProjectActivityDTO projectActivityDTO = new ProjectActivityDTO();
        projectActivityDTO.setId(projectActivity.getId());
        projectActivityDTO.setTask(projectActivity.getTask());
        projectActivityDTO.setDateStart(projectActivity.getDateStart());
        projectActivityDTO.setDateFinish(projectActivity.getDateFinish());
        projectActivityDTO.setProjectName(projectActivity.getProjectName());

        return projectActivityDTO;
    }

    public ProjectActivity mapDtoToModel (ProjectActivityDTO projectActivityDTO) {
        ProjectActivity projectActivity = new ProjectActivity();
        projectActivity.setId(projectActivityDTO.getId());
        projectActivity.setTask(projectActivityDTO.getTask());
        projectActivity.setDateStart(projectActivityDTO.getDateStart());
        projectActivity.setDateFinish(projectActivityDTO.getDateFinish());
        projectActivity.setProjectName(projectActivityDTO.getProjectName());

        return projectActivity;
    }
}
