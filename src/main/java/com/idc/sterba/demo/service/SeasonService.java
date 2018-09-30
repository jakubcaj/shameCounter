package com.idc.sterba.demo.service;

import com.idc.sterba.demo.entity.Season;
import com.idc.sterba.demo.entity.enums.SeasonEnum;

import java.util.List;

public interface SeasonService {

    void createNewSeason(SeasonEnum season);

    void createNewSeason();

    Season getActiveSeason();

    List<Season> getAllSeasons();
}
