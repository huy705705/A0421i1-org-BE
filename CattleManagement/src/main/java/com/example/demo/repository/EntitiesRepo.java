package com.example.demo.repository;


import com.example.demo.model.Entities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntitiesRepo extends JpaRepository<Entities, String> {
    @Query(value = "SELECT * FROM entities  where is_delete=0",nativeQuery = true)
    Page<Entities> findAll(Pageable pageable);

    Optional<Entities> findByEntitiesId(String id);
    @Modifying
    @Transactional
    @Query(value = "  update entities  set is_delete = 1 where entities_id = ? ",nativeQuery = true )
    void deleteEntities(String id);

    Page<Entities> findAllByInDateAndCage(Pageable pageable,String inDate,String cage);

    @Query(value = "SELECT * FROM entities  where in_date like :inDate and  cage_id LIKE :cage",nativeQuery = true)
    Page<Entities> findAllByInDateAndCage22(@Param("page") Pageable pageable, @Param("inDate") String inDate,@Param("cage") String cage);
}