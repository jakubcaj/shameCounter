package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.FilterDTO;
import com.idc.sterba.demo.entity.report.*;
import com.idc.sterba.demo.exception.EmptyFilterException;

import java.util.List;

public interface ReportService {

    List<MatchWinCountReport> getMatchWinCountReport(FilterDTO filterDTO) throws EmptyFilterException;

    List<EmployeeMatchesReport> getEmployeeMatchesReport(FilterDTO filterDTO, Long employeeId) throws EmptyFilterException;

    List<RoundWinCountReport> getRoundWinCountReport(FilterDTO filterDTO) throws EmptyFilterException;

    List<GoalDidGetReport> getGoalDidGetReport (FilterDTO filterDTO) throws EmptyFilterException;

    List<MatchPositionReport> getMatchPositionReport (FilterDTO filterDTO) throws EmptyFilterException;

    List<OwnGoalReport> getOwnGoalReport (FilterDTO filterDTO) throws EmptyFilterException;

    List<StampGoalReport> getStampGoalReport (FilterDTO filterDTO) throws EmptyFilterException;

    List<FaggyGoalReport> getFaggyGoalReport (FilterDTO filterDTO) throws EmptyFilterException;
}
