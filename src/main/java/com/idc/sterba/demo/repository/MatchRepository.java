package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Match getMatchByFinishedAndId(boolean finished, Long id);

    List<Match> findAllByFinishedOrderByIdAsc(boolean finished);

//    Match getMatchByRoundList_TeamList_TeamPlayerList_Employee_IdAndFinishedIsFalseOrderByIdAsc();

    @Query("select m from Match m " +
            "join m.roundList r " +
            "join r.teamList t " +
            "join t.teamPlayerList tp " +
            "join tp.employee e " +
            "where m.finished = false and  e.id = :employeeId order by m.id asc")
    List<Match> findAllUnfinishedMatchesByEmployee_id(@Param("employeeId") Long employeeId);

//    where m.finished = false and m.id = :matchId
}
