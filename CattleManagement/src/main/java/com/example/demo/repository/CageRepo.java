package com.example.demo.repository;

import com.example.demo.model.Cage;
import com.example.demo.model.Employee;
import com.example.demo.model.Entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CageRepo extends JpaRepository<Cage, String> {

    @Query(value = "select cage_id from cage",nativeQuery = true)
    List<String> getListCageId();

    Optional<Cage> findByCageId(String Id);

    Boolean existsByCageId(String id);

    @Query(value="SELECT * FROM cages_seq;", nativeQuery=true)
    Integer getNextId();
}