package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.PlayerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerGroupRepository extends JpaRepository<PlayerGroup, Long> {

    List<PlayerGroup> getAllByLeader_Id(Long leaderId);
}
