package com.example.demo.repository;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {
    @Query(value = "select * from account", nativeQuery = true)
    List<Account> getAllAccount();
}