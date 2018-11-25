package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, Long> {

    List<TeamPlayer> findAllByTeam_Id(Long teamId);
}
