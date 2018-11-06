package com.idc.sterba.demo.repository.report;

import com.idc.sterba.demo.entity.report.FaggyGoalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaggyGoalReportRepository extends JpaRepository<FaggyGoalReport, Long> {

    @Query("select f from FaggyGoalReport f where f.seasonId in :seasonIds and f.groupId in :groupIds")
    List<FaggyGoalReport> getReportData(@Param("seasonIds") List<Long> seasonIds, @Param("groupIds") List<Long> groupIds);
}
