package com.example.notion_ex.service.impl;

import com.example.notion_ex.dto.ProjectActivityDTO;
import com.example.notion_ex.mapper.FinancialActivityMapper;
import com.example.notion_ex.mapper.ProjectActivityMapper;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.People;
import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.model.User;
import com.example.notion_ex.repository.PeopleRepo;
import com.example.notion_ex.repository.ProjectActivityRepo;
import com.example.notion_ex.repository.UserRepo;
import com.example.notion_ex.service.ProjectActivityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectActivityImpl implements ProjectActivityService {
    private final ProjectActivityRepo projectActivityRepo;

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final PeopleRepo peopleRepo;

    private final ProjectActivityMapper projectActivityMapper;

    public ProjectActivityImpl(ProjectActivityRepo prActRepo, UserRepo userRepo, ProjectActivityMapper projectActivityMapper, PeopleRepo peopleRepo) {
        this.projectActivityRepo = prActRepo;
        this.userRepo = userRepo;
        this.projectActivityMapper = projectActivityMapper;
        this.peopleRepo = peopleRepo;
    }

    //READ
    @Override
    public ProjectActivity findProjectByName(String name){
        return projectActivityRepo.findProjectActivityByProjectName(name);
    }

    @Override
    public ProjectActivityDTO findProjectByIdDto(Long id) {
        final ProjectActivity projectActivity = projectActivityRepo.findById(id).orElseThrow(
                () ->  { throw new EntityNotFoundException("Cannot find project task with given Id"); }
        );
        return projectActivityMapper.mapModelToDto(projectActivity);
    }

    @Override
    public List<ProjectActivityDTO> findAllDTO() {
        return projectActivityRepo.findAll().stream()
                .map(ProjectActivityMapper::mapModelToDto)
                .collect(Collectors.toList());
    }

    //UPDATE
    @Override
    public ProjectActivity updateProject(ProjectActivityDTO projectActivityDTO) {
        ProjectActivity projectActivity = projectActivityRepo.findById(projectActivityDTO.getId()).get();
        User user = userRepo.findById(projectActivityDTO.getId()).get();
        //People peopleAttr = (People) peopleRepo.findAllById(Collections.singleton(projectActivityDTO.getId()));
        //user.getProjectActivityList().add(projectActivity);

        return projectActivityRepo.save(projectActivity);
    }

    //DELETE
    @Override
    public void deleteProjectActivityById(Long id) {
        ProjectActivity projectActivity = projectActivityRepo.findById(id).get();
        projectActivityRepo.delete(projectActivity);
    }
}
