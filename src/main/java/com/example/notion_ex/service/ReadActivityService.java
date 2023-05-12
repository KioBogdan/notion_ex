package com.example.notion_ex.service;

import com.example.notion_ex.dto.ReadActivityDTO;
import com.example.notion_ex.model.FinancialActivity;
import com.example.notion_ex.model.ReadActivity;
import com.example.notion_ex.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface ReadActivityService {
    //READ
    ReadActivityDTO findReadByID(Long id);
    List<ReadActivityDTO> findAllDTO();
    ReadActivity saveRead(User user, ReadActivity readActivity);
    Set<ReadActivity> findByUser(User user);
    List<ReadActivity> findByNameService(String t);
    List<ReadActivity> findByAuthorService(String t);
    List<ReadActivity> findByTypeService(String t);
    List<ReadActivity> findByStatusService(String t);
    List<ReadActivity> findByScoreService(int t);
    List<ReadActivity> findByDateOfCompletionService(String t);
    boolean isNumeric(String s);
    boolean isDate(String s);
    List<ReadActivity> sortByParam(String param);

    //UPDATE
    ReadActivity updateRead(ReadActivityDTO readActivityDTO);

    //DELETE
    void delete(Long id);
    //ReadActivity getReadByType(String type);
}
