package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Match getMatchByFinishedAndId(boolean finished, Long id);

    List<Match> findAllByFinishedOrderByIdAsc(boolean finished);
}
