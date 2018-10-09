package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.FilterDTO;
import com.idc.sterba.demo.entity.report.EmployeeMatchesReport;
import com.idc.sterba.demo.entity.report.GoalDidGetReport;
import com.idc.sterba.demo.entity.report.MatchWinCountReport;
import com.idc.sterba.demo.entity.report.RoundWinCountReport;
import com.idc.sterba.demo.exception.EmptyFilterException;
import com.idc.sterba.demo.repository.report.EmployeeMatchesReportRepository;
import com.idc.sterba.demo.repository.report.GoalDidGetReportRepository;
import com.idc.sterba.demo.repository.report.MatchWinCountReportRepository;
import com.idc.sterba.demo.repository.report.RoundWinCountReportRepository;
import com.idc.sterba.demo.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private MatchWinCountReportRepository matchWinCountReportRepository;
    private EmployeeMatchesReportRepository employeeMatchesReportRepository;
    private RoundWinCountReportRepository roundWinCountReportRepository;
    private GoalDidGetReportRepository goalDidGetReportRepository;

    public ReportServiceImpl(MatchWinCountReportRepository matchWinCountReportRepository, EmployeeMatchesReportRepository employeeMatchesReportRepository,
                             RoundWinCountReportRepository roundWinCountReportRepository, GoalDidGetReportRepository goalDidGetReportRepository) {
        this.matchWinCountReportRepository = matchWinCountReportRepository;
        this.employeeMatchesReportRepository = employeeMatchesReportRepository;
        this.roundWinCountReportRepository = roundWinCountReportRepository;
        this.goalDidGetReportRepository = goalDidGetReportRepository;
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

    @Override
    public List<RoundWinCountReport> getRoundWinCountReport(FilterDTO filterDTO) throws EmptyFilterException {
        if (filterDTO.isFilterFilled()) {
            return this.roundWinCountReportRepository.getReportData(filterDTO.getSeasonIds(), filterDTO.getGroupIds());
        } else {
            throw new EmptyFilterException();
        }
    }

    @Override
    public List<GoalDidGetReport> getGoalDidGetReport (FilterDTO filterDTO) throws EmptyFilterException {
        if (filterDTO.isFilterFilled()) {
            return this.goalDidGetReportRepository.getReportData(filterDTO.getSeasonIds(), filterDTO.getGroupIds());
        } else {
            throw new EmptyFilterException();
        }
    }
}
