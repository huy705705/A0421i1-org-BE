package com.example.demo.controller;

import com.example.demo.model.Entities;
import com.example.demo.repository.EntitiesIllnessRepo;
import com.example.demo.repository.EntitiesRepo;
import com.example.demo.service.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entities")
public class EntitiesController {
    @Autowired
    private EntitiesService entitiesService;
    @GetMapping
    public ResponseEntity<List<Entities>> findAllEntities(){
        List<Entities> entities=(List<Entities>) entitiesService.findAll();
        if (entities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entities,HttpStatus.OK);
    }
}
