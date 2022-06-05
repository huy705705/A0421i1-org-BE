package com.example.demo.model.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface CustomerHistoryListDTO {
    String getCreatedDate();
    String getModifyDate();
    String getComment();
    String getStatus();
    String getMessage();
    String getFullName();
    String getEmployeeName();

}
