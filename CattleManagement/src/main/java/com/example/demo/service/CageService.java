package com.example.demo.service;


import com.example.demo.model.Cage;
import com.example.demo.model.dto.CageListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CageService {
    List<String> getListCageId();
    Page<CageListDTO> findAllCage(Pageable pageable);

}
