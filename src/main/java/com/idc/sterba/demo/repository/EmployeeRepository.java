package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee e WHERE concat(e.firstName, ' ', e.lastName) ILIKE concat('%', ?1, '%')",
            nativeQuery = true)
    List<Employee> findAllBySearchTerm(String term);

    @Query(value = "SELECT * from employee e " +
            "join secure.employee_metadata em on em.employee_id = e.id " +
            "where em.username = ?1", nativeQuery = true)
    Employee findByUsername(String username);

}
