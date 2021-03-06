package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.TeamScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamScoreRepository extends JpaRepository<TeamScore, Long> {

    List<TeamScore> findAllByTeam_Round_Match_Id(Long matchId);
}
