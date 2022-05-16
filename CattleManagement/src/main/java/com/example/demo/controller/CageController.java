package com.example.demo.controller;

import com.example.demo.model.Cage;
import com.example.demo.model.dto.CageListDTO;
import com.example.demo.model.dto.GetEmployeeNameDTO;
import com.example.demo.service.CageService;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("/api/public/cage")
public class CageController {
    @Autowired
    CageService cageService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/get_employee")
    public ResponseEntity<List<GetEmployeeNameDTO>> getAllEmployeeName(){
        List<GetEmployeeNameDTO> employeeList=employeeService.getAllEmployeeName();
        if(employeeList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(employeeList,HttpStatus.OK);
        }
    }
    @GetMapping("")
    public ResponseEntity<?> findAllCage(@PageableDefault(size = 2) Pageable pageable,
                                         @RequestParam(value = "sort",defaultValue = "") String sort,
                                         @RequestParam(value = "type",defaultValue = "") boolean type){
        Page<CageListDTO> cageListDTOPage=cageService.findAllCage(pageable);
        if(cageListDTOPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            if(type){
                cageListDTOPage=cageService.findAllCage(PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),Sort.by(sort).ascending()));

            }
            else {
                cageListDTOPage=cageService.findAllCage(PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),Sort.by(sort).descending()));

            }
                return new ResponseEntity<>(cageListDTOPage,HttpStatus.OK);
            }
    }
    @GetMapping("/search")
    public ResponseEntity<?> findCage(@PageableDefault(size = 2) Pageable pageable,
                                      @RequestParam(value = "dateType", defaultValue = "") String dateType,
                                      @RequestParam(value = "dateFrom",defaultValue = "") String dateFrom,
                                      @RequestParam(value = "dateTo",defaultValue = "") String dateTo,
                                      @RequestParam(value = "employee",defaultValue = "") String employeeId,
                                      @RequestParam(value = "sort",defaultValue = "") String sort,
                                      @RequestParam(value = "type",defaultValue = "") boolean type,
                                      @RequestParam(value = "searchCageId",defaultValue = "") String searchCageId){
        Page<CageListDTO> cageListDTOPage;
        if(employeeId.isEmpty()){
            employeeId="%";
        }
        if(dateFrom.isEmpty()){
            dateFrom="2000-01-01";
        }
        if(dateTo.isEmpty()){
            dateTo="2200-12-30";
        }
        if(searchCageId.isEmpty()){
            searchCageId="%";
        }
        if(dateType.equals("createdDate")){
            if(type){
                cageListDTOPage=cageService.findCageByCreatedDate(searchCageId,employeeId,dateFrom,dateTo,PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),Sort.by(sort).ascending()));
            }
            else {
                cageListDTOPage=cageService.findCageByCreatedDate(searchCageId,employeeId,dateFrom,dateTo,PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),Sort.by(sort).descending()));
            }

        }
        else {
            if(type){
                cageListDTOPage=cageService.findCageByCloseDate(searchCageId,employeeId,dateFrom,dateTo,PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),Sort.by(sort).ascending()));
            }
            else {
                cageListDTOPage=cageService.findCageByCloseDate(searchCageId,employeeId,dateFrom,dateTo,PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),Sort.by(sort).descending()));
            }
        }
        if(cageListDTOPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(cageListDTOPage, HttpStatus.OK);
        }
    }
}
