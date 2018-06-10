package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {


    @Query(value = "select t.* \n" +
            "from match m\n" +
            "join round r on m.id = r.match_id \n" +
            "join team t on r.id = t.round_id\n" +
            "join teamplayer t2 on t.id = t2.team_id " +
            "where m.id = ?1 and t2.employee_id = ?2 order by r.id desc limit 1",
            nativeQuery = true)
    Team getTeamByMatchId(Long matchId, Long employeeId);

//    @Query(value = "select team from Team team join Ro")
//    Team test(@Param("matchId") Long matchId,@Param("employeeId") Long employeeId);
}
