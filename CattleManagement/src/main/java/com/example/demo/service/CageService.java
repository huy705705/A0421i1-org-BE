package com.example.demo.service;


import com.example.demo.model.Cage;
import com.example.demo.model.Employee;
import com.example.demo.model.dto.CageForEditDto;
import com.example.demo.model.dto.EmployeeForCageDto;

import java.util.List;
import java.util.Optional;

public interface CageService {

    List<String> getListCageId();

    Cage save (Cage cage);

    Optional<Cage> findCageById(String id);

    Optional<CageForEditDto> findCageById2(String id);

    Boolean checkEmployee(String id);

    Boolean existsByCageId(String id);


    Integer getNextId();

    EmployeeForCageDto getEmployeeIdAndName(String accountName);

}
