package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    private GoalType goalType;

    public TeamScore() {
    }

    public TeamScore(Team team, Employee employee, GoalType goalType) {
        this.team = team;
        this.employee = employee;
        this.goalType = goalType;
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

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }
}
