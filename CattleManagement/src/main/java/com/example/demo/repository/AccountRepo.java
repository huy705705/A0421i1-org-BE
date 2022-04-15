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

    Account findAccountByEmail(String email);

    Account findAccountByResetPasswordToken(String token);

    @Query(value = "select account_id from account where account_name = ?", nativeQuery = true)
    Long findAccountIdByAccountName(String accountName);


    @Query(value = "select * from account", nativeQuery = true)
    List<Account> getAllAccount();


    @Modifying
    @Query(value ="update account set reset_password_token=? where account_name =?",nativeQuery = true)
    void addResetPassToken(String token, String accountName);

    @Modifying
    @Query(value = "update account set password =?,reset_password_token=null where reset_password_token=? ",nativeQuery = true)
    void saveNewPassword(String password, String token);
}