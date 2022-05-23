package com.example.demo.repository;

import com.example.demo.model.Cage;
import com.example.demo.model.dto.CageListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CageRepo extends JpaRepository<Cage, String> {


    @Query(value = "select cage_id from cage",nativeQuery = true)
    List<String> getListCageId();

    Cage findByCageId(String Id);

    @Query(value=" UPDATE cage SET is_delete = b'1' WHERE cage_id = ? ", nativeQuery= true)
    void removeCage(String id);

    @Query(value = "select c.cage_id as cageId, c.closed_date as closedDate, c.created_date as createdDate, c.quantity,e.employee_name as employeeName,count(et.entities_id) as entitiesQuantity " +
            "from cage c left join employee e on c.employee_id=e.employee_id " +
            "left join entities et on c.cage_id=et.cage_id " +
            "group by c.cage_id",
            countQuery="select count(cage_id) from cage",nativeQuery=true)
    Page<CageListDTO> findAllCage(Pageable pageable);

    @Query(value = "select c.cage_id as cageId, c.closed_date as closedDate, c.created_date as createdDate, c.quantity,e.employee_name as employeeName,count(et.entities_id) as entitiesQuantity " +
            "from cage c left join employee e on c.employee_id=e.employee_id " +
            "left join entities et on c.cage_id=et.cage_id " +
            "where c.cage_id like ?1 and c.created_date>=?2 and c.created_date<=?3 "+
            "group by c.cage_id",
            countQuery="select count(c.cage_id) from cage c where c.cage_id like ?1 and c.created_date>=?2 and c.created_date<=?3",nativeQuery=true)
    Page<CageListDTO> findCageByCreatedDate(String cageId, String dateFrom, String dateTo, Pageable pageable);

    @Query(value = "select c.cage_id as cageId, c.closed_date as closedDate, c.created_date as createdDate, c.quantity,e.employee_name as employeeName,count(et.entities_id) as entitiesQuantity " +
            "from cage c left join employee e on c.employee_id=e.employee_id " +
            "left join entities et on c.cage_id=et.cage_id " +
            "where c.cage_id like ?1 and c.closed_date>=?2 and c.closed_date<=?3 "+
            "group by c.cage_id",
            countQuery="select count(c.cage_id) from cage c where c.cage_id like ?1 and c.closed_date>=?2 and c.closed_date<=?3",nativeQuery=true)
    Page<CageListDTO> findCageByClosedDate(String cageId, String dateFrom, String dateTo, Pageable pageable);
}