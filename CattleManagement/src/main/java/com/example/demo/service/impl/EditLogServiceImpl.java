package com.example.demo.service.impl;

import com.example.demo.model.EditLog;
import com.example.demo.repository.EditLogRepo;
import com.example.demo.service.EditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditLogServiceImpl implements EditLogService {
    @Autowired
    private EditLogRepo editLogRepo;

    @Override
    public List<EditLog> findAllByCageId(String cageId) {
        return editLogRepo.getAllLogByCageId(cageId);
    }

    @Override
    public EditLog save(EditLog editLog) {
        return editLogRepo.save(editLog);
    }
}
