package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.report.MatchWinCountReport;
import com.idc.sterba.demo.repository.report.MatchWinCountReportRepository;
import com.idc.sterba.demo.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private MatchWinCountReportRepository matchWinCountReportRepository;

    public ReportServiceImpl(MatchWinCountReportRepository matchWinCountReportRepository) {
        this.matchWinCountReportRepository = matchWinCountReportRepository;
    }

    @Override
    public List<MatchWinCountReport> getMatchWinCountReport() {
        return matchWinCountReportRepository.getReportData();
    }
}
