package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idc.sterba.demo.entity.enums.GoalTypeEnum;

import javax.persistence.*;

@Entity
//@NamedNativeQuery(name = "TeamScore.findTeamScoreByRoundId", query = "select\n" +
//        "  count(blueTeam.TYPE) as BLUETEAM,\n" +
//        "  count(redTeam.TYPE) as REDTEAM\n" +
//        "from (select\n" +
//        "        case when subQuery.colorenum = 'BLUE' and subQuery.type = 'OWN_GOAL'\n" +
//        "          then 'RED'\n" +
//        "        when subQuery.colorenum = 'RED' and subQuery.type = 'OWN_GOAL'\n" +
//        "          then 'BLUE'\n" +
//        "        else subQuery.colorenum end as COLOR,\n" +
//        "        subQuery.type               as TYPE\n" +
//        "      from (select\n" +
//        "              c.colorenum,\n" +
//        "              g.type\n" +
//        "            from team t\n" +
//        "              join color c on c.id = t.color_id\n" +
//        "              join teamscore t2 on t.id = t2.team_id\n" +
//        "              join goaltype g on t2.goaltype_id = g.id\n" +
//        "            where t.round_id = :roundId) subQuery) blueTeam\n" +
//        "join (select\n" +
//        "        case when subQuery.colorenum = 'BLUE' and subQuery.type = 'OWN_GOAL'\n" +
//        "          then 'RED'\n" +
//        "        when subQuery.colorenum = 'RED' and subQuery.type = 'OWN_GOAL'\n" +
//        "          then 'BLUE'\n" +
//        "        else subQuery.colorenum end as COLOR,\n" +
//        "        subQuery.type               as TYPE\n" +
//        "      from (select\n" +
//        "              c.colorenum,\n" +
//        "              g.type\n" +
//        "            from team t\n" +
//        "              join color c on c.id = t.color_id\n" +
//        "              join teamscore t2 on t.id = t2.team_id\n" +
//        "              join goaltype g on t2.goaltype_id = g.id\n" +
//        "            where t.round_id = :roundId) subQuery) redTeam on redTeam.COLOR = 'RED'\n" +
//        "where blueTeam.COLOR = 'BLUE'", resultSetMapping = "teamScoreMapping")
//@SqlResultSetMapping(
//        name = "teamScoreMapping",
//        classes = {
//                @ConstructorResult(
//                        targetClass = ScoreDTO.class,
//                        columns = {
//                                @ColumnResult(name = "BLUETEAM"),
//                                @ColumnResult(name = "REDTEAM")
//                        }
//                )
//        }
//)
public class TeamScore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamScoreIdSeq")
    @SequenceGenerator(name = "teamScoreIdSeq", sequenceName = "team_score_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Team team;

    @OneToOne
    private Employee employee;

    @Column(name = "goal_type")
    @Enumerated(EnumType.STRING)
    private GoalTypeEnum goalType;

    @OneToOne
    @JsonIgnore
    private Employee loggedBy;

    public TeamScore() {
    }

    public TeamScore(Team team, Employee employee, GoalTypeEnum goalType, Employee loggedBy) {
        this.team = team;
        this.employee = employee;
        this.goalType = goalType;
        this.loggedBy = loggedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public GoalTypeEnum getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalTypeEnum goalType) {
        this.goalType = goalType;
    }

    public Employee getLoggedBy() {
        return loggedBy;
    }

    public void setLoggedBy(Employee loggedBy) {
        this.loggedBy = loggedBy;
    }
}
