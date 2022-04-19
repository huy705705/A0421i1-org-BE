package com.example.demo.repository;

import com.example.demo.model.Cage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CageRepo extends JpaRepository<Cage, String> {
    @Query(value = "select cage_id from cage",nativeQuery = true)
    List<String> getListCageId();
}