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
import java.util.Optional;

@Repository
public interface EntitiesRepo extends JpaRepository<Entities, String> {
    @Query(value = "SELECT * FROM entities  where is_delete!=1 or is_delete=null", nativeQuery = true)
    Page<Entities> findAll(Pageable pageable);

    Optional<Entities> findByEntitiesId(String id);

    @Query(value = "select current_index from auto_id where group_code=?1", nativeQuery = true)
    Integer getEntitiesId(String cageId);


    @Modifying
    @Transactional
    @Query(value = "update auto_id set current_index=current_index+1 where group_code=?1", nativeQuery = true)
    void updateAutoRender(String cageId);


    @Modifying
    @Transactional
    @Query(value = "  update entities  set is_delete = 1 where entities_id = ? ", nativeQuery = true)
    void deleteEntities(String id);

    Page<Entities> findAllByInDateAndCage(Pageable pageable, String inDate, String cage);

    @Query(value = "SELECT * FROM entities  where   :inDate < in_date  and in_date < :outDate   and  cage_id LIKE :cage", nativeQuery = true)
    Page<Entities> findAllByInDateAndCage22(@Param("page") Pageable pageable, @Param("inDate") String inDate,
                                            @Param("outDate") String outDate, @Param("cage") String cage);

}