package com.example.notion_ex.controller;

import com.example.notion_ex.dto.ProjectActivityDTO;
import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.service.ProjectActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectActivityController {
    @Autowired
    private ProjectActivityService projectActivityService;

    @GetMapping()
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
