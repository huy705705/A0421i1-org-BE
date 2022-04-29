package com.example.demo.repository;

import com.example.demo.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<News, String> {
    @Query(value = "select * from news "  + " order by created_date desc ", nativeQuery = true)
    Page<News> findAllSortDate(Pageable pageable);
    @Override
    Page<News> findAll(Pageable pageable);

    Page<News> findAllByNewsNameContaining(String name, Pageable pageable);
    Page<News> findAllByHighlight(String name, Pageable pageable);
}