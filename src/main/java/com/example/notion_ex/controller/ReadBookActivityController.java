package com.example.notion_ex.controller;

import com.example.notion_ex.dto.ReadActivityDTO;
import com.example.notion_ex.model.ReadActivity;
import com.example.notion_ex.repository.ReadActivityRepo;
import com.example.notion_ex.service.ReadActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/read")
public class ReadBookActivityController {
    @Autowired
    private ReadActivityService readActivityService;

    @GetMapping()
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(readActivityService.findAllDTO());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity findReadById (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(readActivityService.findReadByID(id));
    }

    @PutMapping("/update")
    public ReadActivity updateRead(@RequestBody ReadActivityDTO readActivityDTO) {
        return readActivityService.updateRead(readActivityDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) { readActivityService.delete(id); }
}
