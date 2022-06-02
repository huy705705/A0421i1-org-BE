package com.example.demo.edit_logger;

import com.example.demo.repository.EditLogRepo;
import com.example.demo.service.impl.EditLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearLogSchedule {
    @Autowired
    private EditLogServiceImpl editLogService;

    @Scheduled(cron = "30 * * * * *")
//    @Scheduled(cron = "0 0 30 3,6,9,12 *")
    public void scheduleClearLog(){
//        editLogService.deleteAll();
        System.out.println("finish delete all " );
    }
}
