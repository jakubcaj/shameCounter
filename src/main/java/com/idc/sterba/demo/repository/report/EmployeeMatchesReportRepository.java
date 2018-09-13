package com.idc.sterba.demo.repository.report;

import com.idc.sterba.demo.entity.report.EmployeeMatchesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeMatchesReportRepository extends JpaRepository<EmployeeMatchesReport, Long> {

    @Query("select emr from EmployeeMatchesReport emr where emr.employeeId = :employeeId")
    List<EmployeeMatchesReport> getReportData(@Param("employeeId") Long employeeId);
}
