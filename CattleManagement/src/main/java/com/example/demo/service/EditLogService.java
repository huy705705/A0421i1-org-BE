package com.example.demo.service;

import com.example.demo.model.Cage;
import com.example.demo.model.EditLog;

import java.util.List;

public interface EditLogService {
    List<EditLog> findAllByCageId(String cageId);

    EditLog save (EditLog editLog);

    void deleteAll ();
}
