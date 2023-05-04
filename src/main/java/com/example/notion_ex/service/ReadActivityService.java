package com.example.notion_ex.service;

import com.example.notion_ex.dto.ReadActivityDTO;
import com.example.notion_ex.model.ReadActivity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReadActivityService {
    //READ
    ReadActivityDTO findReadByID(Long id);
    List<ReadActivityDTO> findAllDTO();

    //UPDATE
    ReadActivity updateRead(ReadActivityDTO readActivityDTO);

    //DELETE
    void delete(Long id);
    //ReadActivity getReadByType(String type);
}
