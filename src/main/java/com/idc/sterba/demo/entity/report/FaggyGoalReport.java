package com.idc.sterba.demo.entity.report;

import org.eclipse.persistence.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Cache(shared = false)
@Table(name = "faggy_goal_report", schema = "report")
public class FaggyGoalReport {
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

    @Column(name = "faggy_goal_count")
    private Long faggyGoalCount;

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

    public Long getFaggyGoalCount() {
        return faggyGoalCount;
    }

    public void setFaggyGoalCount(Long faggyGoalCount) {
        this.faggyGoalCount = faggyGoalCount;
    }
}
