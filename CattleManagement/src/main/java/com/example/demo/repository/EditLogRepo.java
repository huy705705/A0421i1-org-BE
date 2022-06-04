package com.example.demo.repository;

import com.example.demo.model.EditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EditLogRepo extends JpaRepository<EditLog, Integer> {
    @Query(value="select * from edit_log where cage_id = ? order by id DESC limit 20",
            countQuery="select count(id) from edit_log",nativeQuery=true)
    List<EditLog> getAllLogByCageId(String cageId);

    @Modifying
    @Transactional
    @Query(value="delete from edit_log where edit_date < current_date() - 90;", nativeQuery= true)
    void deleteAll();
}
