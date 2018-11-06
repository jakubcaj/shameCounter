package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.FilterDTO;
import com.idc.sterba.demo.entity.report.*;
import com.idc.sterba.demo.exception.EmptyFilterException;
import com.idc.sterba.demo.repository.report.*;
import com.idc.sterba.demo.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private MatchWinCountReportRepository matchWinCountReportRepository;
    private EmployeeMatchesReportRepository employeeMatchesReportRepository;
    private RoundWinCountReportRepository roundWinCountReportRepository;
    private GoalDidGetReportRepository goalDidGetReportRepository;
    private MatchPositionReportRepository matchPositionReportRepository;
    private OwnGoalReportRepository ownGoalReportRepository;
    private StampGoalReportRepository stampGoalReportRepository;
    private FaggyGoalReportRepository faggyGoalReportRepository;

    public ReportServiceImpl(MatchWinCountReportRepository matchWinCountReportRepository, EmployeeMatchesReportRepository employeeMatchesReportRepository,
                             RoundWinCountReportRepository roundWinCountReportRepository, GoalDidGetReportRepository goalDidGetReportRepository,
                             MatchPositionReportRepository matchPositionReportRepository, OwnGoalReportRepository ownGoalReportRepository,
                             StampGoalReportRepository stampGoalReportRepository, FaggyGoalReportRepository faggyGoalReportRepository) {
        this.matchWinCountReportRepository = matchWinCountReportRepository;
        this.employeeMatchesReportRepository = employeeMatchesReportRepository;
        this.roundWinCountReportRepository = roundWinCountReportRepository;
        this.goalDidGetReportRepository = goalDidGetReportRepository;
        this.matchPositionReportRepository = matchPositionReportRepository;
        this.ownGoalReportRepository = ownGoalReportRepository;
        this.stampGoalReportRepository = stampGoalReportRepository;
        this.faggyGoalReportRepository = faggyGoalReportRepository;
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

    @Override
    public List<MatchPositionReport> getMatchPositionReport(FilterDTO filterDTO) throws EmptyFilterException {
        if (filterDTO.isFilterFilled()) {
            return this.matchPositionReportRepository.getReportData(filterDTO.getSeasonIds(), filterDTO.getGroupIds());
        } else {
            throw new EmptyFilterException();
        }
    }

    @Override
    public List<OwnGoalReport> getOwnGoalReport(FilterDTO filterDTO) throws EmptyFilterException {
        if (filterDTO.isFilterFilled()) {
            return this.ownGoalReportRepository.getReportData(filterDTO.getSeasonIds(), filterDTO.getGroupIds());
        } else {
            throw new EmptyFilterException();
        }
    }

    @Override
    public List<StampGoalReport> getStampGoalReport(FilterDTO filterDTO) throws EmptyFilterException {
        if (filterDTO.isFilterFilled()) {
            return this.stampGoalReportRepository.getReportData(filterDTO.getSeasonIds(), filterDTO.getGroupIds());
        } else {
            throw new EmptyFilterException();
        }
    }

    @Override
    public List<FaggyGoalReport> getFaggyGoalReport(FilterDTO filterDTO) throws EmptyFilterException {
        if (filterDTO.isFilterFilled()) {
            return this.faggyGoalReportRepository.getReportData(filterDTO.getSeasonIds(), filterDTO.getGroupIds());
        } else {
            throw new EmptyFilterException();
        }
    }
}
