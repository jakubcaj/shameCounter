package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.FilterDTO;
import com.idc.sterba.demo.entity.report.EmployeeMatchesReport;
import com.idc.sterba.demo.entity.report.MatchWinCountReport;
import com.idc.sterba.demo.entity.report.RoundWinCountReport;
import com.idc.sterba.demo.exception.EmptyFilterException;

import java.util.List;

public interface ReportService {

    List<MatchWinCountReport> getMatchWinCountReport(FilterDTO filterDTO) throws EmptyFilterException;

    List<EmployeeMatchesReport> getEmployeeMatchesReport(FilterDTO filterDTO, Long employeeId) throws EmptyFilterException;

    List<RoundWinCountReport> getRoundWinCountReport(FilterDTO filterDTO) throws EmptyFilterException;
}
