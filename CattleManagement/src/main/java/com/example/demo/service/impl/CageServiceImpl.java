package com.example.demo.service.impl;

import com.example.demo.customUtilities.CageComparator;
import com.example.demo.model.Cage;
import com.example.demo.model.Employee;
import com.example.demo.model.Entities;
import com.example.demo.model.dto.EmployeeForCageDto;
import com.example.demo.repository.CageRepo;
import com.example.demo.service.CageService;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CageServiceImpl implements CageService {
    @Autowired
    private CageRepo cageRepo;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<String> getListCageId() {
        return cageRepo.getListCageId();
    }

    @Override
    public Cage save(Cage cage) {
        return cageRepo.save(cage);
    }

    @Override
    public Optional<Cage> findCageById(String id) {
        return cageRepo.findByCageId(id);
    }

    @Override
    public Boolean checkEmployee(String id) {
        return employeeService.existsByEmployeeId(id);
    }

    @Override
    public Boolean existsByCageId(String id) {
        return cageRepo.existsByCageId(id);
    }

    @Override
    public Cage update(Cage cage, String id) {
        return null;
    }

    @Override
    public Integer getNextId() {
        return cageRepo.getNextId();
    }

    public Boolean isValidDate(Cage cage){
        return  (cage.getCreatedDate().isEqual(LocalDate.now()) || cage.getCreatedDate().isAfter(LocalDate.now()))
                && cage.getClosedDate().isAfter(cage.getCreatedDate());
    }

    public void autoUpdateClosedDate(Cage cage){

        Set<Entities> entitiesSet = cage.getEntities();

        LocalDate greatestOutDate = (Collections.max(entitiesSet, new CageComparator())).getOutDate();

        cage.setClosedDate(greatestOutDate);

        cageRepo.save(cage);
    }


}

