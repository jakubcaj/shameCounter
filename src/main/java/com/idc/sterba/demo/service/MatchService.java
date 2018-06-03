package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.Round;
import com.idc.sterba.demo.entity.Team;
import com.idc.sterba.demo.repository.MatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchService {

    private MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Transactional
    public Match getMatch(Long id) {
        Match match = matchRepository.getOne(id);
//        match.getTeam1();
//        match.getTeam2();
        return match;
    }

    public Match saveMatch(MatchDTO matchDTO) {

        Match match;
        if (matchDTO == null) {
            match = new Match();
        } else {
            match = new Match(matchDTO);
        }
        matchRepository.save(match);
        return match;
    }
}
