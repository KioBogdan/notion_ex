package com.example.notion_ex.service.impl;

import com.example.notion_ex.dto.ReadActivityDTO;
import com.example.notion_ex.mapper.ReadBookActivityMapper;
import com.example.notion_ex.model.Activity;
import com.example.notion_ex.model.ProjectActivity;
import com.example.notion_ex.model.ReadActivity;
import com.example.notion_ex.model.User;
import com.example.notion_ex.repository.ReadActivityRepo;
import com.example.notion_ex.repository.UserRepo;
import com.example.notion_ex.service.ReadActivityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadActivityImpl implements ReadActivityService {
    private final ReadActivityRepo readActivityRepo;

    @Autowired
    private final UserRepo userRepo;
    private final ReadBookActivityMapper readBookActivityMapper;

    public ReadActivityImpl(ReadActivityRepo readActRepo, UserRepo userRepo, ReadBookActivityMapper readBookActivityMapper) {
        this.readActivityRepo = readActRepo;
        this.userRepo = userRepo;
        this.readBookActivityMapper = readBookActivityMapper;
    }

    //READ
    @Override
    public ReadActivityDTO findReadByID(Long id) {
        final ReadActivity readActivity = readActivityRepo.findById(id).orElseThrow(
                () ->  { throw new EntityNotFoundException("Cannot find read task with given Id"); }
        );
        return readBookActivityMapper.mapModelToDto(readActivity);
    }

    @Override
    public List<ReadActivityDTO> findAllDTO() {
        return readActivityRepo.findAll().stream().
                map(ReadBookActivityMapper::mapModelToDto).
                collect(Collectors.toList());
    }

    //UPDATE

    @Override
    public ReadActivity updateRead(ReadActivityDTO readActivityDTO) {
        ReadActivity readActivity = readActivityRepo.findById(readActivityDTO.getId()).get();
        User user = userRepo.findById(readActivityDTO.getId()).get();

        return readActivityRepo.save(readActivity);
    }

    @Override
    public void delete(Long id) {
        ReadActivity readActivity = readActivityRepo.findById(id).get();
        readActivityRepo.delete(readActivity);
    }
}
