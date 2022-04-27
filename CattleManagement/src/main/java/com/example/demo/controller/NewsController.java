package com.example.demo.controller;

import com.example.demo.model.News;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/public/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @GetMapping()
    public ResponseEntity<Page<News>> findAllNews(@PageableDefault(size = 6 ) Pageable pageable,
                                                  @RequestParam(value = "search", defaultValue = "")  String search){

        Page<News> news;
        if ("".equals(search)) {
            news = newsService.findAll(pageable);
        } else{
            System.out.println(search);
            news = newsService.findAllByNewsNameContaining(search, pageable);
        }

        if (news.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
    @GetMapping("/hl")
    public ResponseEntity<Page<News>> findAllNewsHightLight(@PageableDefault(size = 3 ) Pageable pageable){
        Page<News> news;
        news = newsService.findAllByHighlight("1", pageable);

        if (news.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

}