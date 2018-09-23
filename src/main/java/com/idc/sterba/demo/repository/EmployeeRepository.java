package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee e WHERE concat(e.firstName, ' ', e.lastName) ILIKE concat('%', ?1, '%')",
            nativeQuery = true)
    List<Employee> findAllBySearchTerm(String term);

    @Query("select e from Employee e " +
            "join EmployeeMetadata em on em.employee = e " +
            "where em.username = :username")
    Employee findByUsername(@Param("username") String username);

    @Query("select e from Employee e " +
            "join EmployeeMetadata em on em.employee = e " +
            "where em.email = :email")
    Employee findByEmail(@Param("email") String email);

    @Query(value = "SELECT * from employee e " +
            "join employee_player_group g on e.id = g.employee_id " +
            "where g.playergroup_id = ?1"
            , nativeQuery = true)
    List<Employee> findAllByPlayerGroupId(Long id);

}
