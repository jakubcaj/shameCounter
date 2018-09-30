package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.EmployeePlayerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePlayerGroupRepository extends JpaRepository<EmployeePlayerGroup, Long> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update employee_player_group epg set epg.chanceCoeficient = :coeficient where epg.employee.id = :employeeId and epg.playerGroup.id = :groupId")
    void updateCoeficient(@Param("coeficient") Double coeficient, @Param("employeeId") Long employeeId, @Param("groupId") Long groupId);
}
