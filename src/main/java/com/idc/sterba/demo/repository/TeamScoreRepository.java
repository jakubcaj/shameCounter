package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.TeamScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamScoreRepository extends JpaRepository<TeamScore, Long> {

}
