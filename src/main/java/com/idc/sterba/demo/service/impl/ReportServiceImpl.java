package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.report.EmployeeMatchesReport;
import com.idc.sterba.demo.entity.report.MatchWinCountReport;
import com.idc.sterba.demo.repository.report.EmployeeMatchesReportRepository;
import com.idc.sterba.demo.repository.report.MatchWinCountReportRepository;
import com.idc.sterba.demo.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private MatchWinCountReportRepository matchWinCountReportRepository;
    private EmployeeMatchesReportRepository employeeMatchesReportRepository;

    public ReportServiceImpl(MatchWinCountReportRepository matchWinCountReportRepository, EmployeeMatchesReportRepository employeeMatchesReportRepository) {
        this.matchWinCountReportRepository = matchWinCountReportRepository;
        this.employeeMatchesReportRepository = employeeMatchesReportRepository;
    }

    @Override
    public List<MatchWinCountReport> getMatchWinCountReport() {
        return this.matchWinCountReportRepository.getReportData();
    }

    @Override
    public List<EmployeeMatchesReport> getEmployeeMatchesReport(Long employeeId) {
        return this.employeeMatchesReportRepository.getReportData(employeeId);
    }
}
