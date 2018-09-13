package com.idc.sterba.demo.service;

import com.idc.sterba.demo.entity.report.EmployeeMatchesReport;
import com.idc.sterba.demo.entity.report.MatchWinCountReport;

import java.util.List;

public interface ReportService {

    List<MatchWinCountReport> getMatchWinCountReport();

    List<EmployeeMatchesReport> getEmployeeMatchesReport(Long employeeId);
}
