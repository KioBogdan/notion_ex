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
        Set<ReadActivity> reads = readActivityRepo.findByUserId(user.getId());
        if(reads.isEmpty()) {
            throw new ArrayStoreException("Cannot find any reads by user. Check the given user");
        }
        else return reads;
    }

    @Override
    public List<ReadActivity> findByNameService(String t) {
        List<ReadActivity> reads = readActivityRepo.findByName(t);
        if(reads.isEmpty()) {
            throw new ArrayStoreException("Cannot find any reads by name. Check the given name");
        }
        else return reads;
    }

    @Override
    public List<ReadActivity> findByAuthorService(String t) {
        List<ReadActivity> reads = readActivityRepo.findByAuthor(t);
        if(reads.isEmpty()) {
            throw new ArrayStoreException("Cannot find any reads by author. Check the given name");
        }
        else return reads;
    }

    @Override
    public List<ReadActivity> findByTypeService(String t) {
        List<ReadActivity> reads = readActivityRepo.findByType(t);
        if(reads.isEmpty()) {
            throw new ArrayStoreException("Cannot find any reads by type. Check the given type");
        }
        else return reads;
    }

    @Override
    public List<ReadActivity> findByStatusService(String t) {
        List<ReadActivity> reads = readActivityRepo.findByStatus(t);
        if(reads.isEmpty()) {
            throw new ArrayStoreException("Cannot find any reads by status. Check the given status");
        }
        else return reads;
    }

    @Override
    public List<ReadActivity> findByScoreService(int t) {
        List<ReadActivity> reads = readActivityRepo.findByScore(t);
        if(reads.isEmpty()) {
            throw new ArrayStoreException("Cannot find any reads by score. Check the given score");
        }
        else return reads;
    }

    @Override
    public List<ReadActivity> findByDateOfCompletionService(String t) {
        List<ReadActivity> reads = readActivityRepo.findByDateOfCompletion(t);
        if(reads.isEmpty()) {
            throw new ArrayStoreException("Cannot find any reads by date. Check the given date");
        }
        else return reads;
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
        ReadActivity readActivity = readActivityRepo.findById(readActivityDTO.getId()).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find read task with given Id"); }
        );
        User user = userRepo.findById(readActivityDTO.getId()).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find user for read activity Id");}
        );

        return readActivityRepo.save(readActivity);
    }

    @Override
    public void delete(Long id) {
        ReadActivity readActivity = readActivityRepo.findById(id).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find read task with given Id"); }
        );
        readActivityRepo.delete(readActivity);
    }
}
