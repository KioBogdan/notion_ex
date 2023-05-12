package com.example.notion_ex.service.impl;

import com.example.notion_ex.dto.ReadActivityDTO;
import com.example.notion_ex.mapper.ReadBookActivityMapper;
import com.example.notion_ex.model.*;
import com.example.notion_ex.repository.ReadActivityRepo;
import com.example.notion_ex.repository.UserRepo;
import com.example.notion_ex.service.ReadActivityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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

    @Override
    public ReadActivity saveRead(User user, ReadActivity readActivity) {
       // ReadActivity readActivity = new ReadActivity();
        //readActivity.setAuthor("Authors good");
        readActivity.setUser(user);

        return readActivityRepo.save(readActivity);
    }

    @Override
    public List<ReadActivity> sortByParam(String param) {
        return readActivityRepo.findAll(Sort.by(Sort.Direction.ASC, param));
    }


    @Override
    public Set<ReadActivity> findByUser(User user) {
        return readActivityRepo.findByUserId(user.getId());
    }

    @Override
    public List<ReadActivity> findByNameService(String t) {
        return readActivityRepo.findByName(t);
    }

    @Override
    public List<ReadActivity> findByAuthorService(String t) {
        return readActivityRepo.findByAuthor(t);
    }

    @Override
    public List<ReadActivity> findByTypeService(String t) {
        return readActivityRepo.findByType(t);
    }

    @Override
    public List<ReadActivity> findByStatusService(String t) {
        return readActivityRepo.findByStatus(t);
    }

    @Override
    public List<ReadActivity> findByScoreService(int t) {
        return readActivityRepo.findByScore(t);
    }

    @Override
    public List<ReadActivity> findByDateOfCompletionService(String t) {
        return readActivityRepo.findByDateOfCompletion(t);
    }

    @Override
    public boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isDate(String s) {
        if (s == null) {
            return false;
        }
        try {
            LocalDate d = LocalDate.parse(s);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
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
