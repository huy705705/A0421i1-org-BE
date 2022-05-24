package com.example.demo.model.dto;



public interface GetOldCustomerDTO {

    Integer getCustomerId();
    String getPhone();
    String getFullName();
    String getEmail();
    String getAddress();
    boolean getGender();
    Integer getProvinceId();
    Integer getDistrictId();
    Integer getWardId();
    String getMessage();
    String getCreatedDate();
}