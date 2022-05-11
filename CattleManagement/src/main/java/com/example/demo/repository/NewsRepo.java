package com.example.demo.repository;

import com.example.demo.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<News, String> {
    @Query(value = "select * from news " + " where is_delete = '0' " + " order by created_date desc " , nativeQuery = true)
    Page<News> findAllSortDate(Pageable pageable);
//    @Override
    @Query(value = "SELECT news.news_id, news.content, news.created_date, news.detail_description, news.highlight, news.image, news.is_delete, news.news_name, news.total_view, news.type, employee.employee_name FROM news inner join employee on news.employee_id = employee.employee_id " + " where news.is_delete = 0 " + " order by created_date desc " , nativeQuery = true)
    Page<News> findAll(Pageable pageable);
//    @Query(value = "SELECT news.content, news.created_date, news.detail_description, news.highlight, news.image, news.is_delete, news.news_name,employee.employee_name FROM news inner join employee on news.employee_id = employee.employee_id " + " where news.news_id = username and news.is_delete = 0 " , nativeQuery = true)
//    News findNewsByNewsId(String newsId);
    @Query(value = "SELECT * FROM news " + " where news.is_delete = 0 and news.news_name like %:name% " + " order by created_date desc " , nativeQuery = true)
    Page<News> findAllByNewsNameContainingDate(String name,Pageable pageable);

    @Query(value = "SELECT * FROM news " + " where news.is_delete = 0 and news.news_name like %:name% " + " order by created_date asc " , nativeQuery = true)
    Page<News> findAllByNewsNameContainingDateEsc(String name,Pageable pageable);

    @Query(value = "SELECT * FROM news " + " where news.is_delete = 0 and news.news_name like %:name% " + " order by total_view desc " , nativeQuery = true)
    Page<News> findAllByNewsNameContainingViews(String name,Pageable pageable);

    Page<News> findAllByNewsNameContaining(String name, Pageable pageable);
    Page<News> findAllByHighlight(String name, Pageable pageable);
    News findNewsByNewsId(String newsId);
    @Query(value = "select * from news "+ " order by total_view desc " , nativeQuery = true)
    Page<News> findAllSortTotalView(Pageable pageable);
}