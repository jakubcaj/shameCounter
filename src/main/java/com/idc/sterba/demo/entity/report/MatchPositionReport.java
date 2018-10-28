package com.idc.sterba.demo.entity.report;

import org.eclipse.persistence.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Cache(shared = false)
@Table(name = "match_position_report", schema = "report")
public class MatchPositionReport {
    @Id
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    private String firstname;

    private String lastname;

    @Column(name = "player_group_id")
    private Long groupId;

    @Column(name = "player_group_name")
    private String groupName;

    @Column(name = "season_id")
    private Long seasonId;

    @Column(name = "season_name")
    private String seasonName;

    @Column(name = "attacker_count")
    private Long attackerCount;

    @Column(name = "goalman_count")
    private Long goalmanCount;

    @Column(name = "both_count")
    private Long bothCount;

    @Column(name = "total_match_count")
    private Long totalMatchCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public Long getAttackerCount() {
        return attackerCount;
    }

    public void setAttackerCount(Long attackerCount) {
        this.attackerCount = attackerCount;
    }

    public Long getGoalmanCount() {
        return goalmanCount;
    }

    public void setGoalmanCount(Long goalmanCount) {
        this.goalmanCount = goalmanCount;
    }

    public Long getBothCount() {
        return bothCount;
    }

    public void setBothCount(Long bothCount) {
        this.bothCount = bothCount;
    }

    public Long getTotalMatchCount() {
        return totalMatchCount;
    }

    public void setTotalMatchCount(Long totalMatchCount) {
        this.totalMatchCount = totalMatchCount;
    }
}
