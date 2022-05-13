package com.example.demo.controller;

import com.example.demo.model.Cage;
import com.example.demo.model.dto.CageListDTO;
import com.example.demo.service.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("/api/public/cage")
public class CageController {
    @Autowired
    CageService cageService;
    @GetMapping("")
    public ResponseEntity<?> findAllCage(@PageableDefault(size = 2) Pageable pageable){
        Page<CageListDTO> cageListDTOPage=cageService.findAllCage(pageable);
        if(cageListDTOPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(cageListDTOPage, HttpStatus.OK);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> findCage(@PageableDefault(size = 2) Pageable pageable,
                                      @RequestParam(value = "dateType", defaultValue = "") String dateType,
                                      @RequestParam(value = "dateFrom",defaultValue = "") String dateFrom,
                                      @RequestParam(value = "dateTo",defaultValue = "") String dateTo,
                                      @RequestParam(value = "searchCageId",defaultValue = "") String searchCageId){
        Page<CageListDTO> cageListDTOPage;
        if(dateFrom.isEmpty()){
            dateFrom="2000-01-01";
        }
        if(dateTo.isEmpty()){
            dateTo="2200-12-30";
        }
        if(searchCageId.isEmpty()){
            searchCageId="%";
        }
        System.out.println(dateFrom);
        System.out.println(dateTo);
        System.out.println(searchCageId);
        System.out.println(dateType);
        if(dateType.equals("createdDate")){
            cageListDTOPage=cageService.findCageByCreatedDate(searchCageId,dateFrom,dateTo,pageable);
            System.out.println(cageListDTOPage);
        }
        else {
            cageListDTOPage=cageService.findCageByCloseDate(searchCageId,dateFrom,dateTo,pageable);
        }
        if(cageListDTOPage.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(cageListDTOPage, HttpStatus.OK);
        }
    }
}

