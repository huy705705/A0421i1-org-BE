package com.example.demo.controller;

import com.example.demo.model.Entities;
import com.example.demo.service.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping("/entities")
public class EntitiesController {
    @Autowired
    private EntitiesService entitiesService;

    @PostMapping("/create")
    public ResponseEntity<Entities> createEntities(@RequestBody Entities entities) throws Exception {
        Entities newEntities = entitiesService.save(entities);
        if (newEntities == null) {
            throw new Exception();
        } else {
            return new ResponseEntity<>(entitiesService.save(entities), HttpStatus.CREATED);
        }
    }
}
