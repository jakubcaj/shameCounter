package com.idc.sterba.demo.repository.report;

import com.idc.sterba.demo.entity.report.MatchWinCountReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchWinCountReportRepository extends JpaRepository<MatchWinCountReport, Long> {

    @Query("select m from MatchWinCountReport m where m.seasonId in :seasonIds and m.groupId in :groupIds")
    List<MatchWinCountReport> getReportData(@Param("seasonIds") List<Long> seasonIds, @Param("groupIds") List<Long> groupIds);
}
