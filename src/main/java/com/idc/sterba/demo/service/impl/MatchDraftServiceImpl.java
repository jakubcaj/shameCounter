package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.MatchDraft;
import com.idc.sterba.demo.repository.MatchDraftRepository;
import com.idc.sterba.demo.service.MatchDraftService;
import org.springframework.stereotype.Service;

@Service
public class MatchDraftServiceImpl implements MatchDraftService {

    private MatchDraftRepository matchDraftRepository;

    public MatchDraftServiceImpl(MatchDraftRepository matchDraftRepository) {
        this.matchDraftRepository = matchDraftRepository;
    }

    @Override
    public MatchDraft getMatchDraftById(Long id) {
        return matchDraftRepository.getOne(id);
    }

    @Override
    public MatchDraft createMatchDraft(MatchDraft matchDraft) {
        matchDraftRepository.save(matchDraft);
        return matchDraft;
    }

    @Override
    public void updateMatchDraft(MatchDraft matchDraft) {
        matchDraftRepository.save(matchDraft);
    }
}
