package com.example.notion_ex.service.impl;

import com.example.notion_ex.dto.ProjectActivityDTO;
import com.example.notion_ex.mapper.FinancialActivityMapper;
import com.example.notion_ex.mapper.ProjectActivityMapper;
import com.example.notion_ex.model.*;
import com.example.notion_ex.repository.PeopleRepo;
import com.example.notion_ex.repository.ProjectActivityRepo;
import com.example.notion_ex.repository.UserRepo;
import com.example.notion_ex.service.ProjectActivityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectActivityImpl implements ProjectActivityService {
    private final ProjectActivityRepo projectActivityRepo;
    private final UserRepo userRepo;
    private final ProjectActivityMapper projectActivityMapper;

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

    @Override
    public ProjectActivity saveProject(User user, ProjectActivity projectActivity) {
        //ProjectActivity projectActivity = new ProjectActivity();
        //projectActivity.setProjectName("This project lit");
        projectActivity.setUser(user);

        return projectActivityRepo.save(projectActivity);
    }

    @Override
    public List<ProjectActivity> sortByParam(String param) {
        return projectActivityRepo.findAll(Sort.by(Sort.Direction.ASC, param));
    }

    @Override
    public Set<ProjectActivity> findByUser(User user) {
        Set<ProjectActivity> projects = projectActivityRepo.findByUserId(user.getId());
        if(projects.isEmpty()) {
            throw new ArrayStoreException("Cannot find any projects by user. Check the given user");
        }
        else return projects;
    }

    @Override
    public List<ProjectActivity> findByTaskService(String t) {
        List<ProjectActivity> projects = projectActivityRepo.findByTask(t);
        if(projects.isEmpty()) {
            throw new ArrayStoreException("Cannot find any projects by task. Check the given task");
        }
        else return projects;
    }

    @Override
    public List<ProjectActivity> findByProjectNameService(String t) {
        List<ProjectActivity> projects = projectActivityRepo.findByProjectName(t);
        if(projects.isEmpty()) {
            throw new ArrayStoreException("Cannot find any projects by project name. Check the given project name");
        }
        else return projects;
    }

    @Override
    public List<ProjectActivity> findByStatusService(String t) {
        List<ProjectActivity> projects = projectActivityRepo.findByStatus(t);
        if(projects.isEmpty()) {
            throw new ArrayStoreException("Cannot find any projects by status. Check the given status");
        }
        else return projects;
    }

    @Override
    public List<ProjectActivity> findByDateStartService(LocalDate d) {
        List<ProjectActivity> projects = projectActivityRepo.findByDateStart(d);
        if(projects.isEmpty()) {
            throw new ArrayStoreException("Cannot find any projects by starting date. Check the given starting date");
        }
        else return projects;
    }

    @Override
    public List<ProjectActivity> findByDateFinishService(LocalDate d) {
        List<ProjectActivity> projects = projectActivityRepo.findByDateFinish(d);
        if(projects.isEmpty()) {
            throw new ArrayStoreException("Cannot find any projects by ending date. Check the given ending date");
        }
        else return projects;
    }

    //UPDATE
    @Override
    public ProjectActivity updateProject(ProjectActivityDTO projectActivityDTO) {
        ProjectActivity projectActivity = projectActivityRepo.findById(projectActivityDTO.getId()).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find project task with given Id"); }
        );
        User user = userRepo.findById(projectActivityDTO.getId()).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find user for project Id"); }
        );
        //People peopleAttr = (People) peopleRepo.findAllById(Collections.singleton(projectActivityDTO.getId()));
        //user.getProjectActivityList().add(projectActivity);

        return projectActivityRepo.save(projectActivity);
    }

    //DELETE
    @Override
    public void deleteProjectActivityById(Long id) {
        ProjectActivity projectActivity = projectActivityRepo.findById(id).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find project task with given Id"); }
        );
        projectActivityRepo.delete(projectActivity);
    }
}
