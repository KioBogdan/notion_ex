package com.example.notion_ex.mapper;

import com.example.notion_ex.dto.ProjectActivityDTO;
import com.example.notion_ex.dto.ReadActivityDTO;
import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.model.ReadActivity;
import org.springframework.stereotype.Component;

@Component
public class ReadBookActivityMapper {
    public static ReadActivityDTO mapModelToDto(ReadActivity readActivity) {
        ReadActivityDTO readActivityDTO = new ReadActivityDTO();
        readActivityDTO.setId(readActivity.getId());
        readActivityDTO.setAuthor(readActivity.getAuthor());
        readActivityDTO.setType(readActivity.getType());
        readActivityDTO.setDateOfCompletion(readActivity.getDateOfCompletion());
        readActivityDTO.setName(readActivity.getName());
        readActivityDTO.setScore(readActivity.getScore());
        readActivityDTO.setStatus(readActivity.getStatus());

        return readActivityDTO;
    }

    public ReadActivity mapDtoToModel (ReadActivityDTO readActivityDTO) {
        ReadActivity readActivity = new ReadActivity();
        readActivity.setId(readActivityDTO.getId());
        readActivity.setAuthor(readActivityDTO.getAuthor());
        readActivity.setType(readActivityDTO.getType());
        readActivity.setDateOfCompletion(readActivityDTO.getDateOfCompletion());
        readActivity.setName(readActivityDTO.getName());
        readActivity.setScore(readActivityDTO.getScore());
        readActivity.setStatus(readActivityDTO.getStatus());

        return readActivity;
    }
}
