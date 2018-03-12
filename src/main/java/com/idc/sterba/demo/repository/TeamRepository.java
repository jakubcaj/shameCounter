package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    //public void save(Match match);
}
