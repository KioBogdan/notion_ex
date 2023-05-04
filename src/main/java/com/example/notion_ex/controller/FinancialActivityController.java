package com.example.notion_ex.controller;

import com.example.notion_ex.dto.FinancialActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.service.FinancialActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financial")
public class
FinancialActivityController {
    @Autowired
    private FinancialActivityService financialActivityService;

    @GetMapping()
    public List<FinancialActivityDTO> findAllDTO() {
        return financialActivityService.findAllDTO();
    }

    @GetMapping("/all")
    public List<FinancialActivity> findAll() { return financialActivityService.findAll(); }


    @GetMapping("/{expense}")
    public ResponseEntity findByExpense(@PathVariable String expense) {
        return ResponseEntity.status(HttpStatus.OK).body(financialActivityService.findByExpense(expense));
    }

    @GetMapping("find/{id}")
    public ResponseEntity findByID(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(financialActivityService.findFinancialActivityByID(id));
    }

    @GetMapping("/findDTO/{id}")
    public ResponseEntity findByIDDTO(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(financialActivityService.findFinancialByIDDTO(id));
    }

    @PutMapping("/update")
    public FinancialActivity updateFinancial(@RequestBody FinancialActivityDTO fDto) {
        return financialActivityService.updateFinancial(fDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFinancialById(@PathVariable Long id) {
        financialActivityService.deleteFinancial(id);
    }
}
