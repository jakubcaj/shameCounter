package com.idc.sterba.demo.repository.report;

import com.idc.sterba.demo.entity.report.MatchPositionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchPositionReportRepository extends JpaRepository<MatchPositionReport, Long> {

    @Query("select m from MatchPositionReport m where m.seasonId in :seasonIds and m.groupId in :groupIds")
    List<MatchPositionReport> getReportData(@Param("seasonIds") List<Long> seasonIds, @Param("groupIds") List<Long> groupIds);
}
