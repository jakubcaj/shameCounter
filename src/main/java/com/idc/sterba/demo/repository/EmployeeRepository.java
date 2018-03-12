package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //public void save(Match match);
}
