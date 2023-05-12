package com.example.notion_ex.controller;

import com.example.notion_ex.dto.FinancialActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.User;
import com.example.notion_ex.repository.FinancialActivityRepo;
import com.example.notion_ex.service.FinancialActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/financial")
@RequiredArgsConstructor
public class FinancialActivityController {
    private final FinancialActivityService financialActivityService;
    private final FinancialActivityRepo financialActivityRepo;

    @PutMapping("")
    public ResponseEntity createFinancial(@AuthenticationPrincipal User user, @RequestBody FinancialActivity financialActivity){
//        FinancialActivity financialActivity = financialActivityService.saveFinancial(user);

        return ResponseEntity.ok(financialActivityService.saveFinancial(user, financialActivity));
    }

    @GetMapping("")
    public ResponseEntity getFinancialActivities(@AuthenticationPrincipal User user) {
        Set<FinancialActivity> financialActivitySet = financialActivityRepo.findByUserId(user.getId());
        return ResponseEntity.ok(financialActivitySet);
    }

    @GetMapping("/sort/{param}")
    public ResponseEntity getFinancialActivitiesSorted(@AuthenticationPrincipal User user, @PathVariable String param) {
        //Set<FinancialActivity> financialActivities = financialActivityRepo.findByUserId(user.getId());
        List<FinancialActivity> financialActivitiesSS = financialActivityService.sortByParam(param);
        return ResponseEntity.ok(financialActivitiesSS);
    }

    @GetMapping("/search/{param}")
    public ResponseEntity getFinancialActivitiesSearch(@AuthenticationPrincipal User user, @PathVariable String param) {
        boolean val = false;
        if(financialActivityService.isNumeric(param)) {
            List<FinancialActivity> financialActivities = financialActivityService.findByAmountService(Integer.parseInt(param));
            if(!financialActivities.isEmpty()){
                return ResponseEntity.ok(financialActivities);
            }
        }

        if(financialActivityService.isDate(param)) {
            List<FinancialActivity> financialActivity3 = financialActivityService.findByDateService(LocalDate.parse(param));
            if(!financialActivity3.isEmpty())
                return ResponseEntity.ok(financialActivity3);
        }

        List<FinancialActivity> financialActivity1 = financialActivityService.findByExpenseService(param);
        if(financialActivity1.isEmpty()) {
            List<FinancialActivity> financialActivity2 = financialActivityService.findByCategoryService(param);
                return ResponseEntity.ok(financialActivity2);
        }
        else val = true;

        return (val? ResponseEntity.ok(financialActivity1) : ResponseEntity.ok(null));
    }
    //code that ill use later
    @GetMapping("/allDTO")
    public List<FinancialActivityDTO> findAllDTO() {
        return financialActivityService.findAllDTO();
    }

    @GetMapping("/all")
    public List<FinancialActivity> findAll() { return financialActivityService.findAll(); }

    @GetMapping("/{expense}")
    public ResponseEntity findByExpense(@PathVariable String expense) {
        return ResponseEntity.status(HttpStatus.OK).body(financialActivityService.findByExpenseService(expense));
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
