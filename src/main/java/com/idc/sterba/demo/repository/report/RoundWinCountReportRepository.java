package com.idc.sterba.demo.repository.report;

import com.idc.sterba.demo.entity.report.RoundWinCountReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoundWinCountReportRepository extends JpaRepository<RoundWinCountReport, Long> {

    @Query("select r from RoundWinCountReport r where r.seasonId in :seasonIds and r.groupId in :groupIds")
    List<RoundWinCountReport> getReportData(@Param("seasonIds") List<Long> seasonIds, @Param("groupIds") List<Long> groupIds);
}
