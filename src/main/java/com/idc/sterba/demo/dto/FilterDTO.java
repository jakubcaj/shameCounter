package com.idc.sterba.demo.dto;

import java.util.List;

public class FilterDTO {
    private List<Long> seasonIds;
    private List<Long> groupIds;

    public List<Long> getSeasonIds() {
        return seasonIds;
    }

    public void setSeasonIds(List<Long> seasonIds) {
        this.seasonIds = seasonIds;
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }

    public boolean isFilterFilled() {
        return seasonIds != null && groupIds != null && !seasonIds.isEmpty() && !groupIds.isEmpty();
    }
}
