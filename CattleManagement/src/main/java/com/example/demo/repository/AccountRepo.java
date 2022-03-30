package com.example.demo.repository;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findAccountByAccountName (String accountName);

    Boolean existsByAccountName(String name);

    @Query(value = "select account_id from account where account_name = ?", nativeQuery = true)
    Long findAccountIdByAccountName(String accountName);
//
//    @Query(value = "select account_name from account where account_name = ?", nativeQuery = true)
//    Boolean existsByAccountName(String accountName);

    @Query(value = "select * from account", nativeQuery = true)
    List<Account> getAllAccount();

    @Modifying
    @Query(value = "insert into account(account_name,account_password) values (?,?)", nativeQuery = true)
    void addNewAccount(String accountName, String password);

    @Modifying
    @Query(value = "update account set account_password =? where account_name=? ",nativeQuery = true)
    void saveNewPassword(String password);
}