package com.example.demo.repository;

import com.example.demo.model.Entities;
import com.example.demo.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Integer> {
    @Query(value = "SELECT * FROM notification  where is_delete!=1 or is_delete=null order by upload_date desc", nativeQuery = true)
    Page<Notification> findAll(Pageable pageable);
    Optional<Notification> findByNotificationId(int id);


    @Modifying
    @Transactional
    @Query(value = "  update notification  set is_delete = 1 where notification_id = ? ", nativeQuery = true)
    void deleteNotification(int id);

    @Query(value = "SELECT * FROM notification  where ( upload_date between :uploadDateMin and :uploadDateMax ) order by upload_date desc", nativeQuery = true)
    Page<Notification> findAllByUploadDate3(@Param("page") Pageable pageable, @Param("uploadDateMin") String uploadDateMin,
                                           @Param("uploadDateMax") String uploadDateMax);
}
