package com.idc.sterba.demo.repository.report;

import com.idc.sterba.demo.entity.report.MatchWinCountReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MatchWinCountReportRepository extends JpaRepository<MatchWinCountReport, Long> {

    @Query("select m from MatchWinCountReport m")
    List<MatchWinCountReport> getReportData();
}
