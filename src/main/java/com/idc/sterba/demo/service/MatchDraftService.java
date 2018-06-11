package com.idc.sterba.demo.service;

import com.idc.sterba.demo.entity.MatchDraft;

public interface MatchDraftService {

    MatchDraft getMatchDraftById(Long id);
    MatchDraft createMatchDraft(MatchDraft matchDraft);
    void updateMatchDraft(MatchDraft matchDraft);
//    void updateMatchDraft(Long matchDraftId, MatchDTO matchDTO);
}
