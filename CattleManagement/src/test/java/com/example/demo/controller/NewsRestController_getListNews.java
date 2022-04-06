package com.example.demo.controller;

import com.example.demo.model.Entities;
import com.example.demo.model.News;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class NewsRestController_getListNews {
    @Autowired
    private NewsController newsController;
    @Test
    public void getListNews_5() {

        ResponseEntity<Page<News>> responseEntity
                = this.newsController.findAllNews(PageRequest.of(0,2));

        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
    }

}
