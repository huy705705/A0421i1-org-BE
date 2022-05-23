package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.dto.CustomerModifyDTO;
import com.example.demo.model.dto.GetOldCustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CustomerRepo extends JpaRepository<Customer,String> {

    Customer findCustomerByEmailOrPhone(String email, String phone);
    Boolean existsByCustomerId(Integer customerId);

    @Transactional
    @Modifying
    @Query(value="INSERT INTO customer(full_name,address,phone,email,gender,message,province_id,district_id,ward_id,created_date)" +
            " values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)",nativeQuery=true)
    void createCustomer(String fullName, String address, String phone, String email,
                        Boolean gender, String message, int province, int district, int ward,String createDate);

    @Query(value="select customer_id as customerId,phone,full_name as fullName,email,address,gender,province_id as provinceId,district_id as districtId,ward_id as wardId,message,created_date as createdDate " +
            "from customer where email like ?1 and phone like ?2",nativeQuery=true)
    GetOldCustomerDTO getUpdatedCustomer(String email, String phone);

    @Transactional
    @Modifying
    @Query(value="update customer set full_name=?1,address=?2,phone=?3,email=?4,gender=?5,message=?6,province_id=?7,district_id=?8,ward_id=?9,created_date=?10 " +
            "where customer_id=?11",nativeQuery=true)
    void updateCustomer(String fullName, String address, String phone, String email,
                                     Boolean gender, String message, int province, int district, int ward, String createDate, Integer customerID);

}
