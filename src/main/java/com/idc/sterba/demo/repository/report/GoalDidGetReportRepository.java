package com.idc.sterba.demo.repository.report;

import com.idc.sterba.demo.entity.report.GoalDidGetReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalDidGetReportRepository extends JpaRepository<GoalDidGetReport, Long> {

    @Query("select g from GoalDidGetReport g where g.seasonId in :seasonIds and g.groupId in :groupIds")
    List<GoalDidGetReport> getReportData(@Param("seasonIds") List<Long> seasonIds, @Param("groupIds") List<Long> groupIds);
}
