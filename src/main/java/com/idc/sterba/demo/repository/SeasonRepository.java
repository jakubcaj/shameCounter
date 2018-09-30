package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

    Season findByActiveIsTrue();

    @Query(value = "select s from Season s order by s.startedAt desc")
    List<Season> findAllOrderByStartedAt();

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Season s set s.active = false where s.active = true")
    void updateActiveSeasonToFalse();
}
