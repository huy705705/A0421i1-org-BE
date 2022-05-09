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

    @Query(value = "select cage_id from cage where is_delete = 0",nativeQuery = true)
    List<String> getListCageId();

    Cage findByCageId(String Id);

    @Query(value=" UPDATE cage SET is_delete = b'1' WHERE cage_id = ? ", nativeQuery= true)
    void removeCage(String id);

    @Query(value = "select c.cage_id as cageId, c.closed_date as closeDate, c.created_date as createDate, c.quantity,e.employee_name as employeeName,count(et.entities_id) as entitiesQuantity" +
            " from cage c inner join employee e on c.employee_id=e.employee_id inner join entities et on c.cage_id=et.cage_id\n" +
            "group by c.cage_id",nativeQuery=true)
    Page<CageListDTO> findAllCage(Pageable pageable);
}