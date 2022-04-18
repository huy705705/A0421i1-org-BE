package com.example.demo.service.impl;

import com.example.demo.model.News;
import com.example.demo.repository.NewsRepo;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepo newsRepo;
    public Page<News> findAll(Pageable pageable){
        return newsRepo.findAll(pageable);
    }

    @Override
    public Page<News> findAllByNewsNameContaining(String name, Pageable pageable) {
        return newsRepo.findAllByNewsNameContaining(name, pageable);
    }

    @Override
    public Page<News> findAllByHighlight(String hightLight, Pageable pageable) {
        return newsRepo.findAllByHighlight(hightLight, pageable);
    }


}
