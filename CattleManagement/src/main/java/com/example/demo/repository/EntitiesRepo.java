package com.example.demo.repository;


import com.example.demo.model.Entities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface EntitiesRepo extends JpaRepository<Entities, String> {

    @Query(value="select current_index from auto_id where group_code=?1",nativeQuery = true)
    Integer getEntitiesId(String cageId);


    @Modifying
    @Transactional   
    @Query(value="update auto_id set current_index=current_index+1 where group_code=?1",nativeQuery = true)
    void updateAutoRender(String cageId);
    @Override
    Page<Entities> findAll(Pageable pageable);
    Optional<Entities> findByEntitiesId(String id);

}