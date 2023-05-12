package com.example.notion_ex.controller;

import com.example.notion_ex.dto.ProjectActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.model.User;
import com.example.notion_ex.repository.ProjectActivityRepo;
import com.example.notion_ex.service.ProjectActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/project")
@RequiredArgsConstructor
public class ProjectActivityController {

    private final ProjectActivityService projectActivityService;
    private final ProjectActivityRepo projectActivityRepo;


    @PutMapping("")
    public ResponseEntity createProject(@AuthenticationPrincipal User user, @RequestBody ProjectActivity projectActivity){
        //projectActivity = projectActivityService.saveProject(user, projectActivity);

        return ResponseEntity.ok(projectActivityService.saveProject(user, projectActivity));
    }

    @GetMapping("")
    public ResponseEntity getProjectActivities(@AuthenticationPrincipal User user) {
        Set<ProjectActivity> projectActivitySet = projectActivityRepo.findByUserId(user.getId());
        return ResponseEntity.ok(projectActivitySet);
    }

    @GetMapping("/sort/{param}")
    public ResponseEntity getProjectActivitiesSorted(@AuthenticationPrincipal User user, @PathVariable String param) {
        //Set<FinancialActivity> financialActivities = financialActivityRepo.findByUserId(user.getId());
        List<ProjectActivity> projectActivities = projectActivityService.sortByParam(param);
        return ResponseEntity.ok(projectActivities);
    }

    @GetMapping("/search/{param}")
    public ResponseEntity getProjectActivitiesSearch(@AuthenticationPrincipal User user, @PathVariable String param) {
        boolean val = false;
        List<ProjectActivity> projectActivities = projectActivityService.findByTaskService(param);
        if(projectActivities.isEmpty()) {
            List<ProjectActivity> projectActivities1 = projectActivityService.findByProjectNameService(param);
            if(projectActivities1.isEmpty()) {
                List<ProjectActivity> projectActivities2 = projectActivityService.findByStatusService(param);
                if(projectActivities2.isEmpty()) {
                    List<ProjectActivity> projectActivities3 = projectActivityService.findByDateFinishService(LocalDate.parse(param));
                    if(projectActivities3.isEmpty()) {
                        List<ProjectActivity> projectActivities4 = projectActivityService.findByDateFinishService(LocalDate.parse(param));
                        return ResponseEntity.ok(projectActivities4);
                    }
                    else return ResponseEntity.ok(projectActivities3);
                }
                return ResponseEntity.ok(projectActivities2);
            }
            return ResponseEntity.ok(projectActivities1);
        }
        else val=true;

        return (val? ResponseEntity.ok(projectActivities) : ResponseEntity.ok(null));
    }
    //code that ill use later
    @GetMapping("/findDTO")
    public List<ProjectActivityDTO> findAllDTO() { return projectActivityService.findAllDTO(); }

    @GetMapping("/find/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(projectActivityService.findProjectByIdDto(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity findProjByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(projectActivityService.findProjectByName(name));
    }

    @PutMapping("/update")
    public ProjectActivity updateProject(@RequestBody ProjectActivityDTO projectActivityDTO){
        return projectActivityService.updateProject(projectActivityDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProjectById(@PathVariable Long id) {
        projectActivityService.deleteProjectActivityById(id);
    }
    //define others for the other methods
}
