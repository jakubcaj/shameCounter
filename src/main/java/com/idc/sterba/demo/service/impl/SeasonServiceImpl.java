package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.Season;
import com.idc.sterba.demo.entity.enums.SeasonEnum;
import com.idc.sterba.demo.repository.SeasonRepository;
import com.idc.sterba.demo.service.SeasonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {

    private SeasonRepository seasonRepository;

    public SeasonServiceImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }


    @Override
    public void createNewSeason(SeasonEnum seasonEnum) {
        this.seasonRepository.updateActiveSeasonToFalse();

        Season season = new Season(seasonEnum);
        this.seasonRepository.save(season);
    }

    @Override
    @Transactional
    public void createNewSeason() {
        Season currentSeason = getActiveSeason();
        SeasonEnum futureSeason;

        switch (currentSeason.getSeason()) {
            case SPRING: futureSeason = SeasonEnum.SUMMER; break;
            case SUMMER: futureSeason = SeasonEnum.AUTUMN; break;
            case AUTUMN: futureSeason = SeasonEnum.WINTER; break;
            case WINTER: futureSeason = SeasonEnum.SPRING; break;
            default: futureSeason = SeasonEnum.SPRING; break;
        }

        createNewSeason(futureSeason);
    }

    @Override
    public Season getActiveSeason() {
        return this.seasonRepository.findByActiveIsTrue();
    }

    @Override
    public List<Season> getAllSeasons() {
        return this.seasonRepository.findAllOrderByStartedAt();
    }
}
