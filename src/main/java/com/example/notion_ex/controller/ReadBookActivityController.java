package com.example.notion_ex.controller;

import com.example.notion_ex.dto.ReadActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ReadActivity;
import com.example.notion_ex.model.User;
import com.example.notion_ex.repository.ReadActivityRepo;
import com.example.notion_ex.service.ReadActivityService;
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
@RequestMapping("api/read")
@RequiredArgsConstructor
public class ReadBookActivityController {
    private final ReadActivityService readActivityService;
    private final ReadActivityRepo readActivityRepo;

    @PutMapping("")
    public ResponseEntity createRead(@AuthenticationPrincipal User user, @RequestBody ReadActivity readActivity){
//        ReadActivity readActivity = readActivityService.saveRead(user);

        return ResponseEntity.ok(readActivityService.saveRead(user, readActivity));
    }

    @GetMapping("")
    public ResponseEntity getReadActivities(@AuthenticationPrincipal User user) {
        Set<ReadActivity> readActivities = readActivityRepo.findByUserId(user.getId());
        return ResponseEntity.ok(readActivities);
    }

    @GetMapping("/sort/{param}")
    public ResponseEntity getReadBookActivitiesSorted(@AuthenticationPrincipal User user, @PathVariable String param) {
        //Set<FinancialActivity> financialActivities = financialActivityRepo.findByUserId(user.getId());
        List<ReadActivity> readActivities = readActivityService.sortByParam(param);
        return ResponseEntity.ok(readActivities);
    }

    @GetMapping("/search/{param}")
    public ResponseEntity getReadBookActivitiesSearch(@AuthenticationPrincipal User user, @PathVariable String param) {
        boolean val = false;
        if(readActivityService.isNumeric(param)) {
            List<ReadActivity> readActivities = readActivityService.findByScoreService(Integer.parseInt(param));
            if(!readActivities.isEmpty()){
                return ResponseEntity.ok(readActivities);
            }
        }

        List<ReadActivity> readActivities = readActivityService.findByNameService(param);
        if(readActivities.isEmpty()) {
            List<ReadActivity> readActivities1 = readActivityService.findByAuthorService(param);
            if(readActivities1.isEmpty()) {
                List<ReadActivity> readActivities2 = readActivityService.findByTypeService(param);
                if (readActivities2.isEmpty()) {
                    List<ReadActivity> readActivities3 = readActivityService.findByStatusService(param);
                    return ResponseEntity.ok(readActivities3);
                } else return ResponseEntity.ok(readActivities2);
            }
            else return ResponseEntity.ok(readActivities1);
        }
        else val = true;

        return (val? ResponseEntity.ok(readActivities) : ResponseEntity.ok(null));
    }
    //code that ill use later
    @GetMapping("/allDTO")
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
