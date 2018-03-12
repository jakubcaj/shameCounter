package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    //public void save(Match match);
}
