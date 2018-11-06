package com.idc.sterba.demo.repository.report;

import com.idc.sterba.demo.entity.report.OwnGoalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnGoalReportRepository extends JpaRepository<OwnGoalReport, Long> {

    @Query("select o from OwnGoalReport o where o.seasonId in :seasonIds and o.groupId in :groupIds")
    List<OwnGoalReport> getReportData(@Param("seasonIds") List<Long> seasonIds, @Param("groupIds") List<Long> groupIds);
}
