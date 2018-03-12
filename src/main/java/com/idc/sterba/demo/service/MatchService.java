package com.idc.sterba.demo.service;

import com.idc.sterba.demo.entity.Match;
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
}
