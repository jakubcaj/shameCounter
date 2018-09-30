package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.FilterDTO;
import com.idc.sterba.demo.entity.report.EmployeeMatchesReport;
import com.idc.sterba.demo.entity.report.MatchWinCountReport;
import com.idc.sterba.demo.exception.EmptyFilterException;
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
    public List<MatchWinCountReport> getMatchWinCountReport(FilterDTO filterDTO) throws EmptyFilterException {
        if (filterDTO.isFilterFilled()) {
            return this.matchWinCountReportRepository.getReportData(filterDTO.getSeasonIds(), filterDTO.getGroupIds());
        } else {
            throw new EmptyFilterException();
        }
    }

    @Override
    public List<EmployeeMatchesReport> getEmployeeMatchesReport(FilterDTO filterDTO, Long employeeId) throws EmptyFilterException {
        if (filterDTO.isFilterFilled()) {
            return this.employeeMatchesReportRepository.getReportData(employeeId, filterDTO.getSeasonIds(), filterDTO.getGroupIds());
        } else {
            throw new EmptyFilterException();
        }
    }
}
