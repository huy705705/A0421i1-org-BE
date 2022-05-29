package com.example.demo.repository;

import com.example.demo.model.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordHistoryRepo extends JpaRepository<PasswordHistory, Integer> {

    @Query(value = "select * from password_history where account_id = ?", nativeQuery = true)
    List<PasswordHistory> findAllByAccountId(long id);



}
