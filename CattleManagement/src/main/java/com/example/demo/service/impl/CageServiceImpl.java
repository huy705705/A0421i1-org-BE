package com.example.demo.service.impl;

import com.example.demo.model.Cage;
import com.example.demo.model.dto.CageListDTO;
import com.example.demo.repository.CageRepo;
import com.example.demo.service.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CageServiceImpl implements CageService {
    @Autowired
    private CageRepo cageRepo;

    @Override
    public List<String> getListCageId() {
        return cageRepo.getListCageId();
    }

    @Override
    public Page<CageListDTO> findAllCage(Pageable pageable) {
        return cageRepo.findAllCage(pageable);
    }

    @Override
    public Page<CageListDTO> findCageByCreatedDate( String searchCageId,String dateFrom, String dateTo,Pageable pageable) {
        return cageRepo.findCageByCreatedDate(searchCageId,dateFrom,dateTo,pageable);
    }

    @Override
    public Page<CageListDTO> findCageByCloseDate(String searchCageId, String dateFrom, String dateTo, Pageable pageable) {
        return cageRepo.findCageByClosedDate(searchCageId,dateFrom,dateTo,pageable);
    }


}
