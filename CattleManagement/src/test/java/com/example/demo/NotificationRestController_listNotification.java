package com.example.demo;

import com.example.demo.controller.EntitiesController;
import com.example.demo.controller.NotificationController;
import com.example.demo.model.Entities;
import com.example.demo.model.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@SpringBootTest
public class NotificationRestController_listNotification {
    @Autowired
    private NotificationController notificationController;
    @Test
    public void getListEntities_5() {

        ResponseEntity<Page<Notification>> responseEntity
                = this.notificationController.findAllNotification(PageRequest.of(0,2));

        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
    }
    @Test
    public void getListStudent_6() {
        ResponseEntity<Page<Notification>> responseEntity
                = this.notificationController.findAllNotification(PageRequest.of(0, 2));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(4, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("1",
                responseEntity.getBody().getContent().get(0).getNotificationId());
        LocalDate date = LocalDate.of(2022,01,02);
        Assertions.assertEquals(date,
                responseEntity.getBody().getContent().get(0).getUploadDate());
    }
}
