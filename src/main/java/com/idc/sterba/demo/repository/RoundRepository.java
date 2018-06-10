package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {

    Round getRoundByMatch_IdAndRunning(Long matchId, boolean running);
}
