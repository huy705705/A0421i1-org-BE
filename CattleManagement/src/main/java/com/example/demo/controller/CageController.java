package com.example.demo.controller;

import com.example.demo.model.dto.CageListDTO;
import com.example.demo.service.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/public/cage")
public class CageController {
    @Autowired
    CageService cageService;
    @GetMapping("")
    public ResponseEntity<?> findAllCage(@PageableDefault(size = 10) Pageable pageable){
        Page<CageListDTO> cageListDTOPage=cageService.findAllCage(pageable);
        if(cageListDTOPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(cageListDTOPage, HttpStatus.OK);
        }
    }
}
