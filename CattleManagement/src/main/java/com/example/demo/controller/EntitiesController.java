package com.example.demo.controller;

import com.example.demo.model.Entities;
import com.example.demo.repository.EntitiesIllnessRepo;
import com.example.demo.repository.EntitiesRepo;
import com.example.demo.service.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entities")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class EntitiesController {
    @Autowired
    private EntitiesService entitiesService;
    @GetMapping()
    public ResponseEntity<Page<Entities>> findAllEntities(@PageableDefault(size = 2) Pageable pageable){
        Page<Entities> entities= entitiesService.findAll(pageable);
        if (entities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entities,HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Entities> findEntitiesById(@PathVariable String id) {
        Optional<Entities> entitiesOptional = entitiesService.findById(id);
        if (!entitiesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(entitiesOptional.get(), HttpStatus.OK);
    }


    @PatchMapping("/delete/{id}")
    public ResponseEntity<Entities> deleteEntities(@PathVariable String id) {
        Optional<Entities> entitiesOptional = entitiesService.findById(id);
        if (!entitiesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        entitiesOptional.get().setIsDelete(false);
        return new ResponseEntity<>(entitiesService.save(entitiesOptional.get()), HttpStatus.NO_CONTENT);
    }

}
