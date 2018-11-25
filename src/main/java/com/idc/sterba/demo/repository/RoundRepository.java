package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {

    Round findByMatch_IdAndRunning(Long matchId, boolean running);

    Round findFirstByMatch_IdOrderByIdDesc(Long matchId);

    @Modifying(clearAutomatically = true)
    @Query("update Round r set r.running = true where r.id = :roundId")
    void setRoundAsRunning(@Param("roundId") Long roundId);

    @Modifying(clearAutomatically = true)
    @Query("delete from Round r where r.match.id = :matchId and r.running = true")
    void deleteRunningRound(@Param("matchId") Long matchId);
}
